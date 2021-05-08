package cn.guxiangfly.test1;

import cn.guxiangfly.pojo.Teacher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author guxiang02
 * @Date 2021/3/9
 **/
public class ClassPathXMLTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Teacher bean = applicationContext.getBean(Teacher.class);

       System.out.println( bean.getName());
    }
}
