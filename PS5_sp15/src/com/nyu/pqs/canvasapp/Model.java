package com.nyu.pqs.canvasapp;

public interface Model {
  public void registerListener(View canvas) throws IllegalArgumentException;
  public void deleteListener(View canvas);
  public void createNewDrawing();
  public void updateCanvas();
  public void selectDrawingObject();
  
}
