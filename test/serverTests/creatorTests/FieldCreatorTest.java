package serverTests.creatorTests;

import org.junit.After;
import org.junit.Test;
import gamelogic.creator.fieldCreator.EmptyFieldCreator;
import gamelogic.creator.fieldCreator.FieldCreator;
import gamelogic.creator.fieldCreator.NoFieldCreator;
import gamelogic.creator.fieldCreator.PawnCreator;
import gamelogic.field.EmptyField;
import gamelogic.field.Field;
import gamelogic.field.NoField;
import gamelogic.field.Pawn;

import static junit.framework.Assert.assertTrue;

public class FieldCreatorTest {
    private Field actual;
    private FieldCreator creator;

    @After
    public void tearDown() throws Exception{
        actual = null;
        creator = null;
    }

    @Test
    public void testNoFieldCreator(){
        creator = new NoFieldCreator();
        actual = creator.createField(5, 3);
        assertTrue(actual instanceof NoField &&
                5 == actual.getX() &&
                3 == actual.getY()
        );
    }

    @Test
    public void testEmptyFieldCreator(){
        creator = new EmptyFieldCreator();
        actual = creator.createField(4, 5);
        assertTrue(actual instanceof EmptyField &&
                4 == actual.getX() &&
                5 == actual.getY()
        );
    }

    @Test
    public void testPawnCreator(){
        creator = new PawnCreator();
        actual = creator.createField(1, 2);
        assertTrue(actual instanceof Pawn &&
                1 == actual.getX() &&
                2 == actual.getY()
        );
    }
}
