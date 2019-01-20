package serverTests.creatorTests;

import org.junit.After;
import org.junit.Test;
import gamelogic.creator.movementCreator.MainMovementCreator;
import gamelogic.creator.movementCreator.MovementCreator;
import gamelogic.movement.MainMovement;
import gamelogic.movement.Movement;

import static org.junit.Assert.*;

public class MovementCreatorTest {
    private Movement move;
    private MovementCreator creator;

    @After
    public void tearDown(){
        move = null;
        creator = null;
    }

    @Test
    public void testMainMovementCreator(){
        creator = new MainMovementCreator();
        move = creator.createMovement();
        assertTrue(move instanceof MainMovement);
    }
}
