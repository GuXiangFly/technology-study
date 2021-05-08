package cn.guxiangfly.apitest.tableapi;/**
 * Copyright (c) 2018-2028 尚硅谷 All Rights Reserved
 * <p>
 * Project: FlinkTutorial
 * Package: cn.guxiangfly.apitest.tableapi
 * Version: 1.0
 * <p>
 * Created by wushengran on 2020/11/13 9:40
 */

import cn.guxiangfly.apitest.beans.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

/**
 * @ClassName: TableTest1_Example
 * @Description:
 * @Author: wushengran on 2020/11/13 9:40
 * @Version: 1.0
 */
public class TableTest1_Example {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // 1. 读取数据
        DataStreamSource<String> inputStream = env.readTextFile("/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/flink-java-learn/src/main/resources/sensor.txt");

        inputStream.print();

        SingleOutputStreamOperator<Object> map = inputStream.map(row -> {
            if (true) {
                throw new RuntimeException();
            }
            return new Object();
        });

        env.execute();
    }
}
