package cn.guxiangfly.hanshunping.state;

public class DispenseOutState extends State {

    RaffleActivity activity;

    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deductMoney() {

    }

    @Override
    public boolean raffle() {
        return false;
    }

    @Override
    public void dispensePrice() {

    }
}