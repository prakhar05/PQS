package com.nyu.pqs.canvasapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;

/**
 * Class for the drawing object which contains members shape,color and stroke
 * @author pv594
 *
 */
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
  
  /**
   * Setter function to set shape
   * @param Shape
   */
  public void setShape(Shape shape){
    this.shape = shape;
  }
  
  /**
   * Getter function to get shape
   */
  public Shape getShape(){
    return this.shape;
  }
  
  /**
   * Setter function to set color
   * @param Color
   */
  public void setColor(Color color){
    this.color = color;
  }
  
  /**
   * Getter function to get color
   * @return Color
   */
  public Color getColor(){
    return this.color;
  }
  
  /**
   * Setter function to set stroke
   * @param Stroke
   */
  public void setStroke(Stroke stroke){
    this.stroke = stroke;
  }
  
  /**
   * Getter function to get stroke
   * @return Stroke
   */
  public Stroke getStroke(){
    return this.stroke;
  }
  
  /**
   * Overriden object equals method
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof DrawingObject)) {
      return false;
    }
    DrawingObject drawingObj = (DrawingObject) o;
    return drawingObj.getShape().equals(shape) && 
        drawingObj.getColor().equals(color) && 
        drawingObj.getStroke().equals(stroke);
      
  }

  /**
   * Overriden object hashCode method
   */
  @Override
  public int hashCode() {
    int result = 23;
    result = 31 * result + shape.hashCode();
    result = 31 * result + color.hashCode();
    result = 31 * result + stroke.hashCode();
    return result;
  }
}
