package com.nyu.pqs.canvasapp;

import static org.junit.Assert.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.junit.Test;

public class DrawingObjectTest {

  @Test
  public void testHashCode() {
    DrawingObject obj = new DrawingObject();
    int hashcode1 = obj.hashCode();
    int hashcode2 = obj.hashCode();
    assertEquals(hashcode1,hashcode2);
    DrawingObject obj2 = new DrawingObject();
    DrawingObject obj3 = obj;
    int hashcode3 = obj2.hashCode();
    assertFalse(hashcode1==hashcode3);
    assertFalse(obj.equals(obj2));
    assertTrue(obj.equals(obj3));
    assertFalse(obj.equals(""));
  }

  @Test
  public void testDrawingObjectConstructorAndGetters() {
    DrawingObject obj = new DrawingObject();
    assertTrue(obj.getShape() instanceof Line2D.Double);
    assertTrue(obj.getColor().equals(Color.BLACK));
    assertTrue(obj.getStroke() instanceof Stroke);
  }

  @Test
  public void testDrawingObjectSettersAndParameterizedConstr() {
    DrawingObject obj = new DrawingObject(new Ellipse2D.Double());
    assertTrue(obj.getShape() instanceof Ellipse2D.Double);
    obj.setShape(new Rectangle2D.Double());
    assertTrue(obj.getShape() instanceof Rectangle2D.Double);
  }



  @Test
  public void testSetColor() {
    DrawingObject obj = new DrawingObject(new Ellipse2D.Double());
    obj.setColor(Color.BLUE);
    assertTrue(obj.getColor().equals(Color.BLUE));
  }

  @Test
  public void testSetStroke() {
    DrawingObject obj = new DrawingObject(new Ellipse2D.Double());
    obj.setStroke(new BasicStroke(2.0f));
    BasicStroke stroke = (BasicStroke)obj.getStroke();
    assertTrue(stroke.getLineWidth()==2.0f);
  }

}
