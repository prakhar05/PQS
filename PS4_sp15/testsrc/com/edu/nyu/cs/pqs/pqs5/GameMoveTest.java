package com.edu.nyu.cs.pqs.pqs5;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameMoveTest {

  @Test
  public void testGameMoveAndGetterMethods() {
    GameMove move = new GameMove((short)5);
    assertEquals(move.getColumn(),5);
    assertEquals(move.getRow(),0);
    assertEquals(move.getPawn(),'A');
  }

  @Test
  public void testSetColumn() {
    GameMove move = new GameMove((short)3);
    move.setColumn((short)4);
    assertEquals(move.getColumn(),4);
  }


  @Test
  public void testSetRow() {
    GameMove move = new GameMove((short)5,(short)5,'A');
    move.setRow((short)1);
    assertEquals(move.getRow(),1);
  }

  @Test
  public void testSetPawn() {
    GameMove move = new GameMove((short)3);
    move.setPawn('B');
    assertEquals(move.getPawn(),'B');
  }

  @Test
  public void testEqualsObject() {
    GameMove move = new GameMove((short)5,(short)5,'A');
    GameMove move2 = new GameMove((short)5,(short)5,'A');
    assertTrue(move.equals(move2));
    assertFalse(move.equals(""));
  }

  @Test
  public void testToString() {
    GameMove move = new GameMove((short)5,(short)5,'A');
    String moveString = move.toString();
    String moveStringDuplicate = "MoveRow:" + 5 + "\n" + "MoveColumn:" + 5 + "\n" + "MovePawn:" + 'A';
    assertEquals(moveString,moveStringDuplicate);
  }
  
  @Test
  public void testHashCode() {
    GameMove move = new GameMove((short)5,(short)5,'A');
    int moveHashCode = move.hashCode();
    int moveHashCode2 = move.hashCode();
    assertEquals(moveHashCode,moveHashCode2);
  }

}
