package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DrawingAreaPanel extends JPanel {
  private Point startPoint;
  private Point endPoint;
  private String drawMode;
  private List<Line2D> lineList = new ArrayList<Line2D>();
  private Line2D tempLine;
  private boolean mouseReleased;
  
  public DrawingAreaPanel(){
    mouseReleased = false;
    this.setBackground(Color.WHITE);
    tempLine = new Line2D.Double();
    drawMode = "Line";
    startPoint = null;
    endPoint = null;
    mouseHandler mouseHandlerInstance = new mouseHandler();
    this.addMouseListener(mouseHandlerInstance);
    this.addMouseMotionListener(mouseHandlerInstance);
  }
  
  private class mouseHandler implements MouseListener, MouseMotionListener{

    @Override
    public void mousePressed(MouseEvent e) {
      mouseReleased = false;
      startPoint = e.getPoint();
      repaint();
      System.out.println("Mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      mouseReleased = true;
      endPoint = e.getPoint();
      lineList.add(new Line2D.Double(startPoint,endPoint));
      repaint();
      System.out.println("Mouse released");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      endPoint = e.getPoint();
      tempLine.setLine(startPoint, endPoint);
      repaint();
      System.out.println("Mouse dragged");
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
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if(!mouseReleased){
      g2.draw(tempLine);
    }
    for(Line2D line : lineList){
      g2.setColor(Color.BLACK);
      g2.draw(line);
    }
   }    
}


