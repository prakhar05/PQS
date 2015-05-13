package com.nyu.pqs.canvasapp;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import org.junit.Test;

public class CanvasBackendTest {

  @Test
  public void testCanvasBackend() {
    CanvasBackend backend = new CanvasBackend();
    assertTrue(backend instanceof CanvasBackend);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testRegisterListener() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    backend.registerListener(canvas);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testDeleteListener() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    backend.deleteListener(canvas);
    backend.deleteListener(canvas);
  }

  @Test
  public void testCreateNewDrawing() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    backend.createNewDrawing();
    assertTrue(!backend.getDrawingObjectIterator().hasNext());
  }


  @Test
  public void testUpdateDrawMode() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    backend.updateDrawMode("Circle");
    assertTrue(backend.getDrawMode().equals("Circle"));
  }

  @Test
  public void testAddDrawingObject() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    
    backend.updateDrawMode("Rect");
    backend.addDrawingObject(new Point(0,0), new Point(2,2));
    backend.updateDrawMode("Ellipse");
    backend.addDrawingObject(new Point(4,9), new Point(22,36));
    backend.updateDrawMode("Brush");
    backend.addDrawingObject(new Point(3,5), new Point(6,6));
    Iterator<DrawingObject> ir = backend.getDrawingObjectIterator();
    assertTrue(ir.next().getShape() instanceof Rectangle2D.Double);
  }

  @Test
  public void testGetDrawingObjectIterator() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    backend.updateDrawMode("Line");
    backend.addDrawingObject(new Point(0,0), new Point(2,2));
    assertTrue(backend.getDrawingObjectIterator().hasNext());
    assertTrue(backend.getDrawingObjectIterator().next().getShape() instanceof Line2D.Double);
    
  }

  @Test
  public void testAddTempDrawingObject() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    backend.updateDrawMode("Ellipse");
    backend.addTempDrawingObject(new Point(0,0), new Point(2,2));
    assertTrue(backend.getTempDrawingObject().getShape() instanceof Ellipse2D.Double);
    backend.updateDrawMode("Line");
    backend.addTempDrawingObject(new Point(4,5), new Point(8,8));
    assertTrue(backend.getTempDrawingObject().getShape() instanceof Line2D.Double);
    backend.updateDrawMode("Rect");
    backend.addTempDrawingObject(new Point(3,3), new Point(9,9));
    assertTrue(backend.getTempDrawingObject().getShape() instanceof Rectangle2D.Double);
    backend.updateDrawMode("Pencil");
    backend.addTempDrawingObject(new Point(3,3), new Point(9,9));
    assertTrue(backend.getTempDrawingObject().getShape() instanceof Line2D.Double);
  }

  @Test
  public void testIncreaseAndDecreaseStroke() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    backend.increaseStroke();
    assertTrue(backend.getStrokeValue()==2.0f);
    backend.decreaseStroke();
    assertTrue(backend.getStrokeValue()==1.0f);
    backend.decreaseStroke();
    assertTrue(backend.getStrokeValue()==0.0f);
    backend.decreaseStroke();
    assertTrue(backend.getStrokeValue()==0.0f);
    for(int i=0;i<13;i++){
      backend.increaseStroke();
    }
    assertTrue(backend.getStrokeValue()==10.0f);
  }


  @Test
  public void testUpdateCurrentColor() {
    CanvasBackend backend = new CanvasBackend();
    Canvas canvas = new Canvas(backend);
    backend.updateCurrentColor(Color.BLUE);
    assertTrue(backend.getCurrentColor()==Color.BLUE);
  }

}
