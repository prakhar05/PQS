package com.nyu.pqs.canvasapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A class that implements the View interface. Its responsible for creating and integrating all
 * the UI elements like Buttons, Panels and the Frame, while informing the Model about changes taking
 * place within the UI
 * @author pv594
 *
 */

public class Canvas implements View {
  Model backEnd;
  private JFrame frame;
  private JButton startButton;
  private DrawingAreaPanel drawingArea;
  private DrawingOptionsPanel drawingOptions;
  private ColorPanel colorPanel;
  private Point startPoint;
  private Point endPoint;
  
  /**
   * Private class that implements the ActionListener interface, for the buttons in the View.
   * It gets the action command from the button clicks and performs operations accordingly
   * @author KillEmAll
   *
   */
  private class buttonClick implements ActionListener{
    /**
     * Override actionPerformed method which gets the action command from the button clicks 
     * and performs operations accordingly.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      String action = e.getActionCommand();
      switch(action){
      case "Start/Reset":
        backEnd.createNewDrawing();
        break;
      case "Line":
        backEnd.updateDrawMode("Line");
        break;
      case "Rect":
        backEnd.updateDrawMode("Rect");
        break;
      case "Ellipse":
        backEnd.updateDrawMode("Ellipse");
        break;
      case "Pencil":
        backEnd.updateDrawMode("Pencil");
        break;
      case "Eraser":
        backEnd.updateDrawMode("Eraser");
        backEnd.updateCurrentColor(Color.WHITE);
        break;
      case "Brush":
        backEnd.updateDrawMode("Brush");
        break;
      case "IncreaseStroke":
        backEnd.increaseStroke();
        break;
      case "DecreaseStroke":
        backEnd.decreaseStroke();
        break;
      case "Red":
        backEnd.updateCurrentColor(Color.RED);
        break;
      case "Blue":
        backEnd.updateCurrentColor(Color.BLUE);
        break;
      case "Green":
        backEnd.updateCurrentColor(Color.GREEN);
        break;
      case "Yellow":
        backEnd.updateCurrentColor(Color.YELLOW);
        break;
      case "Black":
        backEnd.updateCurrentColor(Color.BLACK);
        break;
      case "Magenta":
        backEnd.updateCurrentColor(Color.MAGENTA);
        break;
      case "Cyan":
        backEnd.updateCurrentColor(Color.CYAN);
        break;
      case "White":
        backEnd.updateCurrentColor(Color.WHITE);
        break;
      }
    }
  }
  
  /**
   * Private class that extends JPanel. This class is used to build the drawing area and add 
   * MouseListener and MouseMotionListener Instances to the drawing panel.
   * @author pv594
   *
   */
  private class DrawingAreaPanel extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private boolean mouseReleased;
    
    public DrawingAreaPanel(){
      mouseReleased = false;
      this.setBackground(Color.WHITE);
      mouseHandler mouseHandlerInstance = new mouseHandler();
      this.addMouseListener(mouseHandlerInstance);
      this.addMouseMotionListener(mouseHandlerInstance);
    }
    /**
     * Private class that implements MouseListener, MouseMotionListener to detect the motion of
     * the mouse over the drawing area and also detect a press,drag and release of the mouse
     * @author KillEmAll
     *
     */
    private class mouseHandler implements MouseListener, MouseMotionListener{
      @Override
      public void mousePressed(MouseEvent e) {
        mouseReleased = false;
        startPoint = e.getPoint();
        backEnd.updateCanvas();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        mouseReleased = true;
        endPoint = e.getPoint();
        backEnd.addDrawingObject(startPoint,endPoint);
        backEnd.updateCanvas();
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        endPoint = e.getPoint();
        backEnd.addTempDrawingObject(startPoint, endPoint);
        backEnd.updateCanvas();
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
    
    /**
     * The paintComponent method that gets the temporary drawing object and all other drawing
     * objects from its model and draws them on the drawing panel
     */
    public void paintComponent(Graphics g) {
      Iterator<DrawingObject> drawingObjectIterator = backEnd.getDrawingObjectIterator();
      
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      if(!mouseReleased && backEnd.getTempDrawingObject()!=null){
        g2.setColor(backEnd.getTempDrawingObject().getColor());
        g2.setStroke(backEnd.getTempDrawingObject().getStroke());
        g2.draw(backEnd.getTempDrawingObject().getShape());
      }
      while(drawingObjectIterator.hasNext()){
        DrawingObject temp = drawingObjectIterator.next();
        g2.setStroke(temp.getStroke());
        g2.setColor(temp.getColor());
        g2.draw(temp.getShape());
      }
     }    
  }
  
  //Constructor
  public Canvas(Model backEnd){
    this.backEnd = backEnd;
    createNewDrawing();
    startPoint = null;
    endPoint = null;
    
    //initialise frame
    frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setTitle("Canvas Application");
    
    //initialise buttons
    startButton = new JButton("Reset Canvas");
    startButton.addActionListener(new buttonClick());
    startButton.setActionCommand("Start/Reset");
    
    //initialise drawingPanel
    drawingArea = new DrawingAreaPanel();
    
    //initialise drawingOptionsPanel
    drawingOptions = new DrawingOptionsPanel();
    drawingOptions.addActionListenerToButtons(new buttonClick());
    
    //initialise colorPanel
    colorPanel = new ColorPanel();
    colorPanel.addActionListenerToButtons(new buttonClick());
    
    //setup east panel
    JPanel westPanel = new JPanel(new GridLayout(2,1));
    westPanel.add(drawingOptions);
    westPanel.add(colorPanel);
    
    //start frame
    frame.add(startButton, BorderLayout.NORTH);
    frame.add(drawingArea, BorderLayout.CENTER);
    frame.add(westPanel, BorderLayout.WEST);
    frame.pack();
    frame.setSize(900,700);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  /**
   * Method to register the listener with its model when object is instantiated
   */
  @Override
  public void createNewDrawing() {
    backEnd.registerListener(this);
  }
  
  /**
   * Update the view with drawing object data from the model when asked to by the 
   * model by using the repaint method of the drawing area
   */
  @Override
  public void updateView() {
    drawingArea.repaint();
  }
}
