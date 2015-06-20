package com.edu.nyu.cs.pqs.pqs5;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTypeFactoryTest {

  @Test
  public void testGetGameBackend() {
    GameTypeFactory gameFactory = new GameTypeFactory();
    GameModel model = gameFactory.GetGameBackend("Vs Computer");
    GameModel model2 =  gameFactory.GetGameBackend("Vs Player");
    assertTrue(model.runTestMethods(1));
    assertTrue(model2.runTestMethods(1));
  }

}
