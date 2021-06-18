package cn.guxiangfly.apitest.state;

import cn.guxiangfly.apitest.source.BroadCastDemoMySQLSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.tuple.Tuple6;
import org.apache.flink.api.java.typeutils.TupleTypeInfo;
import org.apache.flink.streaming.api.datastream.BroadcastConnectedStream;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;



/**
 * @Author guxiang02
 * @Date 2021/5/11
 **/
public class BroadCastStateDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // socket文本流
        DataStream<String> kafkaDs = env.socketTextStream("localhost", 7777);


        DataStream<Tuple4<String, String, String, Integer>> process = kafkaDs.process(new ProcessFunction<String, Tuple4<String, String, String, Integer>>() {
            @Override
            public void processElement(String value, Context ctx, Collector<Tuple4<String, String, String, Integer>> out) throws Exception {
                JSONObject jsonObject = JSON.parseObject(value);
                String userID = jsonObject.getString("userID");
                String eventTime = jsonObject.getString("eventTime");
                String eventType = jsonObject.getString("eventType");
                Integer productID = jsonObject.getIntValue("productID");

                out.collect(new Tuple4<String, String, String, Integer>(userID, eventTime, eventType, productID));
            }
        });


        DataStreamSource<Tuple2<String, Tuple2<String, Integer>>> mysqlSource = env.addSource(new BroadCastDemoMySQLSource());

        //mysqlSource.print("mysqlSource");

        MapStateDescriptor<String, Tuple2<String, Integer>> broadCastStateDesc = new MapStateDescriptor<>("broadCastState", BasicTypeInfo.STRING_TYPE_INFO,
                TupleTypeInfo.getBasicTupleTypeInfo(String.class, Integer.class));


        BroadcastStream<Tuple2<String, Tuple2<String, Integer>>> broadcastStream = mysqlSource.broadcast(broadCastStateDesc);

        BroadcastConnectedStream<Tuple4<String, String, String, Integer>, Tuple2<String, Tuple2<String, Integer>>> connectedStream = process.connect(broadcastStream);

        process.print("process");

        SingleOutputStreamOperator<Tuple6<String, String, String, Integer, String, Integer>> resDs = connectedStream.process(new BroadcastProcessFunction<
                Tuple4<String, String, String, Integer>,
                Tuple2<String, Tuple2<String, Integer>>,
                Tuple6<String, String, String, Integer, String, Integer>
                >() {


            /**
             * 这里只能对broadcastState进行读取操作
             */
            @Override
            public void processElement(Tuple4<String, String, String, Integer> value, ReadOnlyContext ctx, Collector<Tuple6<String, String, String, Integer, String, Integer>> out) throws Exception {

                ReadOnlyBroadcastState<String, Tuple2<String, Integer>> readOnlyBroadcastState = ctx.getBroadcastState(broadCastStateDesc);


                Tuple2<String, Integer> stringIntegerTuple2 = readOnlyBroadcastState.get(value.f0);
                if (stringIntegerTuple2 != null) {
                    out.collect(new Tuple6<>(value.f0, value.f1, value.f2, value.f3, stringIntegerTuple2.f0, stringIntegerTuple2.f1));
                } else {
                    out.collect(new Tuple6<>(value.f0, value.f1, value.f2, value.f3, "", 0));
                }


            }

            /**
             * 这里对broadcastState进行更新操作
             */
            @Override
            public void processBroadcastElement(Tuple2<String, Tuple2<String, Integer>> value, Context ctx, Collector<Tuple6<String, String, String, Integer, String, Integer>> out) throws Exception {
                BroadcastState<String, Tuple2<String, Integer>> broadcastState = ctx.getBroadcastState(broadCastStateDesc);

                broadcastState.put(value.f0, value.f1);

            }
        });

        resDs.print("result");



        env.execute();

    }
}



