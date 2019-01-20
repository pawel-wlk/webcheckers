package gamelogic.field;


import com.google.gson.JsonObject;
import gamelogic.player.Player;

public class Pawn extends Field {
    private Player owner;

    public Pawn(int x, int y){
        super(x,y);
    }
    public void setOwner(Player player){
        this.owner = player;
    }
    public Player getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "Pawn");
        obj.addProperty("x", getX());
        obj.addProperty("y", getY());
        obj.addProperty("owner", owner.getId());
        obj.addProperty("color", owner.getColor().toString());

        return obj.toString();
    }
}
