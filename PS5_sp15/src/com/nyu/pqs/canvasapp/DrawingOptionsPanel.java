package com.nyu.pqs.canvasapp;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingOptionsPanel extends JPanel {
  private List<JButton> drawingOptionsList = new ArrayList<JButton>();
  private JButton drawLine;
  private JButton drawRect;
  private JButton drawCircle;
  private JButton drawPencil;
  private JButton drawEraser;
  private JButton drawX;
  private JButton drawY;
  private JButton drawZ;
  
  public DrawingOptionsPanel(){
    this.setLayout(new GridLayout(4,4));
    drawLine =  new JButton();
    drawRect = new JButton();
    drawCircle = new JButton();
    drawPencil = new JButton();
    drawEraser = new JButton();
    drawX = new JButton();
    drawY = new JButton();
    drawZ = new JButton();
    this.add(drawLine);
    this.add(drawRect);
    this.add(drawCircle);
    this.add(drawPencil);
    this.add(drawEraser);
    this.add(drawX);
    this.add(drawY);
    this.add(drawZ);
    drawLine.setActionCommand("Line");
    drawLine.add(new JLabel("Line"));
    drawRect.setActionCommand("Rect");
    drawRect.add(new JLabel("Rect"));
    drawCircle.setActionCommand("Ellipse");
    drawCircle.add(new JLabel("Ellipse"));
    drawPencil.setActionCommand("Pencil");
    drawPencil.add(new JLabel("Pencil"));
    drawEraser.setActionCommand("Eraser");
    drawEraser.add(new JLabel("Eraser"));
  }
  
  public void addActionListenerToButtons(ActionListener myActionListener){
    drawLine.addActionListener(myActionListener);
    drawRect.addActionListener(myActionListener);
    drawCircle.addActionListener(myActionListener);
    drawPencil.addActionListener(myActionListener);
    drawEraser.addActionListener(myActionListener);
    drawX.addActionListener(myActionListener);
    drawY.addActionListener(myActionListener);
    drawZ.addActionListener(myActionListener);
  }
}
