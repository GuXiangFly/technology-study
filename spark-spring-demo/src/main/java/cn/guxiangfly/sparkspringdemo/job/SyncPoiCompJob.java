package cn.guxiangfly.sparkspringdemo.job;


import ch.qos.logback.core.pattern.ConverterUtil;
import cn.guxiangfly.sparkspringdemo.spark.SparkJob;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author guxiang02
 * @Date 2021/2/22
 **/
@Service
@Slf4j
public class SyncPoiCompJob extends SparkJob {

    @Value("${env.mytestkey}")
    private String myenvtest;

    @Value("${myfirsttestkey}")
    private String myfirsttestkey;

    @Override
    public void execute(String[] args) throws ParseException {
        System.out.println("myenvis:"+myenvtest);
        System.out.println("myfirsttestkey:"+myfirsttestkey);

        SparkConf conf = new SparkConf().setMaster("local[1]").setAppName("SyncPoiCompJob");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();




        System.out.println("finish");
        spark.close();
    }



}
