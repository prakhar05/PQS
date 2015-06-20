package com.edu.nyu.cs.pqs.pqs5;

/**
 * A class that stores the move played in the game. Pertaining to connect 4 it
 * contains the row, column and the pawn which made the move. Move checks are
 * implemented in the back end/game logic.
 * 
 * @author pv594
 *
 */
public class GameMove {
  private short column;
  private short row;
  private char pawn;

  /**
   * Constructor to build the move on selection of column
   * 
   * @param short column
   */
  public GameMove(short column) {
    this.column = column;
    // first move is always in row 0
    this.row = 0;
    // first pawn to play is always A
    this.pawn = 'A';
  }

  /**
   * Constructor to build the move when the row and pawn have been identified.
   * 
   * @param row
   * @param column
   * @param pawn
   */
  // Constructor can be used for games where both row and column are selected.
  // Also used to initialize the LastMoveDetails member of GameState
  public GameMove(short row, short column, char pawn) {
    this.row = row;
    this.column = column;
    this.pawn = pawn;
  }

  /**
   * Setter function for the column member of the GameState.
   * 
   * @param short column
   */
  public void setColumn(short column) {
    this.column = column;
  }

  /**
   * Getter method for the column member of the GameState.
   * 
   * @return short column
   */
  public short getColumn() {
    return this.column;
  }

  /**
   * Setter method for the row member of the GameState.
   * 
   * @param short row
   */
  public void setRow(short row) {
    this.row = row;
  }

  /**
   * Getter method for the row member of the GameState
   * 
   * @return short row
   */
  public short getRow() {
    return this.row;
  }

  /**
   * Setter method for the pawn that made the move
   * 
   * @param char pawn
   */
  public void setPawn(char pawn) {
    this.pawn = pawn;
  }

  /**
   * Getter method for the pawn that made the move
   * 
   * @return
   */
  public char getPawn() {
    return this.pawn;
  }

  /**
   * Overriden Object equals method
   */

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GameMove)) {
      return false;
    }
    GameMove move = (GameMove) o;
    return move.row == row && move.column == column && move.pawn == pawn;
  }

  /**
   * Overriden Object hashCode method
   */

  @Override
  public int hashCode() {
    int result = 23;
    result = 31 * result + (int) row;
    result = 31 * result + (int) column;
    result = 31 * result + (int) pawn;
    return result;
  }

  /**
   * Overriden Object toString method
   */
  @Override
  public String toString() {
    String result;
    result = "MoveRow:" + row + "\n" + "MoveColumn:" + column + "\n"
        + "MovePawn:" + pawn;
    return result;
  }
}
