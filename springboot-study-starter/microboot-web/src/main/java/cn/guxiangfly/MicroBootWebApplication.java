package cn.guxiangfly;

import cn.guxiangfly.study.starter.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroBootWebApplication  implements CommandLineRunner {
    @Autowired
    private Dept dept;

    public static void main(String[] args) {
        SpringApplication.run(MicroBootWebApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("debug");
        System.out.println(dept);
    }
}



