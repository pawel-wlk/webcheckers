package gamelogic.creator;

import gamelogic.creator.boardCreator.BoardCreator;
import gamelogic.creator.boardCreator.SixPointedStarCreator;
import gamelogic.creator.movementCreator.MainMovementCreator;
import gamelogic.creator.movementCreator.MovementCreator;
import gamelogic.exception.WrongBoardTypeException;
import gamelogic.exception.WrongMovementTypeException;
import gamelogic.game.Game;

public class GameCreator {
    private MovementCreator movementCreator;
    private BoardCreator boardCreator;

    public Game createGame(String boardType, String movementType,
                           int numberOfPlayers, int numberOfPawns) throws WrongMovementTypeException, WrongBoardTypeException {
        Game game = new Game();

        if(boardType.equals("\"SixPointedStar\"")){
            boardCreator = new SixPointedStarCreator();
        }else{
            throw new WrongBoardTypeException();
        }

        if(movementType.equals("\"main\"")){
            movementCreator = new MainMovementCreator();
        }else{
            throw new WrongMovementTypeException();
        }

        game.setBoard(boardCreator.createBoard());
        game.setMovement(movementCreator.createMovement());
        game.setNumberOfPlayers(numberOfPlayers);
        game.setNumberOfPawns(numberOfPawns);

//        int numberOfGames = Server.getInstance().getGames().size();
//        if(numberOfGames == 0){
//            game.setGameId(1);
//        }else{
//            game.setGameId(Server.getInstance().getGames().get(numberOfGames-1).getGameId() + 1);
//        }
//
//        Server.getInstance().getGames().add(game);

        return game;
    }
}
