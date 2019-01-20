package serverTests;

import org.junit.Test;
import gamelogic.board.SixPointedStarSide;
import gamelogic.field.EmptyField;
import gamelogic.field.Field;
import gamelogic.field.NoField;
import gamelogic.field.Pawn;
import gamelogic.player.Color;
import gamelogic.player.Player;

import static org.junit.Assert.*;


public class FieldTest {
  @Test
  public void testEmptyFieldToString() {
    Field f = new EmptyField(0, 0);

    assertEquals("{\"type\":\"EmptyField\",\"x\":0,\"y\":0,\"color\":\"null\"}",
        f.toString());
  }
  @Test
  public void testNoFieldToString() {
    Field f = new NoField(0, 0);

    assertEquals("{\"type\":\"NoField\",\"x\":0,\"y\":0,\"color\":\"null\"}",
        f.toString());
  }
  @Test
  public void testPawnToString() {
    Pawn p = new Pawn(0, 0);

    p.setOwner(new Player(SixPointedStarSide.TOP, Color.BLACK));

    assertEquals("{\"type\":\"Pawn\",\"x\":0,\"y\":0,\"owner\":0,\"color\":\"BLACK\"}",
        p.toString());
  }
}
