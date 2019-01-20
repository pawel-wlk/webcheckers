package serverTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import gamelogic.creator.GameCreator;
import gamelogic.exception.*;
import gamelogic.field.EmptyField;
import gamelogic.field.NoField;
import gamelogic.field.Pawn;
import gamelogic.game.Game;
import gamelogic.player.Player;

import static org.junit.Assert.*;


public class MoveTest {
  private Game testedGame;
  private GameCreator gameCreator;
  private Player p1;
  private Player p2;


  @Before
  public void prepareGameToTest() throws WrongMovementTypeException, WrongBoardTypeException,
      BoardSideUsedException, GameFullException, ColorUsedException {
    this.gameCreator = new GameCreator();
    this.testedGame = gameCreator.createGame("\"SixPointedStar\"", "\"main\"",
        2, 10);

    p1 = this.testedGame.getController().addPlayer("\"TOP\"", "\"RED\"");
    p2 = this.testedGame.getController().addPlayer("\"BOTTOM\"", "\"GREEN\"");

    this.testedGame.getController().startGame();
  }

  @Test(expected = ForbiddenActionException.class)
  public void testCanNotMoveEnemyPawn() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn enemyPawn = p2.getPawns().get(0);
    this.testedGame.getBoard().setOneField(new NoField(enemyPawn.getX()+1, enemyPawn.getY()));

    this.testedGame.getController().move(p1.getId(), enemyPawn.getX()+1, enemyPawn.getY(),
        enemyPawn.getX(), enemyPawn.getY());
  }

  @Test(expected = ForbiddenMoveException.class)
  public void testCanNotMoveToEnemyPawnLocation() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(0);
    Pawn enemyPawn = new Pawn(friendlyPawn.getX()+1, friendlyPawn.getY());
    enemyPawn.setOwner(p2);
    this.testedGame.getBoard().setOneField(enemyPawn);
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        enemyPawn.getX(), enemyPawn.getY());
  }
  @Test(expected = ForbiddenMoveException.class)
  public void testCanNotMoveToNoField() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(0);
    this.testedGame.getBoard().setOneField(new NoField(friendlyPawn.getX()+1, friendlyPawn.getY()));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()+1, friendlyPawn.getY());
  }

  @Test
  public void testCanMoveOneFieldHorizontallyRight() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(0);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()+1, friendlyPawn.getY()));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()+1, friendlyPawn.getY());
  }

  @Test
  public void testCanMoveOneFieldHorizontallyLeft() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(0);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()-1, friendlyPawn.getY()));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()-1, friendlyPawn.getY());
  }
  @Test
  public void testCanMoveOneFieldBackwards() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(0);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX(), friendlyPawn.getY()+1));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX(), friendlyPawn.getY()+1);
  }
  @Test
  public void testCanMoveOneFieldForward() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5); // cannot take oth because we would get out of bounds of the array
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX(), friendlyPawn.getY() - 1));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX(), friendlyPawn.getY() - 1);
  }
  @Test
  public void testCanMoveOneFieldDiagonallyForward() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()-1, friendlyPawn.getY() - 1));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()-1, friendlyPawn.getY() - 1);
  }
  @Test
  public void testCanMoveOneFieldDiagonallyBackwards() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()+1, friendlyPawn.getY() + 1));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()+1, friendlyPawn.getY() + 1);
  }

  @Test
  public void testCanJumpHorizontallyRight() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    Pawn jumpedOver = new Pawn(friendlyPawn.getX()+1, friendlyPawn.getY());
    this.testedGame.getBoard().setOneField(jumpedOver);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()+2, friendlyPawn.getY()));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()+2, friendlyPawn.getY());

  }

  @Test
  public void testCanJumpHorizontallyLeft() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    Pawn jumpedOver = new Pawn(friendlyPawn.getX()-1, friendlyPawn.getY());
    this.testedGame.getBoard().setOneField(jumpedOver);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()-2, friendlyPawn.getY()));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()-2, friendlyPawn.getY());

  }

  @Test
  public void testCanJumpForwards() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    Pawn jumpedOver = new Pawn(friendlyPawn.getX(), friendlyPawn.getY()-1);
    this.testedGame.getBoard().setOneField(jumpedOver);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX(), friendlyPawn.getY()-2));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX(), friendlyPawn.getY()-2);

  }
  @Test
  public void testCanJumpBackwards() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    Pawn jumpedOver = new Pawn(friendlyPawn.getX(), friendlyPawn.getY()+1);
    this.testedGame.getBoard().setOneField(jumpedOver);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX(), friendlyPawn.getY()+2));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX(), friendlyPawn.getY()+2);
  }
  @Test
  public void testCanJumpDiagonallyForwards() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    Pawn jumpedOver = new Pawn(friendlyPawn.getX()-1, friendlyPawn.getY()-1);
    this.testedGame.getBoard().setOneField(jumpedOver);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()-2, friendlyPawn.getY()-2));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()-2, friendlyPawn.getY()-2);
  }
  @Test
  public void testCanJumpDiagonallyBackwards() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    Pawn jumpedOver = new Pawn(friendlyPawn.getX()-1, friendlyPawn.getY()-1);
    this.testedGame.getBoard().setOneField(jumpedOver);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()+2, friendlyPawn.getY()+2));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()+2, friendlyPawn.getY()+2);
  }

  @Test
  public void testCanMultiJump() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    Pawn jumpedOver1 = new Pawn(friendlyPawn.getX(), friendlyPawn.getY()-1);
    Pawn jumpedOver2 = new Pawn(friendlyPawn.getX(), friendlyPawn.getY()-3);
    this.testedGame.getBoard().setOneField(friendlyPawn);
    this.testedGame.getBoard().setOneField(jumpedOver1);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX(), friendlyPawn.getY()-2));


    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX(), friendlyPawn.getY()-2);
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX(), friendlyPawn.getY()+2);
  }
  @Test(expected = ForbiddenMoveException.class)
  public void testCannotMoveWithTwoPawnsInOneRound() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(5);
    Pawn anotherPawn = p1.getPawns().get(4);
    Pawn jumpedOver = new Pawn(friendlyPawn.getX()-1, friendlyPawn.getY()-1);
    this.testedGame.getBoard().setOneField(jumpedOver);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()+2, friendlyPawn.getY()+2));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()+2, friendlyPawn.getY()+2);
    this.testedGame.getBoard().setOneField(new EmptyField(anotherPawn.getX()+1, anotherPawn.getY()));
    this.testedGame.getController().move(p1.getId(), anotherPawn.getX(), anotherPawn.getY(),
        anotherPawn.getX()+1, anotherPawn.getY());
  }
  @Test(expected = ForbiddenActionException.class)
  public void testCannotMoveAfterSingularMove() throws ForbiddenMoveException, ForbiddenActionException {
    Pawn friendlyPawn = p1.getPawns().get(0);
    this.testedGame.getBoard().setOneField(new EmptyField(friendlyPawn.getX()+1, friendlyPawn.getY()));
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()+1, friendlyPawn.getY());
    this.testedGame.getController().move(p1.getId(), friendlyPawn.getX(), friendlyPawn.getY(),
        friendlyPawn.getX()-1, friendlyPawn.getY());
  }




  @After
  public void cleanup() {
    this.testedGame.getController().endGame();
  }
}
