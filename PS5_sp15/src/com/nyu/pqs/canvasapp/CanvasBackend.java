package com.nyu.pqs.canvasapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class CanvasBackend implements Model {
  private List<View> canvasList = new ArrayList<View>();
  private List<DrawingObject> drawList = new ArrayList<DrawingObject>();
  private String drawMode;
  private Point lastPencilPoint;
  private DrawingObject tempDrawingObject;
  private float strokeValue;
  
  
  public CanvasBackend(){
    drawMode = "Line";
    lastPencilPoint = null;
    tempDrawingObject = new DrawingObject();
    strokeValue = 1.0f;
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
    drawList = new ArrayList<DrawingObject>();
    for(View canvasListener : canvasList){
      canvasListener.updateView();
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
  public void addDrawingObject(Point startPoint,Point endPoint){
    DrawingObject currentDrawingObject = new DrawingObject();
    //set current Color;
    currentDrawingObject.setColor(canvasList.get(0).getColor());
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
  
  @Override
  public Iterator<DrawingObject> getDrawingObjectIterator(){
    Iterator<DrawingObject> ir = drawList.iterator();
    return ir;
  }
  
  @Override
  public void addTempDrawingObject(Point startPoint, Point endPoint){
    tempDrawingObject.setColor(canvasList.get(0).getColor());
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
  
  @Override
  public DrawingObject getTempDrawingObject() {
    return this.tempDrawingObject;
  }
  @Override
  public void increaseStroke() {
   if(strokeValue>=10.0f){
     strokeValue = 10.0f;
   }else{
     strokeValue+=1.0f;
   }
  }
  @Override
  public void decreaseStroke() {
    if(strokeValue<=0.0f){
      strokeValue = 0.0f;
    }else{
      strokeValue-=1.0f;
    }
  }

}
