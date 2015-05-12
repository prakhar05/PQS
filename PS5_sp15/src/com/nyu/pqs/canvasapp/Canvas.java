package com.nyu.pqs.canvasapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Canvas implements View {
  Model backEnd;
  private JFrame frame;
  private JPanel westPanel;
  private JButton startButton;
  private JButton saveButton;
  private DrawingAreaPanel drawingArea;
  private DrawingOptionsPanel drawingOptions;
  private Point startPoint;
  private Point endPoint;
  private ColorPanel colorPanel;
//  private String drawMode;
  
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
  
  private class DrawingAreaPanel extends JPanel {
    private boolean mouseReleased;
    
    public DrawingAreaPanel(){
      mouseReleased = false;
      this.setBackground(Color.WHITE);
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
        backEnd.addShape(startPoint,endPoint);
        backEnd.addColor(colorPanel.getCurrentColor());
        repaint();
        System.out.println("Mouse released");
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        endPoint = e.getPoint();
        backEnd.addTempColor(colorPanel.getCurrentColor());
        backEnd.addTempShape(startPoint, endPoint);
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
    
    public void paintComponent(Graphics g) {
      Iterator<Shape> shapeIterator = backEnd.getShapeIterator();
      Iterator<Color> colorIterator = backEnd.getColorIterator();
      
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(backEnd.getTempColor());
      if(!mouseReleased && backEnd.getTempShape()!=null){
        g2.setColor(backEnd.getTempColor());
        g2.draw(backEnd.getTempShape());
      }
      while(shapeIterator.hasNext()){
        g2.setColor(colorIterator.next());
        g2.draw(shapeIterator.next());
      }
     }    
  }
  
  //Constructor
  public Canvas(Model backEnd){
    this.backEnd = backEnd;
    backEnd.registerListener(this);
//    drawMode = "Line";
    startPoint = null;
    endPoint = null;
    
    //initialise the frame
    initialiseFrame();
    
    //Initialise buttons
    initialiseButtons();
    
    //initialise drawingPanel
    drawingArea = new DrawingAreaPanel();
    
    //Initialise drawingOptionsPanel
    drawingOptions = new DrawingOptionsPanel();
    drawingOptions.addActionListenerToButtons(new buttonClick());
    
    //Initialise colorPanel
    colorPanel = new ColorPanel();
    
    //start or reset canvas
    westPanel = new JPanel(new GridLayout(2,1));
    westPanel.add(startButton);
    westPanel.add(saveButton);
    
    //setup east panel
    JPanel eastPanel = new JPanel(new GridLayout(2,1));
    eastPanel.add(drawingOptions);
    eastPanel.add(colorPanel);
    
    //start frame
    frame.add(westPanel, BorderLayout.WEST);
    frame.add(drawingArea, BorderLayout.CENTER);
    frame.add(eastPanel, BorderLayout.EAST);
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
    

  }
  
  @Override
  public void clearCanvas() {
    drawingArea = null;
    drawingArea = new DrawingAreaPanel();
  }
  
  @Override
  public void setDrawMode(String drawMode){
//    this.drawMode = drawMode;
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
