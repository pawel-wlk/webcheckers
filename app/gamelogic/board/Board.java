package gamelogic.board;

import com.google.gson.JsonArray;
import gamelogic.field.Field;
import gamelogic.field.Pawn;
import gamelogic.player.Player;


public abstract class Board {

    private String type;
    private Field[][] fields;

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    public void setOneField(Field field){
        field.setBoard(this);
        this.fields[field.getX()][field.getY()] = field;
    }

    public Field getOneField(int x, int y) throws ArrayIndexOutOfBoundsException{
        if(x > sizeX() || y > sizeY()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.fields[x][y];
    }

    public abstract int sizeY();
    public abstract int sizeX();
    public abstract void setPawns(Player player);
    public abstract boolean checkWin(Player player);
    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String fieldsToString(){
        JsonArray parentJsonArray = new JsonArray();

        for (int i=0; i<sizeY(); i++){
            JsonArray childJsonArray = new JsonArray();
            for (int j =0; j<sizeX(); j++){
//                childJsonArray.add(fields[i][j].toString());
            }
            parentJsonArray.add(childJsonArray);
        }

        return parentJsonArray.toString();
    }

    public boolean isPawn(int x, int y) throws ArrayIndexOutOfBoundsException{
       /* if(x >= sizeX() || y >= sizeY()){
            throw new ArrayIndexOutOfBoundsException();
        }*/
        return getOneField(x,y) instanceof Pawn;
    }

}
