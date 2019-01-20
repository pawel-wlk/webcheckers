package gamelogic.movement;

import gamelogic.board.Board;
import gamelogic.board.SixPointedStar;
import gamelogic.board.SixPointedStarSide;
import gamelogic.exception.ForbiddenMoveException;
import gamelogic.field.EmptyField;
import gamelogic.field.Field;
import gamelogic.field.Pawn;
import gamelogic.player.MoveToken;

import java.util.List;

public class MainMovement implements Movement {

    @Override
    public void move(Pawn pawn, Field target) throws ForbiddenMoveException {

        if (checkMove(pawn, target)) {
            if(checkJump(pawn, target)){
                pawn.getOwner().setMoveToken(MoveToken.ONLY_JUMP);
            } else {
                pawn.getOwner().setMoveToken(MoveToken.FORBID);
            }
            int pawnX = pawn.getX();
            int pawnY = pawn.getY();
            pawn.setX(target.getX());
            pawn.setY(target.getY());
            target.setX(pawnX);
            target.setY(pawnY);
            pawn.getOwner().setLastMoved(pawn);
            pawn.getBoard().setOneField(pawn);
            pawn.getBoard().setOneField(target);

        } else {
            throw new ForbiddenMoveException();
        }
    }

    @Override
    public boolean checkMove(Pawn pawn, Field target) {
        //first check if target is empty
        if (!(target instanceof EmptyField)) {
            return false;
        }

        if(pawn.getOwner().getMoveToken().getNum() == 0){
            return false;
        }
        //later check if board place allow to move
        if (!checkMoveForBoard(pawn.getBoard(), pawn, (EmptyField) target)) {
            return false;
        }

        return checkMoveOne(pawn, target)
                || checkJump(pawn, target) ;
    }

    private boolean checkMoveOne(Pawn pawn, Field target) {
        // this method only checks "atomic" moves

        if (pawn.getOwner().getMoveToken().getNum() != 1){
            return false;
        }

        // all following checks assume that we sue flipped cartesian coordinates (ys raise as we go down)
        //forward move
        if (target.getY() == pawn.getY() - 1) {
            return pawn.getX() - target.getX() == 0
                    || pawn.getX() - target.getX() == 1;
        }
        // backwards move
        if (target.getY() == pawn.getY() + 1) {
            return pawn.getX() - target.getX() == 0
                    || pawn.getX() - target.getX() == -1;
        }
        // horizontal move
        if (target.getY() == pawn.getY()) {
            return pawn.getX() - target.getX() == 1
                    || pawn.getX() - target.getX() == -1;
        }
        // any other move is forbidden
        return false;
    }

    private boolean checkJump(Pawn pawn, Field target) {

        if (!pawn.equals(pawn.getOwner().getLastMoved()) && pawn.getOwner().getLastMoved() != null){
            return false;
        }

        // vertical jump
        if (pawn.getX() == target.getX()) {
            return (pawn.getY() - target.getY() == 2
                        && pawn.getBoard().isPawn(pawn.getX(), pawn.getY() - 1))
                    || (pawn.getY() - target.getY() == -2
                            && pawn.getBoard().isPawn(pawn.getX(), pawn.getY() + 1));
        }
        //horizontal jump
        if (pawn.getY() == target.getY()) {
            return (pawn.getX() - target.getX() == 2
                        && pawn.getBoard().isPawn(pawn.getX() - 1, pawn.getY()))
                    || (pawn.getX() - target.getX() == -2
                            && pawn.getBoard().isPawn(pawn.getX() + 1, pawn.getY()));
        }
        // '\' jump
        return (pawn.getY() - target.getY() == -2
                    && pawn.getX() - target.getX() == -2
                    && pawn.getBoard().isPawn(pawn.getX() + 1, pawn.getY() + 1))
                || (pawn.getY() - target.getY() == 2
                        && pawn.getX() - target.getX() == 2
                        && pawn.getBoard().isPawn(pawn.getX() - 1 , pawn.getY() - 1));


    }

    private boolean checkMoveForBoard(Board board, Pawn pawn, EmptyField target) {
        if ("SixPointedStar".equals(board.getType())) {
            List<Field> temp = ((SixPointedStarSide) pawn.getOwner().getStartingSide()).getOppositeArea((SixPointedStar)board);
            return !temp.contains(pawn) || temp.contains(target);
        }

        return false;

    }

}
