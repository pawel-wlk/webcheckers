package gamelogic.creator.fieldCreator;

import gamelogic.field.EmptyField;
import gamelogic.field.Field;

public class EmptyFieldCreator implements FieldCreator {

    public Field createField(int x, int y) {
        return new EmptyField(x,y);
    }
}
