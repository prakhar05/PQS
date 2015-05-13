package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
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
  private JButton drawBrush;
  private JButton increaseStroke;
  private JButton decreaseStroke;
  
  public DrawingOptionsPanel(){
    this.setLayout(new GridLayout(4,4));
    this.setBackground(Color.BLACK);
    this.setOpaque(true);
    drawLine =  new JButton();
    drawRect = new JButton();
    drawCircle = new JButton();
    drawPencil = new JButton();
    drawEraser = new JButton();
    drawBrush = new JButton();
    increaseStroke = new JButton();
    decreaseStroke = new JButton();
    this.add(drawLine);
    this.add(drawRect);
    this.add(drawCircle);
    this.add(drawPencil);
    this.add(drawEraser);
    this.add(drawBrush);
    this.add(increaseStroke);
    this.add(decreaseStroke);
    drawLine.setActionCommand("Line");
    drawLine.setIcon(new ImageIcon(getClass().getResource("img/line.png")));
    drawRect.setActionCommand("Rect");
    drawRect.setIcon(new ImageIcon(getClass().getResource("img/rectangle.png")));
    drawCircle.setActionCommand("Ellipse");
    drawCircle.setIcon(new ImageIcon(getClass().getResource("img/circle.png")));
    drawPencil.setActionCommand("Pencil");
    drawPencil.setIcon(new ImageIcon(getClass().getResource("img/pencil.png")));
    drawEraser.setActionCommand("Eraser");
    drawEraser.setIcon(new ImageIcon(getClass().getResource("img/eraser.png")));
    drawBrush.setActionCommand("Brush");
    drawBrush.setIcon(new ImageIcon(getClass().getResource("img/brush.png")));
    increaseStroke.setActionCommand(("IncreaseStroke"));
    increaseStroke.setIcon(new ImageIcon(getClass().getResource("img/increase.png")));    
    decreaseStroke.setActionCommand(("DecreaseStroke"));
    decreaseStroke.setIcon(new ImageIcon(getClass().getResource("img/decrease.png")));
    
  }
  
  public void addActionListenerToButtons(ActionListener myActionListener){
    drawLine.addActionListener(myActionListener);
    drawRect.addActionListener(myActionListener);
    drawCircle.addActionListener(myActionListener);
    drawPencil.addActionListener(myActionListener);
    drawEraser.addActionListener(myActionListener);
    drawBrush.addActionListener(myActionListener);
    increaseStroke.addActionListener(myActionListener);
    decreaseStroke.addActionListener(myActionListener);
  }
}
