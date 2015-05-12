package com.nyu.pqs.canvasapp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ColorPanel extends JPanel {
  private Color currentColor;
  private JButton red;
  private JButton blue;
  private JButton green;
  private JButton yellow;
  private JButton magenta;
  private JButton cyan;
  private JButton black;
  private JButton white;
  
  private class colorButtonClick implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
      String color = e.getActionCommand();
      switch(color){
      case "Red":
        currentColor = Color.RED;
        break;
      case "Blue":
        currentColor = Color.BLUE;
        break;
      case "Green":
        currentColor = Color.GREEN;
        break;
      case "Yellow":
        currentColor = Color.YELLOW;
        break;
      case "Black":
        currentColor = Color.BLACK;
        break;
      case "Magenta":
        currentColor = Color.MAGENTA;
        break;
      case "Cyan":
        currentColor = Color.CYAN;
        break;
      case "White":
        currentColor = Color.WHITE;
        break;
      default:
        currentColor = Color.BLACK;
      }
    }
  }
  
  public ColorPanel(){
    this.currentColor = Color.BLACK;
    this.setLayout(new GridLayout(4,2));
    red = new JButton();
    blue = new JButton();
    green = new JButton();
    yellow = new JButton();
    magenta = new JButton();
    cyan = new JButton();
    black = new JButton();
    white = new JButton();
    red.setBackground(Color.RED);
    blue.setBackground(Color.BLUE);
    green.setBackground(Color.GREEN);
    yellow.setBackground(Color.YELLOW);
    magenta.setBackground(Color.MAGENTA);
    cyan.setBackground(Color.CYAN);
    black.setBackground(Color.BLACK);
    white.setBackground(Color.WHITE);
    red.setOpaque(true);
    blue.setOpaque(true);
    green.setOpaque(true);
    yellow.setOpaque(true);
    magenta.setOpaque(true);
    cyan.setOpaque(true);
    black.setOpaque(true);
    white.setOpaque(true);
    red.setActionCommand("Red");
    blue.setActionCommand("Blue");
    green.setActionCommand("Green");
    yellow.setActionCommand("Yellow");
    magenta.setActionCommand("Magenta");
    cyan.setActionCommand("Cyan");
    white.setActionCommand("White");
    black.setActionCommand("Black");
    red.addActionListener(new colorButtonClick());
    blue.addActionListener(new colorButtonClick());
    green.addActionListener(new colorButtonClick());
    yellow.addActionListener(new colorButtonClick());
    magenta.addActionListener(new colorButtonClick());
    cyan.addActionListener(new colorButtonClick());
    black.addActionListener(new colorButtonClick());
    white.addActionListener(new colorButtonClick());
    this.add(red);
    this.add(blue);
    this.add(green);
    this.add(yellow);
    this.add(magenta);
    this.add(cyan);
    this.add(black);
    this.add(white);
  }
  
  public Color getCurrentColor(){
    return this.currentColor;
  }
  
  public void setCurrentColor(Color currentColor){
    this.currentColor = currentColor;
  }
}
