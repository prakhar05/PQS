package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class DrawingAreaPanel extends JPanel {
  private Point startPoint;
  private Point currentPoint;
  private Point endPoint;
  private String drawMode;
  
  public DrawingAreaPanel(){
    this.setBackground(Color.WHITE);
    drawMode = "";
    startPoint = null;
    currentPoint = null;
    endPoint = null;
    mouseHandler mouseHandlerInstance = new mouseHandler();
    this.addMouseListener(mouseHandlerInstance);
    this.addMouseMotionListener(mouseHandlerInstance);
  }
  
  private class mouseHandler implements MouseListener, MouseMotionListener{

    @Override
    public void mousePressed(MouseEvent e) {
      startPoint = e.getPoint();
      System.out.println("Mouse pressed");
      repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      endPoint = e.getPoint();
      System.out.println("Mouse released");
      repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      currentPoint = e.getPoint();
      startPoint = currentPoint;
      System.out.println("Mouse dragged");
      repaint();
      
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
  }
  
  public void setDrawMode(String drawMode){
    this.drawMode = drawMode;
  }
  
  public void paintComponent(Graphics g) {
    System.out.println("in Paintcomponent");
    System.out.println("Draw mode is:" + drawMode);
    super.paintComponent(g);
    if (drawMode.equals("Line") && startPoint!=null && endPoint!=null) {
        g.setColor(Color.BLACK);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
}

