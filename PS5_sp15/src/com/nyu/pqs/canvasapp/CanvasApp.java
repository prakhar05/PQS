package com.nyu.pqs.canvasapp;

public class CanvasApp {
  
  private void runCanvasApp(){
    Model canvasBackend = new CanvasBackend();
    View canvas1 = new Canvas(canvasBackend);
//    View canvas2 = new Canvas(canvasBackend);
  }
 
  public static void main(String[] args){
    new CanvasApp().runCanvasApp();
  }
}
