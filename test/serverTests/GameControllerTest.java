package serverTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import gamelogic.board.BoardSide;
import gamelogic.board.SixPointedStarSide;
import gamelogic.creator.GameCreator;
import gamelogic.exception.*;
import gamelogic.field.Field;
import gamelogic.field.Pawn;
import gamelogic.game.Game;
import gamelogic.player.Bot;
import gamelogic.player.Color;
import gamelogic.player.MoveToken;
import gamelogic.player.Player;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GameControllerTest {

    private Game game;
    private GameCreator creator;

    @Before
    public void prepareTestingGame() throws WrongMovementTypeException, WrongBoardTypeException {
        this.creator = new GameCreator();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                2, 10);
    }

    @After
    public void tearDown() {
//        if (Server.getInstance().getGames().contains(game)) {
//            this.game.getController().endGame();
//        }
    }

    //block of startGame() tests
    @Test
    public void testStartGameWithoutBots() throws BoardSideUsedException, GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"RED\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"BLUE\"");
        this.game.getController().startGame();
        assertTrue(MoveToken.ALLOW.equals(game.getController().getCurrentTurnPlayer().getMoveToken()) &&
                game.getController().isStarted() &&
                !(game.getPlayers().get(0) instanceof Bot) &&
                !(game.getPlayers().get(1) instanceof Bot)
        );
    }

    @Test
    public void testStartNonFullGame() throws BoardSideUsedException, GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"RED\"");
        this.game.getController().startGame();
        assertTrue(MoveToken.ALLOW.equals(game.getController().getCurrentTurnPlayer().getMoveToken()) &&
                game.getController().isStarted() &&
                !(game.getPlayers().get(0) instanceof Bot) &&
                game.getPlayers().get(1) instanceof Bot
        );

    }

    //endGame() method test
    @Test
    public void endGame() {
        this.game.getController().endGame();
        assertFalse(this.game.getController().isStarted()
//                || Server.getInstance().getGames().contains(game)
        );
    }

    //block of endTurn() tests
    @Test
    public void testNormalEndTurn() throws BoardSideUsedException, GameFullException,
            ColorUsedException, ForbiddenActionException {
        this.game.getController().addPlayer("\"TOP\"", "\"RED\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"BLUE\"");
        this.game.getController().startGame();
        Player first = this.game.getController().getCurrentTurnPlayer();
        this.game.getController().endTurn(first.getId());
        Player second = this.game.getController().getCurrentTurnPlayer();
        this.game.getController().endTurn(second.getId());
        assertTrue(!first.equals(second) &&
                first.equals(this.game.getController().getCurrentTurnPlayer())
        );
    }

    @Test
    public void testLastEndTurn() throws BoardSideUsedException, GameFullException,
            ColorUsedException, ForbiddenActionException {
        this.game.getController().addPlayer("\"TOP\"", "\"RED\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"BLUE\"");
        this.game.getController().startGame();
        Player prev = this.game.getController().getCurrentTurnPlayer();
        this.game.getController().endTurn(prev.getId());
        prev.setMoveToken(MoveToken.WINNER);
        Player curr = this.game.getController().getCurrentTurnPlayer();
        this.game.getController().endTurn(curr.getId());
        assertFalse(this.game.getController().isStarted());
    }

    @Test(expected = ForbiddenActionException.class)
    public void testWrongPlayerEndTurn() throws ForbiddenActionException, BoardSideUsedException,
            GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"RED\"");
        this.game.getController().startGame();
        this.game.getController().endTurn(
                this.game.getController().getCurrentTurnPlayer().getId() + 1);
    }

    //block of addPlayer() tests
    @Test
    public void testAddPlayer() throws BoardSideUsedException, GameFullException, ColorUsedException {
        Player p = this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        List<Field> list = SixPointedStarSide.TOP.getArea(game.getBoard());
        boolean isTheSame = false;
        for (Pawn pawn : p.getPawns()) {
            isTheSame = list.contains(pawn);
            if (!isTheSame) {
                break;
            }
        }
        assertTrue(isTheSame && this.game.getPlayers().contains(p));
    }

    @Test(expected = BoardSideUsedException.class)
    public void testChoosingUsedSide() throws BoardSideUsedException, GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"TOP\"", "\"RED\"");
    }

    @Test(expected = ColorUsedException.class)
    public void testChoosingUsedColor() throws BoardSideUsedException, GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"BLUE\"");
    }

    @Test(expected = GameFullException.class)
    public void testTooManyPlayersInGame() throws BoardSideUsedException, GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"RED\"");
        this.game.getController().addPlayer("\"LEFT_TOP\"", "\"PURPLE\"");
    }


    // test getIdColorMap() method
    @Test
    public void testGetIdColorMap() throws BoardSideUsedException, GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"RED\"");

        Map<Integer, String> testMap = this.game.getController().getIdColorMap();
        assertTrue(testMap.size() == 2 &&
                testMap.containsKey(0) &&
                "BLUE".equals(testMap.get(0)) &&
                testMap.containsKey(1) &&
                "RED".equals(testMap.get(1))
        );
    }

    //block of methods giving free colors or sides
    @Test
    public void testGetEnabledColorsForNonFirstPlayer() throws BoardSideUsedException, GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"RED\"");
        List<Color> testColors = this.game.getController().getEnabledColors();
        assertTrue(testColors.size() == 4 &&
                testColors.contains(Color.BLACK) &&
                testColors.contains(Color.GREEN) &&
                testColors.contains(Color.YELLOW) &&
                testColors.contains(Color.PURPLE)
        );
    }

    @Test
    public void testGetEnabledColorsForFirstPlayer(){
        List<Color> testColors = this.game.getController().getEnabledColors();
        assertEquals(6, testColors.size());
    }

    @Test
    public void testGetEnabledSidesForFirstPlayer(){
        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertEquals(6, testSides.size());
    }

    @Test
    public void testGetEnabledSidesForNonFirstFromSixPlayers() throws Exception {
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                6, 10);
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"RED\"");
        this.game.getController().addPlayer("\"LEFT_TOP\"", "\"PURPLE\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 3 &&
                testSides.contains(SixPointedStarSide.RIGHT_TOP) &&
                testSides.contains(SixPointedStarSide.RIGHT_BOTTOM) &&
                testSides.contains(SixPointedStarSide.LEFT_BOTTOM)
        );
    }

    @Test
    public void testGetEnabledSidesForSecondFromTwoPlayers() throws BoardSideUsedException, GameFullException, ColorUsedException {
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 1 &&
                testSides.contains(SixPointedStarSide.BOTTOM)
        );
    }

    @Test
    public void testGetEnabledSidesForSecondFromThreePlayers1TOP() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                3, 10);
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 2 &&
                testSides.contains(SixPointedStarSide.RIGHT_BOTTOM) &&
                testSides.contains(SixPointedStarSide.LEFT_BOTTOM));
    }

    @Test
    public void testGetEnabledSidesForSecondFromThreePlayers1BOTTOM() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                3, 10);
        this.game.getController().addPlayer("\"BOTTOM\"", "\"BLUE\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 2 &&
                testSides.contains(SixPointedStarSide.RIGHT_TOP) &&
                testSides.contains(SixPointedStarSide.LEFT_TOP));
    }

    @Test
    public void testGetEnabledSidesForSecondFromThreePlayers1LEFT_TOP() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                3, 10);
        this.game.getController().addPlayer("\"LEFT_TOP\"", "\"BLUE\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 2 &&
                testSides.contains(SixPointedStarSide.RIGHT_TOP) &&
                testSides.contains(SixPointedStarSide.BOTTOM));
    }

    @Test
    public void testGetEnabledSidesForSecondFromThreePlayers1RIGHT_TOP() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                3, 10);
        this.game.getController().addPlayer("\"RIGHT_TOP\"", "\"BLUE\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 2 &&
                testSides.contains(SixPointedStarSide.BOTTOM) &&
                testSides.contains(SixPointedStarSide.LEFT_TOP));
    }

    @Test
    public void testGetEnabledSidesForSecondFromThreePlayers1LEFT_BOTTOM() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                3, 10);
        this.game.getController().addPlayer("\"LEFT_BOTTOM\"", "\"BLUE\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 2 &&
                testSides.contains(SixPointedStarSide.RIGHT_BOTTOM) &&
                testSides.contains(SixPointedStarSide.TOP));
    }

    @Test
    public void testGetEnabledSidesForSecondFromThreePlayers1RIGHT_BOTTOM() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                3, 10);
        this.game.getController().addPlayer("\"RIGHT_BOTTOM\"", "\"BLUE\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 2 &&
                testSides.contains(SixPointedStarSide.TOP) &&
                testSides.contains(SixPointedStarSide.LEFT_BOTTOM));
    }

    @Test
    public void testGetEnabledSidesForLastFromThreePlayers() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                3, 10);
        this.game.getController().addPlayer("\"RIGHT_BOTTOM\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"LEFT_BOTTOM\"", "\"RED\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 1 &&
                testSides.contains(SixPointedStarSide.TOP)
        );
    }

    @Test
    public void testGetEnabledSidesForSecondFromFourPlayers() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                4, 10);
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 5 &&
                testSides.contains(SixPointedStarSide.RIGHT_TOP) &&
                testSides.contains(SixPointedStarSide.RIGHT_TOP) &&
                testSides.contains(SixPointedStarSide.LEFT_BOTTOM) &&
                testSides.contains(SixPointedStarSide.RIGHT_BOTTOM) &&
                testSides.contains(SixPointedStarSide.BOTTOM)
        );
    }

    @Test
    public void testGetEnabledSidesForThirdFromFourPlayersFirstBothNonOpposite() throws Exception {
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                4, 10);
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"LEFT_TOP\"", "\"RED\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 2 &&
                testSides.contains(SixPointedStarSide.RIGHT_BOTTOM) &&
                testSides.contains(SixPointedStarSide.BOTTOM)
        );
    }

    @Test
    public void testGetEnabledSidesForThirdFromFourPlayersFirstBothOpposite() throws Exception {
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                4, 10);
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"RED\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 4 &&
                testSides.contains(SixPointedStarSide.RIGHT_BOTTOM) &&
                testSides.contains(SixPointedStarSide.LEFT_BOTTOM) &&
                testSides.contains(SixPointedStarSide.RIGHT_TOP) &&
                testSides.contains(SixPointedStarSide.LEFT_TOP)
        );
    }

    @Test
    public void testGetEnabledSidesForLastFromFourPlayers() throws Exception{
        this.game.getController().endGame();
        this.game = creator.createGame("\"SixPointedStar\"",
                "\"main\"",
                4, 10);
        this.game.getController().addPlayer("\"TOP\"", "\"BLUE\"");
        this.game.getController().addPlayer("\"BOTTOM\"", "\"RED\"");
        this.game.getController().addPlayer("\"LEFT_BOTTOM\"", "\"GREEN\"");

        List<BoardSide> testSides = this.game.getController().getEnabledSides();
        assertTrue(testSides.size() == 1 &&
                testSides.contains(SixPointedStarSide.RIGHT_TOP)
        );
    }

}