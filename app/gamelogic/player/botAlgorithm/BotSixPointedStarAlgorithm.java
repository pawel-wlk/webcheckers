package gamelogic.player.botAlgorithm;

import gamelogic.board.SixPointedStarSide;
import gamelogic.exception.ForbiddenActionException;
import gamelogic.exception.ForbiddenMoveException;
import gamelogic.field.EmptyField;
import gamelogic.field.Field;
import gamelogic.player.Bot;

import java.util.*;

public abstract class BotSixPointedStarAlgorithm extends BotAlgorithmTemplate {
    public abstract void checkMoves(Bot b);

    public void chooseAndExecuteMove(Bot b){
        //find the best target that has the shortest distance to winning corner
        if(b.getChosenPawn() == null) return;

        Map<EmptyField, Double> targets = new LinkedHashMap<>();

        for(EmptyField f : b.getTargets()){
            targets.put(f, distance(f, (SixPointedStarSide) b.getStartingSide()));
        }

        List<Map.Entry<EmptyField, Double>> list = new ArrayList<>(targets.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        EmptyField chosenTarget = list.get(0).getKey();

        //move chosen Pawn to chosen target
        try {
            b.getGame().getController().move(
                    b.getId(),
                    b.getChosenPawn().getX(),
                    b.getChosenPawn().getY(),
                    chosenTarget.getX(),
                    chosenTarget.getY()
            );

            b.getLastPositions().add(chosenTarget);

        } catch (ForbiddenMoveException | ForbiddenActionException e) {
            System.out.println("bot:" + b.getId() + " " + e.toString());
        }
    }

    private double distance(Field target, SixPointedStarSide side){
        int edgeX = side.getFarthest().getX();
        int edgeY = side.getFarthest().getY();

        return Math.sqrt(Math.pow(Math.abs(target.getX() - edgeX), 2) +
                        Math.pow(Math.abs(target.getY() - edgeY),2));
    }

}
