package com.nyu.pqs.canvasapp;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

public class CanvasBackend implements Model {
  private List<View> canvasList = new ArrayList<View>();
  private Point startPoint;
  private Point currentPoint;
  
  
  @Override
  public void registerListener(View canvas) throws IllegalArgumentException {
    if(!canvasList.contains(canvas)){
      canvasList.add(canvas);
    }else{
      throw new IllegalArgumentException("This canvas already exists in list of listeners");
    }
   }

  @Override
  public void deleteListener(View canvas) throws IllegalArgumentException{
    if(canvasList.contains(canvas)){
      canvasList.remove(canvas);
    }else{
      throw new IllegalArgumentException("This canvas does not exist in the list of listeners");
    }

  }

  @Override
  public void createNewDrawing() {
    for(View canvasListener : canvasList){
      canvasListener.clearCanvas();
    }
  }

  @Override
  public void updateCanvas() {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateDrawMode(String drawMode) {
    for(View canvasListener : canvasList){
      canvasListener.setDrawMode(drawMode);
    }
  }


  @Override
  public void draw(Point start, Point end) {
    for(View canvasListener : canvasList){
      canvasListener.drawLine();
    }
  }

 

}
