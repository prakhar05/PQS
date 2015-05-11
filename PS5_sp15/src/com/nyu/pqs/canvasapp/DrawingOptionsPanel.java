package com.nyu.pqs.canvasapp;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawingOptionsPanel extends JPanel {
  private List<JButton> drawingOptionsList = new ArrayList<JButton>();
  private JButton drawLine;
  private JButton drawRect;
  private JButton drawCircle;
  private JButton drawPencil;
  private JButton drawCurvedLine;
  private JButton drawX;
  private JButton drawY;
  private JButton drawZ;
  
  public DrawingOptionsPanel(){
    this.setLayout(new GridLayout(4,4));
    drawLine =  new JButton();
    drawRect = new JButton();
    drawCircle = new JButton();
    drawPencil = new JButton();
    drawCurvedLine = new JButton();
    drawX = new JButton();
    drawY = new JButton();
    drawZ = new JButton();
    this.add(drawLine);
    this.add(drawRect);
    this.add(drawCircle);
    this.add(drawPencil);
    this.add(drawCurvedLine);
    this.add(drawX);
    this.add(drawY);
    this.add(drawZ);
  }
  
  public void addActionListenerToButtons(ActionListener myActionListener){
    drawLine.addActionListener(myActionListener);
    drawRect.addActionListener(myActionListener);
    drawCircle.addActionListener(myActionListener);
    drawPencil.addActionListener(myActionListener);
    drawCurvedLine.addActionListener(myActionListener);
    drawX.addActionListener(myActionListener);
    drawY.addActionListener(myActionListener);
    drawZ.addActionListener(myActionListener);
  }
}
