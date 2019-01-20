package gamelogic.field;

import com.google.gson.JsonObject;

public class NoField extends Field {
    public NoField(int x, int y){
        super(x,y);
    }

    @Override
    public String toString() {

        JsonObject obj = new JsonObject();
        obj.addProperty("type", "NoField");
        obj.addProperty("x", getX());
        obj.addProperty("y", getY());
        obj.addProperty("color", "null");

        return obj.toString();
    }
}
