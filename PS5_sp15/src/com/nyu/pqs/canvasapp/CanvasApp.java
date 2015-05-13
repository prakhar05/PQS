package com.nyu.pqs.canvasapp;

/**
 * The class containing the main function which instantiates one model and multiple views,
 * and binds them together
 * @author pv594
 *
 */
public class CanvasApp {
  
  private void runCanvasApp(){
    Model canvasBackend = new CanvasBackend();
    View canvas1 = new Canvas(canvasBackend);
    View canvas2 = new Canvas(canvasBackend);
  }
  
  /**
   * Main method that calls private methods to instantiate model and view objects
   * @param args
   */
  public static void main(String[] args){
    new CanvasApp().runCanvasApp();
  }
}
