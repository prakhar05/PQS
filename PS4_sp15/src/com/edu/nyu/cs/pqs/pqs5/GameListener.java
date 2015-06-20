package com.edu.nyu.cs.pqs.pqs5;

/**
 * Interface for the observers / listeners / UI windows for the game
 * 
 * @author pv594
 *
 */
public interface GameListener {
  /**
   * Method that starts the game and updates the UI.
   * 
   * @param gameState
   */
  public void gameStart(GameState gameState);

  /**
   * Method to update the board on the UI. Accepts the last move played as
   * parameter
   * 
   * @param GameMove
   *          LastMovePlayed
   */
  public void updateBoard(GameMove LastMovePlayed);

  /**
   * Method that upates the game status information on the UI. Accepts game
   * state instance as parameter
   * 
   * @param GameState
   *          gameState
   */
  public void updateGameStatus(GameState gameState);

  /**
   * Method that updates the UI when the game ends
   */
  public void gameEnd();

  /**
   * Method to reset the UI elements when the game is reset and a new game is
   * started
   */
  public void resetGame();
}
