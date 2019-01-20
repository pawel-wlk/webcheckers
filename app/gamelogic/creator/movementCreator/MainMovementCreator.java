package gamelogic.creator.movementCreator;

import gamelogic.movement.MainMovement;
import gamelogic.movement.Movement;

public class MainMovementCreator implements MovementCreator{
    @Override
    public Movement createMovement() {
        return new MainMovement();
    }
}
