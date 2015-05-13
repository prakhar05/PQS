package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A panel which contains all the color buttons and panel settings
 * @author pv594
 *
 */
public class ColorPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JButton red;
  private JButton blue;
  private JButton green;
  private JButton yellow;
  private JButton magenta;
  private JButton cyan;
  private JButton black;
  private JButton white;
  
  public ColorPanel(){
    this.setLayout(new GridLayout(4,2));
    this.setBackground(Color.RED);
    this.setOpaque(true);
    initButtons();
    setButtonIcons();
    setButtonCommands();
    addButtonsToPanel();
    
  }
  
  /**
   * Method to add ActionListener to all the buttons
   * @param myActionListener
   */
  public void addActionListenerToButtons(ActionListener myActionListener){
    red.addActionListener(myActionListener);
    blue.addActionListener(myActionListener);
    green.addActionListener(myActionListener);
    magenta.addActionListener(myActionListener);
    yellow.addActionListener(myActionListener);
    cyan.addActionListener(myActionListener);
    black.addActionListener(myActionListener);
    white.addActionListener(myActionListener);
  }
  
  /**
   * Method to initialise the buttons
   */
  private void initButtons(){
    red = new JButton();
    blue = new JButton();
    green = new JButton();
    yellow = new JButton();
    magenta = new JButton();
    cyan = new JButton();
    black = new JButton();
    white = new JButton();
  }
  
  /**
   * Method to set the buttons icons
   */
  private void setButtonIcons(){
    red.setIcon(new ImageIcon(getClass().getResource("/img/red.png")));
    blue.setIcon(new ImageIcon(getClass().getResource("/img/blue.png")));
    green.setIcon(new ImageIcon(getClass().getResource("/img/green.png")));
    yellow.setIcon(new ImageIcon(getClass().getResource("/img/yellow.png")));
    magenta.setIcon(new ImageIcon(getClass().getResource("/img/magenta.png")));
    cyan.setIcon(new ImageIcon(getClass().getResource("/img/cyan.png")));
    black.setIcon(new ImageIcon(getClass().getResource("/img/black.png")));
    white.setIcon(new ImageIcon(getClass().getResource("/img/white.png")));
  }
  
  /**
   * Method to set the button action commands
   */
  private void setButtonCommands(){
    red.setActionCommand("Red");
    blue.setActionCommand("Blue");
    green.setActionCommand("Green");
    yellow.setActionCommand("Yellow");
    magenta.setActionCommand("Magenta");
    cyan.setActionCommand("Cyan");
    white.setActionCommand("White");
    black.setActionCommand("Black");
  }
  
  /**
   * Method to add buttons to the color panel
   */
  private void addButtonsToPanel(){
    this.add(red);
    this.add(blue);
    this.add(green);
    this.add(yellow);
    this.add(magenta);
    this.add(cyan);
    this.add(black);
    this.add(white);
  }
}
