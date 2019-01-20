package gamelogic.player.botAlgorithm;

import gamelogic.exception.ForbiddenActionException;
import gamelogic.field.Pawn;
import gamelogic.player.Bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BotAlgorithmTemplate {
    public void play(Bot b) {
        //first stage
        prepareMove(b);
        //second and third stage in loop
        while (b.getTargets().size() > 0) {
            chooseAndExecuteMove(b);
            checkMoves(b);
        }
        //last stage
        endBotTurn(b);
    }

    private void prepareMove(Bot b) {
        List<Pawn> list = new ArrayList<>();
        while (!b.isMovable()) {
            if (list.size() == b.getPawns().size()) {
                b.setChosenPawn(null);
                break;
            }
            chooseRandomPawn(b, list);
            checkMoves(b);
        }
    }

    private void chooseRandomPawn(Bot b, List<Pawn> list) {
        List<Pawn> pawns = b.getPawns();
        Random rand = new Random();
        int index = rand.nextInt(b.getPawns().size());

        b.setChosenPawn(pawns.get(index));

        //save pawn if it hasn't chosen before
        if (!list.contains(b.getChosenPawn())) {
            list.add(b.getChosenPawn());
        }
    }

    public abstract void checkMoves(Bot b);

    public abstract void chooseAndExecuteMove(Bot b);

    private void endBotTurn(Bot b) {
        try {
            b.getGame().getController().endTurn(b.getId());
            b.sendToPlayers();
            b.setMovable(false);
            b.getLastPositions().clear();
            Thread.sleep(1000);
        } catch (ForbiddenActionException e) {
            System.out.println("bot:" + " " + e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
