package gamelogic.creator.boardCreator;

import gamelogic.board.Board;
import gamelogic.creator.fieldCreator.FieldCreator;

public abstract class BoardCreator {
    protected FieldCreator fieldCreator;

    public abstract Board createBoard();
}
