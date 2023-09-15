package cn.guxiangfly.hanshunping.state;

public class RaffleActivity {

    State state;



    public State getState(State state) {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getCanRaffleState(){
        return new CanRaffleState(this);
    }

    public State getNoRaffleState(){
        return new NoRaffleState(this);
    }

    public State getDispenseState(){
        return new DispenseState(this);
    }
}
