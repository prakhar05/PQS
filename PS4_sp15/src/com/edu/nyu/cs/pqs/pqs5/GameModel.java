package com.edu.nyu.cs.pqs.pqs5;

/**
 * GameModel.java This is an interface for the Game Models or Backends. The
 * classes that implement these interfaces are meant to hold the state of the
 * game and contain functions that modify the state of the game. They also
 * communicate with the "listeners" or "Observers" which are UI windows, and
 * provide them with the information required to update the UI state.
 * 
 * @author pv594
 *
 */

public interface GameModel {
  /**
   * This method is used to add listeners to list so that messages can be
   * broadcasted to all listeners or observers when the observers need to be
   * updated.
   * 
   * @param GameListener
   *          listener
   */
  public void registerListener(GameListener listener);

  /**
   * This method removes listeners from the list of observers.
   * 
   * @param GameListener
   *          listener
   */
  public void removeListener(GameListener listener);

  /**
   * This method will broadcast to the listeners that game has started
   */
  public void startGame();

  /**
   * This method will take a column as parameter and construct a move that will
   * contain a row, column and a pawn.
   * 
   * @param short column
   * @return GameMove
   */
  public GameMove createMove(short column);

  /**
   * This method will return true if the move that is passed is ok to be played
   * on the board contained in the game state, else will return false.
   * 
   * @param GameMove
   *          move
   * @param GameState
   *          state
   * @return boolean
   */
  public boolean isMoveOk(GameMove move, GameState state);

  /**
   * This method will update the state of the game according to the move played
   * and returned the updated state
   * 
   * @param GameMove
   *          move
   * @param GameState
   *          state
   * @return GameState
   */
  public GameState updateState(GameMove move, GameState state);

  /**
   * This method should update the UI by reading the internal state of the game
   */
  public void updateUI();

  /**
   * This method will return the winner of the game when provided with a board.
   * 
   * @param char[][] board
   * @return char
   */
  public char findWinner(char[][] board);

  /**
   * This method will broadcast to the listeners the game is over.
   */
  public void endGame();

  /**
   * This method is the overall controller that will call the methods in the
   * order that they have to be executed.
   * 
   * @param short column
   */
  public void gameController(short column);

  /**
   * This method should clear the states of the current instance.
   */
  public void gameReset();

  /**
   * This method is used to write tests for methods that do not return anything.
   * An option argument can be passed to identify the piece of test code within
   * this method that has to be run. It returns a boolean for the result of the
   * test.
   * 
   * @param option
   * @return
   */
  public boolean runTestMethods(int option);
}
