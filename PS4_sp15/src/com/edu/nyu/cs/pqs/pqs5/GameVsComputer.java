package com.edu.nyu.cs.pqs.pqs5;

import java.util.ArrayList;
import java.util.List;

//Note: This class is the same as GameVsPlayer class except that it has  a generate computer move method
//and additional conditions in the Game controller to make sure that the Computer plays the game.
//But I put it in two different classes so that I could implement the factory pattern.
/**
 * Class that acts as the back end for the Vs Computer mode of the game
 * 
 * @author KillEmAll
 *
 */
public class GameVsComputer implements GameModel {
  private static GameVsComputer uniqueInstance;
  private List<GameListener> UIWindows = new ArrayList<GameListener>();
  private GameState gameState;

  private GameVsComputer() {
    gameState = new GameState("Vs Computer");
  }

  /**
   * Method that instantiates and returns an instance of this class.
   * 
   * @return
   */
  // Singleton Pattern
  public static GameVsComputer getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new GameVsComputer();
    }
    return uniqueInstance;
  }

  /**
   * This method is used to add listeners to list so that messages can be
   * broadcasted to all listeners or observers when the observers need to be
   * updated.
   * 
   * @param GameListener
   *          listener
   */
  @Override
  public void registerListener(GameListener listener) {
    UIWindows.add(listener);
  }

  /**
   * This method removes listeners from the list of observers.
   * 
   * @param GameListener
   *          listener
   */
  @Override
  public void removeListener(GameListener listener) {
    if (UIWindows.indexOf(listener) > 0) {
      UIWindows.remove(UIWindows.indexOf(listener));
    } else {
      throw new IllegalArgumentException("Listener does not exist");
    }
  }

  /**
   * This method will broadcast to the listeners that game has started
   */
  @Override
  public void startGame() {
    for (GameListener UIWindow : UIWindows) {
      UIWindow.gameStart(gameState);
    }
  }

  /**
   * This method will take a column as parameter and construct a move that will
   * contain a row, column and a pawn.
   * 
   * @param short column
   * @return GameMove
   */
  @Override
  public GameMove createMove(short column) {
    GameMove playerMove = new GameMove(column);
    return playerMove;
  }

  /**
   * Method that generates the computer move by looking ahead one move and
   * deciding which move is the best. A modified implementation of the min max
   * algorithm for AI
   * 
   * @param GameState
   *          gameState
   * @return short column
   */
  public short generateComputerMoveColumn(GameState gameState) {
    List<Short> highscores = new ArrayList<Short>();
    List<Short> midscores = new ArrayList<Short>();
    List<Short> lowscores = new ArrayList<Short>();
    boolean columnFull = true;

    for (short i = 0; i < 7; i++) {
      char[][] currentBoard = gameState.getBoard();
      // generate the new board with computerMove
      for (short j = 0; j < 6; j++) {
        if (currentBoard[j][i] == ' ') {
          currentBoard[j][i] = 'B';
          columnFull = false;
          break;
        }
      }
      // check if column was full and assign low score to move if column was
      // full
      if (columnFull) {
        lowscores.add(i);
      }
      // check for winner and if column number results in a win put in
      // highscores list,
      // if no result then put in midscores and if loss then in lowscores list
      char tempWinner = findWinner(currentBoard);
      if (tempWinner == 'B') {
        highscores.add(i);
      } else if (tempWinner == 'A') {
        lowscores.add(i);
      } else if (tempWinner == '0') {
        midscores.add(i);
      }
    }
    // choose move with highest score
    if (!highscores.isEmpty()) {
      double index = ((Math.random())) * ((double) (highscores.size()));
      int randomIndex = (int) index;
      return highscores.get(randomIndex);
    } else if (!midscores.isEmpty()) {
      double index = ((Math.random())) * ((double) (midscores.size()));
      int randomIndex = (int) index;
      return midscores.get(randomIndex);
    } else {
      double index = ((Math.random())) * ((double) (lowscores.size()));
      int randomIndex = (int) index;
      return lowscores.get(randomIndex);
    }
  }

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
  @Override
  public boolean isMoveOk(GameMove playerMove, GameState gameState) {
    short playerMoveColumn = playerMove.getColumn();
    char[][] gameStateBoard = gameState.getBoard();

    // check if the column entered in not part of the index
    if (playerMoveColumn < 0 || playerMoveColumn > 6) {
      return false;
    }

    // check if the column is already full
    if (gameStateBoard[0][playerMoveColumn] != ' ') {
      return false;
    }

    return true;
  }

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
  @Override
  public GameState updateState(GameMove playerMove, GameState gameState) {
    short playerMoveColumn = playerMove.getColumn();
    char[][] gameStateBoard = gameState.getBoard();
    char pawn = gameState.getTurn();

    // update the board
    for (short i = 5; i >= 0; i--) {
      if (gameStateBoard[i][playerMoveColumn] == ' ') {
        gameState.setBoard(i, playerMoveColumn, pawn);
        gameState.setLastMove(i, playerMoveColumn, pawn);
        break;
      }
    }

    // change the turn
    if (pawn == 'A') {
      gameState.setTurn('B');
    } else {
      gameState.setTurn('A');
    }

    // set the winner
    gameState.setWinner(findWinner(gameState.getBoard()));

    return gameState;
  }

  /**
   * This method will return the winner of the game when provided with a board.
   * 
   * @param char[][] board
   * @return char
   */
  @Override
  public char findWinner(char[][] board) {
    for (short i = 5; i >= 0; i--) {
      for (short j = 0; j < 4; j++) {
        if (checkWinnerEast(i, j, board)) {
          return board[i][j];
        }
      }
    }

    for (short i = 5; i > 2; i--) {
      for (short j = 0; j < 4; j++) {
        if (checkWinnerNorthEast(i, j, board)) {
          return board[i][j];
        }
      }
    }

    for (short i = 5; i > 2; i--) {
      for (short j = 0; j < 7; j++) {
        if (checkWinnerNorth(i, j, board)) {
          return board[i][j];
        }
      }
    }

    for (short i = 5; i > 2; i--) {
      for (short j = 3; j < 7; j++) {
        if (checkWinnerNorthWest(i, j, board)) {
          return board[i][j];
        }
      }
    }

    return '0';
  }

  /**
   * This method is the overall controller that will call the methods in the
   * order that they have to be executed. Contains additional fucntionality to
   * ensure that computer move is called after the player has played his move
   * 
   * @param short column
   */
  @Override
  public void gameController(short column) {
    GameMove playerMove = createMove(column);
    if (isMoveOk(playerMove, this.gameState)) {
      this.gameState = updateState(playerMove, this.gameState);
      // call update UI passing everything the UI needs to change itself
      updateUI();
      if (this.gameState.getWinner() != '0') {
        endGame();
      }
      if (this.gameState.getGameMode().equals("Vs Computer")
          && this.gameState.getTurn() == 'B') {
        short computerMoveColumn = generateComputerMoveColumn(this.gameState);
        this.gameController(computerMoveColumn);
      }
    }
  }

  /**
   * This method will broadcast to the listeners the game is over.
   */
  @Override
  public void endGame() {
    for (GameListener UIWindow : UIWindows) {
      UIWindow.gameEnd();
    }
  }

  /**
   * This method should update the UI by reading the internal state of the game
   */
  @Override
  public void updateUI() {
    for (GameListener UIWindow : UIWindows) {
      try {
        UIWindow.updateBoard(gameState.getLastMove());
        UIWindow.updateGameStatus(gameState);
      } catch (Exception nullLastMove) {
      }
    }
  }

  /**
   * This method should clear the states of the current instance.
   */
  @Override
  public void gameReset() {
    gameState = null;
    for (GameListener UIWindow : UIWindows) {
      UIWindow.resetGame();
    }
  }

  /**
   * This method is used to write tests for methods that do not return anything.
   * An option argument can be passed to identify the piece of test code within
   * this method that has to be run. It returns a boolean for the result of the
   * test.
   * 
   * @param option
   * @return
   */
  @Override
  public boolean runTestMethods(int option) {
    if (option == 1) {
      String test1Output = TestGameMode();
      if (test1Output.equals("Vs Computer")) {
        return true;
      }
    }
    if (option == 2) {
      GameTypeFactory factory = new GameTypeFactory();
      GameUI UI1 = new GameUI(factory);
      GameUI UI2 = new GameUI(factory);
      return TestListenerArrayRegisterAndDelete(UI1, UI2);
    }

    return false;
  }

  private String TestGameMode() {
    return this.gameState.getGameMode();
  }

  private boolean TestListenerArrayRegisterAndDelete(GameUI UI1, GameUI UI2) {
    UIWindows.add(UI1);
    UIWindows.add(UI2);
    UIWindows.remove(UI2);
    if (!UIWindows.contains(UI2)) {
      return true;
    }
    return false;
  }

  private boolean checkWinnerEast(short row, short column, char[][] board) {
    char pawn = board[row][column];
    // if the pawn is an empty space, no winner
    if (pawn == ' ') {
      return false;
    }
    // move east from current position and make sure all tiles same as current
    // pawn
    for (short i = column; i < column + 4; i++) {
      if (board[row][i] != pawn) {
        return false;
      }
    }
    return true;
  }

  private boolean checkWinnerNorth(short row, short column, char[][] board) {
    char pawn = board[row][column];
    if (pawn == ' ') {
      return false;
    }
    for (short i = row; i > row - 4; i--) {
      if (board[i][column] != pawn) {
        return false;
      }
    }
    return true;
  }

  private boolean checkWinnerNorthEast(short row, short column, char[][] board) {
    char pawn = board[row][column];
    if (pawn == ' ') {
      return false;
    }
    for (short i = row, j = column; i > row - 4 && j < column + 4; i--, j++) {
      if (board[i][j] != pawn) {
        return false;
      }
    }
    return true;
  }

  private boolean checkWinnerNorthWest(short row, short column, char[][] board) {
    char pawn = board[row][column];
    if (pawn == ' ') {
      return false;
    }
    for (short i = row, j = column; i > row - 4 && j > column - 4; i--, j--) {
      if (board[i][j] != pawn) {
        return false;
      }
    }
    return true;
  }

}
