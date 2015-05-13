package com.nyu.pqs.canvasapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Class which implements the Model and all its functions. Its promary job is to receive
 * UI updates from any of the Views and then broadcast it to all the other Views. It also
 * keeps a track of the all the shapes created which have to be redrawn everytime a new object
 * is added to the list of objects to be drawn
 * @author pv594
 *
 */
public class CanvasBackend implements Model {
  private List<View> canvasList = new ArrayList<View>();
  private List<DrawingObject> drawList = new ArrayList<DrawingObject>();
  private String drawMode;
  private Color currentColor;
  private Point lastPencilPoint;
  private DrawingObject tempDrawingObject;
  private float strokeValue;
  
  
  public CanvasBackend(){
    currentColor = Color.BLACK;
    drawMode = "Line";
    lastPencilPoint = null;
    tempDrawingObject = new DrawingObject();
    strokeValue = 1.0f;
  }
  
  /**
   * Add the View to the list of listeners, throw exception if listener already exists in the
   * list
   * @param View canvas
   * @throws IllegalArgumentException
   */
  @Override
  public void registerListener(View canvas) throws IllegalArgumentException {
    if(!canvasList.contains(canvas)){
      canvasList.add(canvas);
    }else{
      throw new IllegalArgumentException("This canvas already exists in list of listeners");
    }
   }
  
  /**
   * Remove the View form the list of listeners, throw exception if listener not found in the
   * list
   * @param View canvas
   * @throws IllegalArgumentException
   */
  @Override
  public void deleteListener(View canvas) throws IllegalArgumentException{
    if(canvasList.contains(canvas)){
      canvasList.remove(canvas);
    }else{
      throw new IllegalArgumentException("This canvas does not exist in the list of listeners");
    }

  }
  
  /**
   * Method used to clear out the canvas/reset it by creating a new empty list of drawing objects,
   * and then calls its update canvas method that broadcasts the resetting to all Views
   */
  @Override
  public void createNewDrawing() {
    drawList = new ArrayList<DrawingObject>();
    updateCanvas();
  }
  
  /**
   * Method used to update the canvas of the listeners, by redrawing all the shapes stored in 
   * the drawing object list, including any new ones added to it as the mouse moves
   */
  @Override
  public void updateCanvas() {
    for(View canvasListener : canvasList){
      canvasListener.updateView();
    }
  }
  
  /**
   * Keep track of what drawing option is currently selected in the View
   * @param drawMode
   */
  @Override
  public void updateDrawMode(String drawMode) {
    this.drawMode = drawMode;
  }

  /**
   * Add a new drawing object to the list of things to be drawn, by creating new drawing objects
   * and setting the shape,color and stroke parameters according to what is currently selected in 
   * the View
   * @param startPoint
   * @param endPoint
   */
  @Override
  public void addDrawingObject(Point startPoint,Point endPoint){
    DrawingObject currentDrawingObject = new DrawingObject();
    //set current Color;
    currentDrawingObject.setColor(currentColor);
    currentDrawingObject.setStroke(new BasicStroke(strokeValue));
    if(drawMode.equals("Line")){
      currentDrawingObject.setShape(new Line2D.Double(startPoint, endPoint));
    }else if(drawMode.equals("Rect")){
      currentDrawingObject.setShape(new Rectangle2D.Double(startPoint.getX(), startPoint.getY(), 
          endPoint.getX()-startPoint.getX(), endPoint.getY()-startPoint.getY()));
    }else if(drawMode.equals("Ellipse")){
      currentDrawingObject.setShape(new Ellipse2D.Double(startPoint.getX(), startPoint.getY(), 
          endPoint.getX()-startPoint.getX(), endPoint.getY()-startPoint.getY()));
    }else if(drawMode.equals("Pencil") || drawMode.equals("Eraser")){
      lastPencilPoint = null;
    }
    else if(drawMode.equals("Brush")){
      lastPencilPoint = null;
    }
    drawList.add(currentDrawingObject);
  }
  
  /**
   * A temporary drawing object to display what is being drawn as the cursor moves on the screen.
   * Used to display pencil, brush and eraser strokes on the View
   * @param startPoint
   * @param endPoint
   */
  @Override
  public void addTempDrawingObject(Point startPoint, Point endPoint){
    tempDrawingObject.setColor(currentColor);
    tempDrawingObject.setStroke(new BasicStroke(strokeValue));
    if(drawMode.equals("Line")){
      tempDrawingObject.setShape(new Line2D.Double(startPoint, endPoint));
    }else if(drawMode.equals("Rect")){
      tempDrawingObject.setShape(new Rectangle2D.Double(startPoint.getX(), startPoint.getY(), 
          endPoint.getX()-startPoint.getX(), endPoint.getY()-startPoint.getY()));
    }else if(drawMode.equals("Ellipse")){
      tempDrawingObject.setShape(new Ellipse2D.Double(startPoint.getX(), startPoint.getY(), 
          endPoint.getX()-startPoint.getX(), endPoint.getY()-startPoint.getY()));
    }else if(drawMode.equals("Pencil") || drawMode.equals("Eraser") || drawMode.equals("Brush")){
      if(lastPencilPoint==null){
        tempDrawingObject.setShape(new Line2D.Double(startPoint, startPoint));
      }else{
        tempDrawingObject.setShape(new Line2D.Double(lastPencilPoint, endPoint));
      }
      drawList.add(tempDrawingObject);
      tempDrawingObject = new DrawingObject();
      lastPencilPoint = endPoint;
    }
  }
  
  /**
   * An iterator for the list of shapes to be drawn, so that the View can access the shapes and 
   * draw them on the canvas
   * @return Iterator of DrawingObject
   */
  @Override
  public Iterator<DrawingObject> getDrawingObjectIterator(){
    Iterator<DrawingObject> ir = drawList.iterator();
    return ir;
  }
  
  /**
   * Method that returns the current temporary drawing object, so that the View can draw the 
   * temporary shape on the screen
   * @return DrawingObject
   */
  @Override
  public DrawingObject getTempDrawingObject() {
    return this.tempDrawingObject;
  }
  
  /**
   * Method to increase the stroke values of the shapes being drawn
   */
  @Override
  public void increaseStroke() {
     if(strokeValue==10.0f){
     strokeValue = 10.0f;
   }else{
     strokeValue+=1.0f;
   } 
  }
  
  /**
   * Method to decrease the stroke values of the shapes being drawn
   */
  @Override
  public void decreaseStroke() {
    if(strokeValue==0.0f){
      strokeValue = 0.0f;
    }else{
      strokeValue-=1.0f;
    } 
  }
  
  /**
   * Keep track of current color selected in the View
   * @param currentColor
   */
  @Override
  public void updateCurrentColor(Color currentColor) {
    this.currentColor = currentColor;
  }
  
  /**
   * Getter for the current color
   * @return Color 
   */
  @Override
  public Color getCurrentColor(){
    return this.currentColor;
  }
  
  /**
   * Getter for the current drawing mode
   * @return String
   */
  @Override
  public String getDrawMode(){
    return this.drawMode;
  }
  
  /**
   * Getter for the current Stroke Value
   * @return float
   */
  @Override
  public float getStrokeValue(){
    return this.strokeValue;
  }

}
