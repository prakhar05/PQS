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
  public void draw(Point start,Point end);
  public void updateCanvas();
  public void updateDrawMode(String drawMode);
  public void addShape(Point startPoint, Point endPoint);
  public void addColor(Color currentColor);
  public void addTempShape(Point startPoint, Point endPoint);
  public void addTempColor(Color tempColor);
  public Iterator<Shape> getShapeIterator();
  public Iterator<Color> getColorIterator();
  public Shape getTempShape();
  public Color getTempColor();
  
}
