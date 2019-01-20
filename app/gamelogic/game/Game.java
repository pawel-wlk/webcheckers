package gamelogic.game;

import gamelogic.board.Board;
import gamelogic.movement.Movement;
import gamelogic.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
  private int gameId;
  private Board board;
  private Movement movement;
  private GameController controller;
  private List<Player> players;
  private int numberOfPlayers;
  private int numberOfPawns;

  public Game(){
    this. controller = new GameController(this);
    this.players = new ArrayList<>();

  }

  public Game(int gameId, Board board, Movement movement, GameController controller, List<Player> players) {
    this.gameId = gameId;
    this.board = board;
    this.movement = movement;
    this.controller = controller;
    this.players = players;
  }

  public int getGameId() {
    return gameId;
  }

  public Board getBoard() {
    return board;
  }

  public Movement getMovement() {
    return movement;
  }

  public GameController getController() {
    return controller;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public void setMovement(Movement movement) {
    this.movement = movement;
  }

  public int getNumberOfPlayers() {
    return numberOfPlayers;
  }

  public void setNumberOfPlayers(int numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
  }

  public int getNumberOfPawns() {
    return numberOfPawns;
  }

  public void setNumberOfPawns(int numberOfPawns) {
    this.numberOfPawns = numberOfPawns;
  }

}
