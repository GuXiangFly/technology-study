package cn.guxiangfly.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @Author guxiang02
 * @Date 2021/3/9
 **/
public class Teacher implements BeanNameAware, EnvironmentAware {


    public String beanName;

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }


    public String environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment.toString();
    }
}
