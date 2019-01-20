package gamelogic.player.botAlgorithm;

import com.google.gson.JsonObject;
import gamelogic.player.Bot;

public class BotSender {
    private Bot actual;

    public BotSender(Bot bot){
        actual = bot;
    }

    public void sendToPlayers(){
        sendMove();
        sendEndTurn();
    }

    private void sendEndTurn(){
        JsonObject returnObj = new JsonObject();
        returnObj.addProperty("player", "bot");
        returnObj.addProperty("status", "successful");
        returnObj.addProperty("action", "endTurn");
        returnObj.addProperty("currentPlayer",
                this.actual.getGame().getController().getCurrentTurnPlayer().getId());

//        Server.getInstance().pushToMany(this.actual.getGame(), returnObj.toString());
    }

    private void sendMove(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("player", "bot");
        jsonObject.addProperty("action", "move");
        jsonObject.addProperty("status", "successful");
        jsonObject.addProperty("board", this.actual.getGame().getBoard().fieldsToString());

//        Server.getInstance().pushToMany(this.actual.getGame(), jsonObject.toString());
    }
}
