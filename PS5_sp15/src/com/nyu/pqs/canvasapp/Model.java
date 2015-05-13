package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Iterator;

/**
 * An interface for the model of the Cavas Application. The aim of the model is to have all the 
 * information regarding the drawings being done on the UI, so that it can broadcast the changes 
 * to other listeners(UI elements) in line with the Observer pattern
 * @author pv594
 *
 */
public interface Model {
  /**
   * Add the View to the list of listeners, throw exception if listener already exists in the
   * list
   * @param View canvas
   * @throws IllegalArgumentException
   */
  public void registerListener(View canvas) throws IllegalArgumentException;
  
  /**
   * Remove the View form the list of listeners, throw exception if listener not found in the
   * list
   * @param View canvas
   * @throws IllegalArgumentException
   */
  public void deleteListener(View canvas) throws IllegalArgumentException;
  
  /**
   * Method used to clear out the canvas/reset it
   */
  public void createNewDrawing();
  
  /**
   * Method used to update the canvas of the listeners, by redrawing all the shapes stored in 
   * the drawing object list, including any new ones added to it as the mouse moves
   */
  public void updateCanvas();
  
  /**
   * Keep track of what drawing option is currently selected in the View
   * @param drawMode
   */
  public void updateDrawMode(String drawMode);
  
  /**
   * Keep track of current color selected in the View
   * @param currentColor
   */
  public void updateCurrentColor(Color currentColor);
  
  /**
   * Add a new drawing object to the list of things to be drawn, by creating new drawing objects
   * and setting the shape,color and stroke parameters according to what is currently selected in 
   * the View
   * @param startPoint
   * @param endPoint
   */
  public void addDrawingObject(Point startPoint, Point endPoint);
  
  /**
   * A temporary drawing object to display what is being drawn as the cursor moves on the screen.
   * Used to display pencil, brush and eraser strokes on the View
   * @param startPoint
   * @param endPoint
   */
  public void addTempDrawingObject(Point startPoint, Point endPoint);
  
  /**
   * An iterator for the list of shapes to be drawn, so that the View can access the shapes and 
   * draw them on the canvas
   * @return Iterator of DrawingObject
   */
  public Iterator<DrawingObject> getDrawingObjectIterator();
  
  /**
   * Method that returns the current temporary drawing object, so that the View can draw the 
   * temporary shape on the screen
   * @return DrawingObject
   */
  public DrawingObject getTempDrawingObject();
  
  /**
   * Method to increase the stroke values of the shapes being drawn
   */
  public void increaseStroke();
  
  /**
   * Method to decrease the stroke values of the shapes being drawn
   */
  public void decreaseStroke();
  
  /**
   * Getter for the current color
   * @return Color 
   */
  public Color getCurrentColor();
  
  /**
   * Getter for the current drawing mode
   * @return String
   */
  public String getDrawMode();
  
  /**
   * Getter for the current Stroke Value
   * @return float
   */
  public float getStrokeValue();
}
