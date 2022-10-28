package cn.guxiangfly;

import cn.guxiangfly.study.starter.Dept;
import cn.guxiangfly.study.starter.GuxiangflyConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MicroBootWebApplication.class)
public class TestAutoConfig {

    @Autowired
    public Dept dept;

    @Autowired
    public List<String> books;

    @Test
    public void test() {
        System.out.println("debug");
        System.out.println(dept);

        System.out.println(books);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(GuxiangflyConfiguration.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.print("[name] "+ beanDefinitionName);
            System.out.println(" \t [type] "+ annotationConfigApplicationContext.getBean(beanDefinitionName).getClass().getName());
        }

    }

}

