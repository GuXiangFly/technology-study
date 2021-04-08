package cn.guxiangfly.service;

import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Author guxiang02
 * @Date 2021/3/15
 **/
@Service
public class SayService implements Serializable {

    public void readAndSay(String message){
        System.out.println("readAndSay:" + message);
    }
}
