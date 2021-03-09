package cn.guxiangfly.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author guxiang02
 * @Date 2021/3/9
 **/
@Data
public class Teacher {

    public String name;

    public Teacher(String name) {
        this.name = name;
    }
}
