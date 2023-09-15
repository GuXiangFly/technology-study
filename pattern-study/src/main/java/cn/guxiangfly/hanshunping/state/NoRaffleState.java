package cn.guxiangfly.hanshunping.state;

public class NoRaffleState extends State{

    RaffleActivity activity;

    public NoRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }


    @Override
    public void deductMoney() {
        System.out.println("扣除50积分成功，你可以抽奖了");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        return false;
    }

    @Override
    public void dispensePrice() {

    }
}
