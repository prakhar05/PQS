package com.edu.nyu.cs.pqs.pqs5;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameVsPlayerTest {

  @Test
  public void testGetInstance() {
    GameVsPlayer game = GameVsPlayer.getInstance();
    assertTrue(game instanceof GameVsPlayer);
  }

  @Test
  public void testRemoveListener() {
    GameVsPlayer game = GameVsPlayer.getInstance();
    assertTrue(game.runTestMethods(2));
  }

  @Test
  public void testCreateMove() {
    GameVsPlayer game = GameVsPlayer.getInstance();
    GameMove move = game.createMove((short)5);
    assertEquals(move.getColumn(),5);
    assertEquals(move.getRow(),0);
    assertEquals(move.getPawn(),'A');
  }

  @Test
  public void testIsMoveOk() {
    GameVsPlayer game = GameVsPlayer.getInstance();
    GameMove move = new GameMove((short)0,(short)2,'B');
    GameState tempState = new GameState("Vs Player");
    assertTrue(game.isMoveOk(move,tempState));
    move.setColumn((short)33);
    assertFalse(game.isMoveOk(move,tempState));
    tempState.setBoard((short)0,(short)3, 'B');
    move.setColumn((short)3);
    assertFalse(game.isMoveOk(move, tempState));
  }

  @Test
  public void testUpdateState() {
    GameVsPlayer game = GameVsPlayer.getInstance();
    GameMove move = new GameMove((short)0,(short)2,'A');
    GameState tempState = new GameState("Vs Player");
    tempState = game.updateState(move, tempState);
    assertEquals(tempState.getWinner(),'0');
    char[][] tempboard = tempState.getBoard();
    assertEquals(tempboard[5][2],'A');
    GameMove move2 = new GameMove((short)0,(short)3,'B');
    tempState = game.updateState(move2, tempState);
    assertEquals(tempState.getTurn(),'A');
  }

  @Test
  public void testFindWinner() {
    GameState tempState = new GameState("Vs Player");
    //check winners East
    tempState.setBoard((short)0, (short)0, 'A');
    tempState.setBoard((short)0, (short)1, 'A');
    tempState.setBoard((short)0, (short)2, 'A');
    tempState.setBoard((short)0, (short)3, 'A');
    GameVsPlayer game = GameVsPlayer.getInstance();
    assertEquals(game.findWinner(tempState.getBoard()),'A');
    //check winners north
    tempState.setBoard((short)0, (short)0, 'B');
    tempState.setBoard((short)1, (short)0, 'B');
    tempState.setBoard((short)2, (short)0, 'B');
    tempState.setBoard((short)3, (short)0, 'B');
    assertEquals(game.findWinner(tempState.getBoard()),'B');
    //check winners northWest
    tempState.setBoard((short)1, (short)0, ' ');
    tempState.setBoard((short)2, (short)0, ' ');
    tempState.setBoard((short)3, (short)0, ' ');
    tempState.setBoard((short)0, (short)0, 'B');
    tempState.setBoard((short)1, (short)1, 'B');
    tempState.setBoard((short)2, (short)2, 'B');
    tempState.setBoard((short)3, (short)3, 'B');
    assertEquals(game.findWinner(tempState.getBoard()),'B');
    //check winners northEast
    tempState.setBoard((short)0, (short)0, ' ');
    tempState.setBoard((short)1, (short)1, ' ');
    tempState.setBoard((short)2, (short)2, ' ');
    tempState.setBoard((short)3, (short)3, ' ');
    tempState.setBoard((short)0, (short)6, 'A');
    tempState.setBoard((short)1, (short)5, 'A');
    tempState.setBoard((short)2, (short)4, 'A');
    tempState.setBoard((short)3, (short)3, 'A');
    assertEquals(game.findWinner(tempState.getBoard()),'A');
  }


//  public void testGameController() {}
//  There is no good way to test this function, it does not return anything and only calls the 
//  other functions in the order they are supposed to be called. Even writing a private function
//  within the class did not make sense since the individual functions making up this function 
//  are already being tested.
  

//  public void testEndGame() {}
//  public void testUpdateUI() {}
//  public void testGameReset() {}
//  These methods just call the UI methods hence testing them is not required.
}
