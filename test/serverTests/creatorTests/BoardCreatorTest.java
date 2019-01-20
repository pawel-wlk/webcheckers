package serverTests.creatorTests;


import org.junit.After;
import org.junit.Test;
import gamelogic.board.Board;
import gamelogic.creator.boardCreator.BoardCreator;
import gamelogic.creator.boardCreator.SixPointedStarCreator;
import gamelogic.field.EmptyField;
import gamelogic.field.NoField;
import gamelogic.field.Pawn;

import static junit.framework.Assert.assertTrue;

public class BoardCreatorTest {

    private Board board;
    private BoardCreator creator;

    @After
    public void tearDown(){
        board = null;
        creator = null;
    }

    @Test
    public void testSixPointedStarBoardCreator(){
        creator = new SixPointedStarCreator();
        board = creator.createBoard();
        int[] fields_no = howManyFields();
        assertTrue(17 == board.sizeY() &&
                17 == board.sizeX() &&
                fields_no[2] == 0 &&
                fields_no[1] == 121 &&
                fields_no[0] == 168
        );
    }


    private int[] howManyFields(){
        //indexes : 0 - noFields, 1 - emptyFields, 2 - pawns
        int[] result = new int[3];
        for(int i=0; i<board.sizeX(); i++){
            for(int j=0; j<board.sizeY(); j++){
                if(board.getOneField(i,j) instanceof NoField){
                    result[0]++;
                }else if(board.getOneField(i, j) instanceof EmptyField){
                    result[1]++;
                }else if(board.getOneField(i, j) instanceof Pawn){
                    result[2]++;
                }
            }
        }

        return result;
    }
}
