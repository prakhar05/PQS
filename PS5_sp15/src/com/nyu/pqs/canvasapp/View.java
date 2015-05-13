package com.nyu.pqs.canvasapp;

import java.awt.Color;

public interface View {
  //act as a new drawing or a reset drawing
  public void createNewDrawing();
  //update the view with data from model
  public void updateView();
  //getColor from the color panel
  public Color getColor();
}
