package com.nyu.pqs.canvasapp;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas implements View {
  Model backEnd;
  private JFrame frame;
  private JPanel topPanel;
  private JButton startButton;
  private JButton saveButton;
  private JPanel drawingArea;
  
  
  
  public Canvas(Model backEnd){
    this.backEnd = backEnd;
    
    //initialise the frame
    initialiseFrame();
    
    //Initialise buttons
    initialiseButtons();
    
    //initialise drawingPanel
    drawingArea = new JPanel();
    JLabel tempLabel = new JLabel("This is the drawing area");
    drawingArea.add(tempLabel);
    
    //start or reset canvas
    topPanel = new JPanel(new GridLayout(1,2));
    topPanel.add(startButton);
    topPanel.add(saveButton);
    
    //start frame
    frame.add(topPanel, BorderLayout.PAGE_START);
    frame.add(drawingArea, BorderLayout.CENTER);
    frame.pack();
    frame.setSize(500, 500);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
  }
  
  @Override
  public void createNewDrawing() {
    backEnd.registerListener(this);

  }

  @Override
  public void updateView() {
    // TODO Auto-generated method stub

  }
  
  private void initialiseFrame(){
    frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setTitle("Canvas Application");
  }
  
  private void initialiseButtons(){
    startButton = new JButton("New Drawing/Reset Canvas");
    saveButton = new JButton("Save Drawing");
  }

}
