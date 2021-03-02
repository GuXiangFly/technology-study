package cn.guxiangfly.sparkspringdemo;

import cn.guxiangfly.sparkspringdemo.spark.SparkJob;
import cn.guxiangfly.sparkspringdemo.utils.SpringBootBeanUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SparkSpringDemoApplication implements CommandLineRunner {

    private static final String SPLIT_STR = ":";

    @Value("${mytestenv}")
    private String mytestenv;

    public static void main(String[] args) {
        SpringApplication.run(SparkSpringDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (val arg : args) {
            int index = arg.indexOf(SPLIT_STR);
            String jobName;
            String[] jobArgs;
            if (index > 0) {
                jobName = arg.substring(0, index);
                jobArgs = StringUtils.split(arg.substring(index + 1), SPLIT_STR);
            } else {
                jobName = arg;
                jobArgs = new String[0];
            }
            Class clazz = Class.forName(jobName);
            Object sparkJob = SpringBootBeanUtils.getBean(clazz);
            if (sparkJob instanceof SparkJob) {

                try {
                    ((SparkJob) sparkJob).execute(jobArgs);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            break;
        }

    }


}
