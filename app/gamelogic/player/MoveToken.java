package gamelogic.player;

public enum MoveToken {

    FORBID(0), //can't move
    ALLOW(1), //allow to move
    ONLY_JUMP(2), //allow only to jump
    WINNER(3); //already win, can't move

    private int num;
    MoveToken(int num){
        this.num = num;
    }
    public int getNum(){
        return num;
    }
}
