package com.edu.nyu.cs.pqs.pqs5;

public class GameTypeFactory {
  public GameModel GetGameBackend(String GameMode) {
    GameModel model = null;

    if (GameMode.equals("Vs Computer")) {
      model = GameVsComputer.getInstance();
    } else if (GameMode.equals("Vs Player")) {
      model = GameVsPlayer.getInstance();
    }
    return model;
  }
}
