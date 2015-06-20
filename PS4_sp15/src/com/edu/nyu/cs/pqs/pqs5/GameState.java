package com.edu.nyu.cs.pqs.pqs5;

import java.util.Arrays;

/**
 * Class that stores the GameState. Contains the game mode, board, player turn,
 * game winner last move details
 * 
 * @author pv594
 *
 */
public class GameState {
  private String gameMode;
  private char[][] board = new char[6][7];
  private char playerTurn;
  private char winner;
  private GameMove LastMoveDetails;

  /**
   * Constructor for the GameState class. Takes in the game mode as a parameter
   * which should be a String "Vs Computer" or "Vs Player"
   * 
   * @param String
   *          gameMode
   */
  public GameState(String gameMode) {
    // game mode can be Vs Computer or Vs Player
    this.gameMode = gameMode;
    // initialise board to be empty
    initialiseBoard();
    // turnIndex can be A or B
    playerTurn = 'A';
    // winner can be A, B or 0 where 0 represents no winner
    winner = '0';
    // LastMoveDetails initialised to values not accepted in the game
    LastMoveDetails = new GameMove((short) 100, (short) 100, 'Z');
  }

  /**
   * Setter method for the game mode member. Accepts a String as a parameter
   * whose values should be be either "Vs Computer" or "Vs Player"
   * 
   * @param String
   *          gameMode
   */
  public void setGameMode(String gameMode) {
    this.gameMode = gameMode;
  }

  /**
   * Getter method for the game mode member
   * 
   * @return String gameMode
   */
  public String getGameMode() {
    return gameMode;
  }

  /**
   * Setter method for the turn member. Turn can be 'A' or 'B'
   * 
   * @param char turn
   */
  public void setTurn(char turn) {
    this.playerTurn = turn;
  }

  /**
   * Getter method for the turn member
   * 
   * @return char turn
   */
  public char getTurn() {
    return this.playerTurn;
  }

  /**
   * Setter method for the winner. Initially winner set to '0' representing no
   * winner. Winner can be '0', 'A' or 'B' at any moment
   * 
   * @param char winner
   */
  public void setWinner(char winner) {
    this.winner = winner;
  }

  /**
   * Getter method for the winner. Returns 'A', 'B' or '0'
   * 
   * @return
   */
  public char getWinner() {
    return this.winner;
  }

  /**
   * Setter method for the board that takes in a row,column and pawn and sets
   * the corresponding place in the array with the value of the pawn
   * 
   * @param short row
   * @param short column
   * @param short pawn
   */
  public void setBoard(short row, short column, char pawn) {
    board[row][column] = pawn;
  }

  /**
   * Getter method for the board. Returns a char[][] array which is a copy of
   * the current state of the board
   * 
   * @return char[][]
   */
  public char[][] getBoard() {
    char[][] tempBoard = new char[6][7];
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        tempBoard[i][j] = board[i][j];
      }
    }
    return tempBoard;
  }

  /**
   * Method to set the last move member of game state. Takes in a row, column
   * and pawn as parameters
   * 
   * @param short row
   * @param short column
   * @param char pawn
   */
  public void setLastMove(short row, short column, char pawn) {
    LastMoveDetails.setRow(row);
    LastMoveDetails.setColumn(column);
    LastMoveDetails.setPawn(pawn);
  }

  /**
   * Method that returns the last move played on the board. Contains dummy
   * values when instance of this class is created hence if called when no moves
   * have been played, will throw an exception
   * 
   * @return GameMove move
   * @throws Exception
   */
  public GameMove getLastMove() throws Exception {
    if (LastMoveDetails.getRow() == 100) {
      throw new Exception("No last move yet");
    }
    short tempRow = LastMoveDetails.getRow();
    short tempColumn = LastMoveDetails.getColumn();
    char tempPawn = LastMoveDetails.getPawn();
    GameMove LastMoveCopy = new GameMove(tempRow, tempColumn, tempPawn);
    return LastMoveCopy;
  }

  /**
   * Overriden object equals method
   */
  @Override
  public boolean equals(Object o) {
    boolean boardEqual = true;
    if (!(o instanceof GameState)) {
      return false;
    }
    GameState state = (GameState) o;
    char[][] tempBoard = state.getBoard();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        if (tempBoard[i][j] != board[i][j]) {
          boardEqual = false;
          break;
        }
      }
    }
    return state.gameMode.equals(gameMode) && state.playerTurn == playerTurn
        && state.winner == winner && boardEqual
        && state.LastMoveDetails.equals(LastMoveDetails);
  }

  /**
   * Overriden object hashCode method
   */
  @Override
  public int hashCode() {
    int result = 23;
    result = 31 * result + (int) playerTurn;
    result = 31 * result + (int) winner;
    result = 31 * result + gameMode.hashCode();
    result = 31 * result + Arrays.deepHashCode(board);
    result = 31 * result + LastMoveDetails.hashCode();
    return result;
  }

  // not Overriding toString because I dont see the use, and there's too much to
  // print including the board etc.

  // helper method to initialize an empty board
  private void initialiseBoard() {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        board[i][j] = ' ';
      }
    }
  }
}
