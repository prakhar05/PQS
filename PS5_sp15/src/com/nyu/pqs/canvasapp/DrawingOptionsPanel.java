package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class that contains the drawing options panel for the View. Contains the buttons
 * and all its settings
 * @author KillEmAll
 *
 */
public class DrawingOptionsPanel extends JPanel {
  private static final long serialVersionUID = 1L;
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
    initButtons();
    addButtonsToPanel();
    addButtonCommands();
    setButtonIcons();
  }
  
  /**
   * Method to add ActionListener to all the buttons
   * @param myActionListener
   */
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
  
  /**
   * Method to initialise the buttons
   */
  private void initButtons(){
    drawLine =  new JButton();
    drawRect = new JButton();
    drawCircle = new JButton();
    drawPencil = new JButton();
    drawEraser = new JButton();
    drawBrush = new JButton();
    increaseStroke = new JButton();
    decreaseStroke = new JButton();
  }
  
  /**
   * Method to add buttons to the panel
   */
  private void addButtonsToPanel(){
    this.add(drawLine);
    this.add(drawRect);
    this.add(drawCircle);
    this.add(drawPencil);
    this.add(drawEraser);
    this.add(drawBrush);
    this.add(increaseStroke);
    this.add(decreaseStroke);
  }
  
  /**
   * Method to set the button action commands
   */
  private void addButtonCommands(){
    drawLine.setActionCommand("Line");
    drawRect.setActionCommand("Rect");
    drawCircle.setActionCommand("Ellipse");
    drawPencil.setActionCommand("Pencil");
    drawEraser.setActionCommand("Eraser");
    drawBrush.setActionCommand("Brush");
    increaseStroke.setActionCommand(("IncreaseStroke"));
    decreaseStroke.setActionCommand(("DecreaseStroke"));
  }
  
  /**
   * Method to set the buttons icons
   */
  private void setButtonIcons(){
    drawLine.setIcon(new ImageIcon(getClass().getResource("/img/line.png")));
    drawRect.setIcon(new ImageIcon(getClass().getResource("/img/rectangle.png")));
    drawCircle.setIcon(new ImageIcon(getClass().getResource("/img/circle.png")));
    drawPencil.setIcon(new ImageIcon(getClass().getResource("/img/pencil.png")));
    drawEraser.setIcon(new ImageIcon(getClass().getResource("/img/eraser.png")));
    drawBrush.setIcon(new ImageIcon(getClass().getResource("/img/brush.png")));
    increaseStroke.setIcon(new ImageIcon(getClass().getResource("/img/increase.png")));    
    decreaseStroke.setIcon(new ImageIcon(getClass().getResource("/img/decrease.png")));
  }
}
