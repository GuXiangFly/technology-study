package cn.guxiangfly.study.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "cn.guxiangfly.study.starter.dept")
public class Dept {
    private String deptno;
    private String dname;
    private String loc;

}
