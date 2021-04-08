package cn.guxiangfly.spark;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public abstract class SparkJob implements Serializable {

    protected SparkJob() {
    }

    /**
     * 执行spark任务
     */
    public void execute(String[] args) throws Exception {
    }
}
