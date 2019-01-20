package gamelogic.creator.fieldCreator;

import gamelogic.field.Field;
import gamelogic.field.Pawn;

public class PawnCreator implements FieldCreator {
    public Field createField(int x, int y) {
        return new Pawn(x,y);
    }



}
