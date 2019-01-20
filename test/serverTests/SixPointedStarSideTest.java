package serverTests;

import org.junit.Test;
import gamelogic.board.Board;
import gamelogic.board.SixPointedStar;
import gamelogic.board.SixPointedStarSide;
import gamelogic.creator.boardCreator.BoardCreator;
import gamelogic.creator.boardCreator.SixPointedStarCreator;
import gamelogic.field.Field;

import static org.junit.Assert.*;


public class SixPointedStarSideTest {
  @Test
  public void testBottomOpposite() {
    assertEquals(SixPointedStarSide.TOP,
        SixPointedStarSide.BOTTOM.getOppositeSide());
  }
  @Test
  public void testLeftBottomOpposite() {
    assertEquals(SixPointedStarSide.RIGHT_TOP,
        SixPointedStarSide.LEFT_BOTTOM.getOppositeSide());
  }
  @Test
  public void testRightBottomOpposite() {
    assertEquals(SixPointedStarSide.LEFT_TOP,
        SixPointedStarSide.RIGHT_BOTTOM.getOppositeSide());
  }

  @Test
  public void testTopOpposite() {
    assertEquals(SixPointedStarSide.BOTTOM,
        SixPointedStarSide.TOP.getOppositeSide());
  }
  @Test
  public void testLeftTopOpposite() {
    assertEquals(SixPointedStarSide.RIGHT_BOTTOM,
        SixPointedStarSide.LEFT_TOP.getOppositeSide());
  }
  @Test
  public void testRightTopOpposite() {
    assertEquals(SixPointedStarSide.LEFT_BOTTOM,
        SixPointedStarSide.RIGHT_TOP.getOppositeSide());
  }

  @Test
  public void testBottomGetOppositeArea() {
    BoardCreator bc = new SixPointedStarCreator();
    Board board = bc.createBoard();
    assertEquals(SixPointedStarSide.BOTTOM.getArea(board),
        SixPointedStarSide.TOP.getOppositeArea((SixPointedStar)board));
  }
  @Test
  public void testLeftBottomGetOppositeArea() {
    BoardCreator bc = new SixPointedStarCreator();
    Board board = bc.createBoard();
    assertEquals(SixPointedStarSide.LEFT_BOTTOM.getArea(board),
        SixPointedStarSide.RIGHT_TOP.getOppositeArea((SixPointedStar)board));
  }
  @Test
  public void testRightBottomGetOppositeArea() {
    BoardCreator bc = new SixPointedStarCreator();
    Board board = bc.createBoard();
    assertEquals(SixPointedStarSide.RIGHT_BOTTOM.getArea(board),
        SixPointedStarSide.LEFT_TOP.getOppositeArea((SixPointedStar)board));
  }

  @Test
  public void testTopGetOppositeArea() {
    BoardCreator bc = new SixPointedStarCreator();
    Board board = bc.createBoard();
    assertEquals(SixPointedStarSide.TOP.getArea(board),
        SixPointedStarSide.BOTTOM.getOppositeArea((SixPointedStar)board));
  }
  @Test
  public void testLeftTopGetOppositeArea() {
    BoardCreator bc = new SixPointedStarCreator();
    Board board = bc.createBoard();
    assertEquals(SixPointedStarSide.LEFT_TOP.getArea(board),
        SixPointedStarSide.RIGHT_BOTTOM.getOppositeArea((SixPointedStar)board));
  }
  @Test
  public void testRightTopGetOppositeArea() {
    BoardCreator bc = new SixPointedStarCreator();
    Board board = bc.createBoard();
    assertEquals(SixPointedStarSide.RIGHT_TOP.getArea(board),
        SixPointedStarSide.LEFT_BOTTOM.getOppositeArea((SixPointedStar)board));
  }

  @Test
  public void testBottomGetFarthest() {
    Field field = SixPointedStarSide.BOTTOM.getFarthest();
    assertTrue(field.getX() == 12 && field.getY() == 16);
  }
  @Test
  public void testLeftBottomGetFarthest() {
    Field field = SixPointedStarSide.LEFT_BOTTOM.getFarthest();
    assertTrue(field.getX() == 4 && field.getY() == 12);
  }
  @Test
  public void testRightBottomGetFarthest() {
    Field field = SixPointedStarSide.RIGHT_BOTTOM.getFarthest();
    assertTrue(field.getX() == 16 && field.getY() == 12);
  }

  @Test
  public void testTopGetFarthest() {
    Field field = SixPointedStarSide.TOP.getFarthest();
    assertTrue(field.getX() == 4 && field.getY() == 0);
  }
  @Test
  public void testLeftTopGetFarthest() {
    Field field = SixPointedStarSide.LEFT_TOP.getFarthest();
    assertTrue(field.getX() == 0 && field.getY() == 4);
  }
  @Test
  public void testRightTopGetFarthest() {
    Field field = SixPointedStarSide.RIGHT_TOP.getFarthest();
    assertTrue(field.getX() == 12 && field.getY() == 4);
  }

}
