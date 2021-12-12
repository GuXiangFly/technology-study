package cn.guxiangfly.apitest.state;/**
 * Copyright (c) 2018-2028 尚硅谷 All Rights Reserved
 * <p>
 * Project: FlinkTutorial
 * Package: cn.guxiangfly.apitest.state
 * Version: 1.0
 * <p>
 * Created by wushengran on 2020/11/10 15:30
 */

import cn.guxiangfly.apitest.beans.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName: StateTest1_OperatorState
 * @Description:
 * @Author: wushengran on 2020/11/10 15:30
 * @Version: 1.0
 *  定义一个有状态的map操作，统计当前分区数据个数
 */
public class StateTest1_OperatorState_V2 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStateBackend( new RocksDBStateBackend("file:///Users/mtdp/dev/softwareworkspace/flinkdevhome/savepointhome/StateTest1_OperatorState_V2_checkpoint"));

        //设置canceljob后不删除checkpoint数据
       env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.enableCheckpointing(3000);
        env.setParallelism(1);

        // socket文本流
        DataStream<String> inputStream = env.socketTextStream("localhost", 7777);

        // 转换成SensorReading类型
        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });

        // 定义一个有状态的map操作，统计当前分区数据个数
        SingleOutputStreamOperator<Integer> resultStream = dataStream.map(new MyCountMapper());

        resultStream.print();

        env.execute();
    }

    // 自定义MapFunction

    /**
     * 泛型：第一个输入，第二个输出
     * ListCheckpointed 就是保存列表状态
     */
    public static class MyCountMapper extends RichMapFunction<SensorReading, Integer> implements ListCheckpointed<Integer>{
        // 定义一个本地变量，作为算子状态
        private Integer count = 0;

        @Override
        public void open(Configuration parameters) throws Exception {
            System.out.println("MyCountMapper open ===action");
        }

        @Override
        public Integer map(SensorReading value) throws Exception {
            count++;
            return count;
        }

        /**
         *  这个是对状态做一个快照,将状态进行保存。
         * @param checkpointId
         * @param timestamp
         * @return
         * @throws Exception
         */
        @Override
        public List<Integer> snapshotState(long checkpointId, long timestamp) throws Exception {
            System.out.println("snapshotState==action");
            return Collections.singletonList(count);
        }

        /**
         * 用于将保存的状态进行恢复。
         * @param state
         * @throws Exception
         */
        @Override
        public void restoreState(List<Integer> state) throws Exception {
            for( Integer num: state ) {
                count += num;
            }
            System.out.println("restoreState==action");
        }
    }
}
