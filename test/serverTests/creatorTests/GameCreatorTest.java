package serverTests.creatorTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import gamelogic.board.SixPointedStar;
import gamelogic.creator.GameCreator;
import gamelogic.exception.WrongBoardTypeException;
import gamelogic.exception.WrongMovementTypeException;
import gamelogic.game.Game;
import gamelogic.movement.MainMovement;

import static junit.framework.Assert.*;

public class GameCreatorTest {

    private String boardType;
    private String moveType;
    private GameCreator creator;

    @After
    public void tearDown(){
//        Server.getInstance().getGames().clear();
    }

    @Before
    public void setUp(){
        creator = new GameCreator();
    }

    @Test(expected = WrongBoardTypeException.class)
    public void testWrongBoardTypeException() throws WrongMovementTypeException, WrongBoardTypeException {
        moveType = "\"main\"";
        creator.createGame("something wrong", moveType, 2, 10);
    }

    @Test(expected = WrongMovementTypeException.class)
    public void testWrongMovementTypeException() throws WrongMovementTypeException, WrongBoardTypeException {
        boardType = "\"SixPointedStar\"";
        creator.createGame(boardType, "something wrong", 2, 10);
    }

    @Test
    public void testCreateGame() throws WrongMovementTypeException, WrongBoardTypeException {
        boardType = "\"SixPointedStar\"";
        moveType = "\"main\"";
        Game game = creator.createGame(boardType, moveType, 2, 10);
        assertTrue(1 == game.getGameId() &&
                game.getBoard() instanceof SixPointedStar &&
                game.getPlayers().size() == 0 &&
                game.getMovement() instanceof MainMovement &&
                game.getNumberOfPawns() == 10 &&
                game.getNumberOfPlayers() == 2);
    }
    @Test
    public void testIsCreatedGameAddedToServer() throws WrongMovementTypeException, WrongBoardTypeException {
        boardType = "\"SixPointedStar\"";
        moveType = "\"main\"";
        Game g = creator.createGame(boardType, moveType, 2, 10);
//        assertTrue(Server.getInstance().getGames().contains(g));
    }

}
