package gamelogic.player;

public enum Color {
  RED, GREEN, BLACK, BLUE, YELLOW, PURPLE;

  public static Color valueOfJson(String val){

    if(("\"RED\"").equals(val)){
      return Color.RED;
    }if(("\"GREEN\"").equals(val)){
      return Color.GREEN;
    }if(("\"BLACK\"").equals(val)){
      return Color.BLACK;
    }if(("\"BLUE\"").equals(val)){
      return Color.BLUE;
    }if(("\"YELLOW\"").equals(val)){
      return Color.YELLOW;
    }if(("\"PURPLE\"").equals(val)){
      return Color.PURPLE;
    }

    return null;
  }

}
