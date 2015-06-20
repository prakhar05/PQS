package com.edu.nyu.cs.pqs.pqs5;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStateTest {

  @Test
  public void testGameStateConstructorAndGetters() {
    GameState state = new GameState("Vs Computer");
    assertTrue(state.getGameMode().equals("Vs Computer"));
    assertEquals(state.getTurn(),'A');
    assertEquals(state.getWinner(),'0');
  }

  @Test
  public void testSetGameMode() {
    GameState state = new GameState("Vs Computer");
    state.setGameMode("Vs Player");
    assertEquals(state.getGameMode(),"Vs Player");
  }

  @Test
  public void testSetTurn() {
    GameState state = new GameState("Vs Computer");
    state.setTurn('B');
    assertEquals(state.getTurn(),'B');
  }

  @Test
  public void testSetWinner() {
    GameState state = new GameState("Vs Computer");
    state.setWinner('A');
    assertEquals(state.getWinner(),'A');
  }

  @Test
  public void testSetBoard() {
    GameState state = new GameState("Vs Computer");
    state.setBoard((short)0, (short)2, 'A');
    char[][] gameBoard = state.getBoard();
    assertEquals(gameBoard[0][2],'A');
    assertEquals(gameBoard[2][3],' ');
  }

  @Test
  public void testSetLastMove() throws Exception {
    GameState state = new GameState("Vs Computer");
    state.setLastMove((short)2, (short)3, 'B');
    GameMove LastMove = state.getLastMove();
    assertEquals(LastMove.getRow(),2);
    assertEquals(LastMove.getColumn(),3);
    assertEquals(LastMove.getPawn(),'B');
  }

  @Test(expected = Exception.class)
  public void testGetLastMove() throws Exception{
    GameState state2 = new GameState("Vs Player");
    GameMove LastMove2 = state2.getLastMove();
  }

  @Test
  public void testEqualsObject() throws Exception {
    GameState state = new GameState("Vs Player");
    GameState state2 = new GameState("Vs Player");
    state.setLastMove((short)2, (short)3, 'B');
    state2.setLastMove((short)2, (short)3, 'B');
    assertFalse(state.equals(""));
    assertEquals(state.getLastMove(),state2.getLastMove());
    assertTrue(state.equals(state2));
    state2.setBoard((short)2, (short)3, 'A');
    assertFalse(state.equals(state2));
  }

  @Test
  public void testHashCode() {
    GameState state = new GameState("Vs Player");
    GameState state2 = new GameState("Vs Player");
    GameState state3 = new GameState("Vs Computer");
    int hashcode1 = state.hashCode();
    int hashcode2 = state2.hashCode();
    int hashcode3 = state3.hashCode();
    assertEquals(hashcode1,hashcode2);
    assertFalse(hashcode1==hashcode3);
  }

}
