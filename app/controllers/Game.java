package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.game;

public class Game extends Controller {
  public Result game() {
    return ok(game.render());
  }
}
