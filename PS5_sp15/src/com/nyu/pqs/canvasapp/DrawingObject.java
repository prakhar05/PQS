package com.nyu.pqs.canvasapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;

public class DrawingObject {
  private Shape shape;
  private Color color;
  private Stroke stroke;
  
  public DrawingObject(){
    this.shape = new Line2D.Double();
    this.color = Color.BLACK;
    this.stroke = new BasicStroke();
  }
  
  public DrawingObject(Shape shape){
    this.shape = shape;
    this.color = Color.BLACK;
    this.stroke = new BasicStroke();
  }
  
  public void setShape(Shape shape){
    this.shape = shape;
  }
  
  public Shape getShape(){
    return this.shape;
  }
  
  public void setColor(Color color){
    this.color = color;
  }
  
  public Color getColor(){
    return this.color;
  }
  
  public void setStroke(Stroke stroke){
    this.stroke = stroke;
  }
  
  public Stroke getStroke(){
    return this.stroke;
  }
}
