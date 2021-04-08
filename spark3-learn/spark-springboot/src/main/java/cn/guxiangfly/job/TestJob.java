package cn.guxiangfly.job;

import cn.guxiangfly.service.SayService;
import cn.guxiangfly.spark.SparkJob;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Author guxiang02
 * @Date 2021/3/15
 **/
@Service
public class TestJob extends SparkJob {

    @Autowired
    SayService sayService;

    @Override
    public void execute(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("Acc");
        SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();

        SparkContext context = sparkSession.sparkContext();

        JavaSparkContext javaSparkContext = JavaSparkContext.fromSparkContext(context);

        JavaRDD<String> parallelize = javaSparkContext.parallelize(Arrays.asList("hello", "world", "yes"));

        JavaRDD<String> map = parallelize.map(word -> {
            sayService.readAndSay(word);
            return word;
        });

        map.collect();
        javaSparkContext.close();

    }
}
