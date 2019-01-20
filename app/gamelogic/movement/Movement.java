package gamelogic.movement;


import gamelogic.exception.ForbiddenMoveException;
import gamelogic.field.Field;
import gamelogic.field.Pawn;


public interface Movement {

  void move(Pawn pawn, Field target) throws ForbiddenMoveException;
  boolean checkMove(Pawn pawn, Field target);
}
