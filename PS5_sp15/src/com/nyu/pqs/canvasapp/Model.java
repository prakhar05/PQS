package com.nyu.pqs.canvasapp;

import java.awt.Point;

public interface Model {
  public void registerListener(View canvas) throws IllegalArgumentException;
  public void deleteListener(View canvas) throws IllegalArgumentException;
  public void createNewDrawing();
  public void draw(Point start,Point end);
  public void updateCanvas();
  //to set the drawMode and tell all the listeners what the drawing mode is
  public void updateDrawMode(String drawMode);
  
}
