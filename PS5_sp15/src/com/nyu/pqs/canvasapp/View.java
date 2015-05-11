package com.nyu.pqs.canvasapp;

public interface View {
  //act as a new drawing or a reset drawing
  public void createNewDrawing();
  //update the view with data from model
  public void updateView();
  //clear the canvas
  public void clearCanvas();
  //set drawMode to tell the panel what mode of drawing
  public void setDrawMode(String drawMode);
  //draw line
  public void drawLine();
  
}
