package gamelogic.player;

import gamelogic.board.BoardSide;
import gamelogic.field.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private int id;
  private List<Pawn> pawns;
  private BoardSide startingSide;
  private Color color;

  //0 - can't move, 1 - can move, 2 - can jump only, 3 - has won already
  private MoveToken moveToken;
  private Pawn lastMoved;

  public Player() {
  }
  public Player(BoardSide side, Color color) {
    this.pawns = new ArrayList<>();
    this.startingSide = side;
    this.color = color;
    this.moveToken = MoveToken.FORBID;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public MoveToken getMoveToken() {
    return moveToken;
  }

  public void setMoveToken(MoveToken moveToken) {
    this.moveToken = moveToken;
  }

  public BoardSide getStartingSide() {
    return startingSide;
  }

  public List<Pawn> getPawns() {
    return pawns;
  }

  public Color getColor() {
    return color;
  }

  public void setOnePawn(Pawn pawn) {
    this.pawns.add(pawn);
    pawn.setOwner(this);
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Pawn getLastMoved() {
    return lastMoved;
  }

  public void setLastMoved(Pawn lastMoved) {
    this.lastMoved = lastMoved;
  }
}
