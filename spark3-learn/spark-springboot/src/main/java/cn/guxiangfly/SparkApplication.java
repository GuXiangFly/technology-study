package cn.guxiangfly;

import cn.guxiangfly.spark.SparkJob;
import cn.guxiangfly.utils.SpringBootBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

/**
 * @Author guxiang02
 * @Date 2021/3/15
 **/

@SpringBootApplication
@Slf4j
public class SparkApplication implements CommandLineRunner {

    @Value("${mytest}")
    String mytest;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SparkApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    /**
     * 任务执行
     *
     * @param args 任务类路径集合，{@link #}分割类和参数
     */
    @Override
    public void run(String... args) throws Exception {

        System.out.println(mytest);
        String jobName = "cn.guxiangfly.job.TestJob";
        Class clazz = Class.forName(jobName);
        Object sparkJob = SpringBootBeanUtils.getBean(clazz);
        if (sparkJob instanceof SparkJob) {

            log.info("========>execute job={}", args);
            try {
                ((SparkJob) sparkJob).execute(args);
            } catch (Exception e) {
                log.error("========>execute job={} error!", args, e);
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * 执行销毁动作
     */
    @PreDestroy
    public void destroy() {
        // 强制发送 metric，避免任务结束但是 cat 还没有写入的问题
        log.info("========>SparkApplication destroy!");
    }

}
