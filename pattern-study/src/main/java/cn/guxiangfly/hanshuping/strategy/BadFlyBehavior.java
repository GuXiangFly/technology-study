package cn.guxiangfly.hanshuping.strategy;

public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞翔技术");
    }
}

