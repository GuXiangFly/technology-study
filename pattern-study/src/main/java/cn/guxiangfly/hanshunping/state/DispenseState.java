package cn.guxiangfly.hanshunping.state;

public class DispenseState extends State {

    RaffleActivity activity;

    public DispenseState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deductMoney() {
        System.out.println("已经扣取积分");
    }

    @Override
    public boolean raffle() {
        return false;
    }

    @Override
    public void dispensePrice() {

    }
}