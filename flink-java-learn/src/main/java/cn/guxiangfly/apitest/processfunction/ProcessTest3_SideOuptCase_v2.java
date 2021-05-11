package cn.guxiangfly.apitest.processfunction;/**
 * Copyright (c) 2018-2028 尚硅谷 All Rights Reserved
 * <p>
 * Project: FlinkTutorial
 * Package: cn.guxiangfly.apitest.processfunction
 * Version: 1.0
 * <p>
 * Created by wushengran on 2020/11/11 11:32
 */

import cn.guxiangfly.apitest.beans.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @ClassName: ProcessTest3_SideOuptCase
 * @Description:
 * @Author: wushengran on 2020/11/11 11:32
 * @Version: 1.0
 */
public class ProcessTest3_SideOuptCase_v2 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // socket文本流
        DataStream<String> inputStream = env.socketTextStream("localhost", 7777);

        // 转换成SensorReading类型
        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });

        // 定义一个OutputTag，用来表示侧输出流低温流
        OutputTag<SensorReading> lowTemp = new OutputTag<SensorReading>("lowTemp") {
        };

        SingleOutputStreamOperator<SensorReading> highTempStream = dataStream.process(new ProcessFunction<SensorReading, SensorReading>() {
            @Override
            public void processElement(SensorReading value, Context ctx, Collector<SensorReading> out) throws Exception {
                // 判断温度，大于30度，高温流输出到主流；小于低温流输出到侧输出流
                if (value.getTemperature() > 30) {
                    out.collect(value);
                } else {
                    ctx.output(lowTemp, value);
                }
            }
        });

        highTempStream.print("high-temp");
        DataStream<SensorReading> lowTempStream = highTempStream.getSideOutput(lowTemp);

        lowTempStream.print("lowTempStream");

        env.execute();
    }
}