package com.nyu.pqs.canvasapp;

/**
 * Interface for the View. The View must handle all the interaction with the user, and communicate
 * mouse coordinates and drawing options back to the model so that it can be boradcasted to all 
 * subscribed View Instances.
 * @author pv594
 *
 */

public interface View {
  /**
   * Method to register the listener with its model when object is instantiated
   */
  public void createNewDrawing();
  /**
   * Update the view with drawing object data from the model when asked to by the 
   * model
   */
  public void updateView();
}
