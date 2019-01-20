package gamelogic.field;

import gamelogic.board.Board;

public abstract class Field {
    private int x;
    private int y;
    private Board board;

    public Field(){
        this.x = 0;
        this.y = 0;
    }
    public Field(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "Field{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
