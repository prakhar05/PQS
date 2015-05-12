package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class CanvasBackend implements Model {
  private List<View> canvasList = new ArrayList<View>();
  private List<Shape> shapeList = new ArrayList<Shape>();
  private List<Color> colorList = new ArrayList<Color>();
   private String drawMode;
  private Shape tempShape;
  private Color tempColor;
  private Point lastPencilPoint;
  private Color lastPencilColor;
  
  
  public CanvasBackend(){
    drawMode = "Line";
    tempColor = Color.BLACK;
    lastPencilPoint = null;
    lastPencilColor = null;
  }
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
    for(View canvasListener : canvasList){
      canvasListener.updateView();
    }
  }

  @Override
  public void updateDrawMode(String drawMode) {
    this.drawMode = drawMode;
  }


  @Override
  public void draw(Point start, Point end) {
    for(View canvasListener : canvasList){
      canvasListener.drawLine();
    }
  }

  @Override
  public void addShape(Point startPoint,Point endPoint){
    if(drawMode.equals("Line")){
      shapeList.add(new Line2D.Double(startPoint, endPoint));
    }else if(drawMode.equals("Rect")){
      shapeList.add(new Rectangle2D.Double(startPoint.getX(), startPoint.getY(), 
          endPoint.getX()-startPoint.getX(), endPoint.getY()-startPoint.getY()));
    }else if(drawMode.equals("Ellipse")){
      shapeList.add(new Ellipse2D.Double(startPoint.getX(), startPoint.getY(), 
          endPoint.getX()-startPoint.getX(), endPoint.getY()-startPoint.getY()));
    }else if(drawMode.equals("Pencil") || drawMode.equals("Eraser")){
      lastPencilPoint = null;
      lastPencilColor = null;
    }
  }
  
  @Override
  public Iterator<Shape> getShapeIterator(){
    Iterator<Shape> ir = shapeList.iterator();
    return ir;
  }
  
  @Override
  public Iterator<Color> getColorIterator() {
    Iterator<Color> ir = colorList.iterator();
    return ir;
  }
  
  @Override
  public void addTempShape(Point startPoint, Point endPoint){
    if(drawMode.equals("Line")){
      tempShape = new Line2D.Double(startPoint, endPoint);
    }else if(drawMode.equals("Rect")){
      tempShape = new Rectangle2D.Double(startPoint.getX(), startPoint.getY(), 
          endPoint.getX()-startPoint.getX(), endPoint.getY()-startPoint.getY());
    }else if(drawMode.equals("Ellipse")){
      tempShape = new Ellipse2D.Double(startPoint.getX(), startPoint.getY(), 
          endPoint.getX()-startPoint.getX(), endPoint.getY()-startPoint.getY());
    }else if(drawMode.equals("Pencil") || drawMode.equals("Eraser")){
      if(lastPencilPoint==null){
        tempShape = new Line2D.Double(startPoint, startPoint);
      }else{
        tempShape = new Line2D.Double(lastPencilPoint, endPoint);
        shapeList.add(tempShape);
        colorList.add(tempColor);
      }
      lastPencilPoint = endPoint;
      lastPencilColor = tempColor;
    }
  }
  
  @Override
  public Shape getTempShape() {
    return tempShape;
  }
  @Override
  public void addColor(Color currentColor) {
    colorList.add(currentColor);
  }
  
  @Override
  public void addTempColor(Color tempColor) {
    this.tempColor = tempColor;
    
  }
  @Override
  public Color getTempColor() {
    return this.tempColor;
  }
}
