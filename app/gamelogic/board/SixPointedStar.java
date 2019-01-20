package gamelogic.board;

import gamelogic.creator.fieldCreator.FieldCreator;
import gamelogic.creator.fieldCreator.PawnCreator;
import gamelogic.field.Field;
import gamelogic.field.Pawn;
import gamelogic.player.Player;

import java.util.List;


public class SixPointedStar extends Board {


    public SixPointedStar(){
        setType("SixPointedStar");
        setFields(new Field[17][17]);
    }

    @Override
    public int sizeY() {
        return 17;
    }

    @Override
    public int sizeX() {
        return 17;
    }

    @Override
    public void setPawns(Player p) {
        FieldCreator creator = new PawnCreator();
        Pawn created;
        if(SixPointedStarSide.TOP == p.getStartingSide()){
            for(int i=0; i<4; i++){
                for(int j=0; j<=i; j++){
                    created = (Pawn)creator.createField(4 + j, i);
                    p.setOnePawn(created);
                    setOneField(created);
                }
            }
        } else if(SixPointedStarSide.LEFT_TOP == p.getStartingSide()){
            for(int i=0; i<4; i++){
                for(int j=3; j>=i; j--){
                    created = (Pawn)creator.createField(j, 4 + i);
                    p.setOnePawn(created);
                    setOneField(created);
                }
            }
        }else if(SixPointedStarSide.RIGHT_TOP == p.getStartingSide()){
            for(int i=0; i<4; i++){
                for(int j=3; j>=i; j--){
                    created = (Pawn)creator.createField(9 + j, 4 + i);
                    p.setOnePawn(created);
                    setOneField(created);
                }
            }
        }else if(SixPointedStarSide.BOTTOM == p.getStartingSide()){
            for(int i=0; i<4; i++){
                for(int j=3; j>=i; j--){
                    created = (Pawn)creator.createField(9 + j, 13 + i);
                    p.setOnePawn(created);
                    setOneField(created);
                }
            }
        }else if(SixPointedStarSide.LEFT_BOTTOM == p.getStartingSide()){
            for(int i=0; i<4; i++){
                for(int j=0; j<=i; j++){
                    created = (Pawn) creator.createField(4 + j, 9 + i);
                    p.setOnePawn(created);
                    setOneField(created);
                }
            }
        }else if(SixPointedStarSide.RIGHT_BOTTOM == p.getStartingSide()){
            for(int i=0; i<4; i++){
                for(int j=0; j<=i; j++){
                    created = (Pawn)creator.createField(13 + j, 9 + i);
                    p.setOnePawn(created);
                    setOneField(created);
                }
            }
        }
    }

    @Override
    public boolean checkWin(Player player) {
        List<Field> winning =
                ((SixPointedStarSide) player.getStartingSide()).getOppositeArea((SixPointedStar) player.getPawns().get(0).getBoard());
        int fieldsMatches = 0;

        for (Field f : winning) {
            for (Pawn p : player.getPawns()) {
                if (p.equals(f)) {
                    fieldsMatches++;
                }
            }
        }

        return fieldsMatches == player.getPawns().size();
    }
}
