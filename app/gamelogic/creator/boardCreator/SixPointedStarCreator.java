package gamelogic.creator.boardCreator;

import gamelogic.board.Board;
import gamelogic.board.SixPointedStar;
import gamelogic.creator.fieldCreator.EmptyFieldCreator;
import gamelogic.creator.fieldCreator.NoFieldCreator;

public class SixPointedStarCreator extends BoardCreator {
    public Board createBoard() {
        SixPointedStar star = new SixPointedStar();
        fieldCreator = new NoFieldCreator();
        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 17 ; j++){
                star.setOneField(fieldCreator.createField(i, j));
            }
        }
        fieldCreator = new EmptyFieldCreator();
        for(int j = 0; j < 13; j ++){
            for(int i=0; i <= j; i++){
                star.setOneField(fieldCreator.createField(i+4, j));
            }
        }

        //LEFT-TOP
        for(int i=0; i<4; i++){
            for(int j=3; j>=i; j--){
                star.setOneField(fieldCreator.createField(j, 4 + i));
            }
        }

        //RIGHT-TOP
        for(int i=0; i<4; i++){
            for(int j=3; j>=i; j--){
                star.setOneField(fieldCreator.createField(9 + j, 4 + i));
            }
        }

        //BOTTOM
        for(int i=0; i<4; i++){
            for(int j=3; j>=i; j--){
                star.setOneField(fieldCreator.createField(9 + j, 13 + i));
            }
        }

        /* //done in setOneField
        //set created board for all fields
        for(int i=0; i<17; i++){
            for(int j=0; j<17; j++){
                star.getOneField(i,j).setBoard(star);
            }
        }*/

        return star;
    }
}
