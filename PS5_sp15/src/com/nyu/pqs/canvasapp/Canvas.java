package com.nyu.pqs.canvasapp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
  private DrawingAreaPanel drawingArea;
  private DrawingOptionsPanel drawingOptions;
  
  
  
  private class buttonClick implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
      String action = e.getActionCommand();
      switch(action){
      case "Start/Reset":
        backEnd.createNewDrawing();
        break;
      case "Save":
        saveImage();
      case "Line":
        backEnd.updateDrawMode("Line");
        break;
      }
      
    }
    
  }
  
  
  public Canvas(Model backEnd){
    this.backEnd = backEnd;
    //by default draw mode is a line
    backEnd.updateDrawMode("Line");
    
    //initialise the frame
    initialiseFrame();
    
    //Initialise buttons
    initialiseButtons();
    
    //initialise drawingPanel
    drawingArea = new DrawingAreaPanel();
    
    //Initialise drawingOptions
    drawingOptions = new DrawingOptionsPanel();
    drawingOptions.addActionListenerToButtons(new buttonClick());
    
    //start or reset canvas
    topPanel = new JPanel(new GridLayout(2,1));
    topPanel.add(startButton);
    topPanel.add(saveButton);
    
    //start frame
    frame.add(topPanel, BorderLayout.WEST);
    frame.add(drawingArea, BorderLayout.CENTER);
    frame.add(drawingOptions, BorderLayout.EAST);
    frame.pack();
    frame.setSize(700,700);
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
  
  @Override
  public void clearCanvas() {
  
  }
  
  @Override
  public void setDrawMode(String drawMode) {
    System.out.println("IM in setDrawMODe CANVAS");
    drawingArea.setDrawMode(drawMode);
    
  }
  
  private void initialiseFrame(){
    frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setTitle("Canvas Application");
  }
  
  private void initialiseButtons(){
    startButton = new JButton("New Drawing/Reset Canvas");
    startButton.addActionListener(new buttonClick());
    saveButton = new JButton("Save Drawing");
    saveButton.addActionListener(new buttonClick());
  }
  
  

  private void saveImage(){
    //save the image
  }

  @Override
  public void drawLine() {
    
  }

}
