package cn.guxiangfly.hanshunping.state;

import java.util.Random;

public class CanRaffleState extends State {


    RaffleActivity activity;

    public CanRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deductMoney() {
        System.out.println("已经扣取积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("正在抽奖");
        Random random = new Random();
        int i = random.nextInt(10);
        if (i == 0){
            System.out.println("中奖了");
            activity.setState(activity.getDispenseState());
            return true;
        }else {
            System.out.println("没中奖");
            activity.setState(activity.getNoRaffleState());
            return false;
        }
    }

    @Override
    public void dispensePrice() {
        System.out.println("没中奖，不能发放奖品");
    }
}
