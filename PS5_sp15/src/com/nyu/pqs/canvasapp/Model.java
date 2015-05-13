package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Iterator;

public interface Model {
  public void registerListener(View canvas) throws IllegalArgumentException;
  public void deleteListener(View canvas) throws IllegalArgumentException;
  public void createNewDrawing();
  public void updateCanvas();
  public void updateDrawMode(String drawMode);
  public void addDrawingObject(Point startPoint, Point endPoint);
  public void addTempDrawingObject(Point startPoint, Point endPoint);
  public Iterator<DrawingObject> getDrawingObjectIterator();
  public DrawingObject getTempDrawingObject();
  public void increaseStroke();
  public void decreaseStroke();
}
