package com.nyu.pqs.canvasapp;

import java.util.List;
import java.util.ArrayList;

public class CanvasBackend implements Model {
  private List<View> CanvasList = new ArrayList<View>();
  private int oldX;
  private int oldY;
  private int currentX;
  private int currentY;
  
  
  @Override
  public void registerListener(View canvas) throws IllegalArgumentException {
    if(!CanvasList.contains(canvas)){
      CanvasList.add(canvas);
    }else{
      throw new IllegalArgumentException("This canvas already exists in list of listeners");
    }
   }

  @Override
  public void deleteListener(View canvas) throws IllegalArgumentException{
    if(CanvasList.contains(canvas)){
      CanvasList.remove(canvas);
    }else{
      throw new IllegalArgumentException("This canvas does not exist in the list of listeners");
    }

  }

  @Override
  public void createNewDrawing() {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateCanvas() {
    // TODO Auto-generated method stub

  }

  @Override
  public void selectDrawingObject() {
    // TODO Auto-generated method stub

  }

}
