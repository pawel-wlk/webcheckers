package gamelogic.creator.fieldCreator;

import gamelogic.field.Field;
import gamelogic.field.NoField;

public class NoFieldCreator implements FieldCreator {
    public Field createField(int x, int y) {
        return new NoField(x,y);
    }
}
