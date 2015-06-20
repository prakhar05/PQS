package com.edu.nyu.cs.pqs.pqs5;

/**
 * Class that contains the main method that starts the game
 * 
 * @author pv594
 *
 */
public class GameApp {

  private void go() {
    GameTypeFactory factory = new GameTypeFactory();
    new GameUI(factory);
  }

  /**
   * Main method to start the game.
   * 
   * @param args
   */
  public static void main(String[] args) {
    new GameApp().go();
  }
}
