
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class ActualDrawPanel extends JPanel  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int OutlinedO= 1;
    final int OutlinedR=2;
    final int filledR=3;
    
    private MyMouseHandler handler;
    private  int currentShape=0;
    
    BufferedImage canavs;
    Graphics2D graphics2D;
    private Draw draw;
     int x1, x2, y1, y2;
     
    ArrayList<GraphicsShape> shapeArray = new ArrayList<GraphicsShape>();



    public ActualDrawPanel(int ADPWidth, int ADPHeight, Draw draw){
    	
        this.setSize(ADPWidth,ADPHeight);
        this.setLayout(null);
        setDoubleBuffered(true);
        setLocation(10, 10);
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();
        this.draw = draw;
       
        setVisible(true);
        
        handler  = new MyMouseHandler();
    	this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
        
        
    }
    
  
   public void setCurrentShape(int currentShape) {
       this.currentShape = currentShape;
   }
   
   

   
   
   private class MyMouseHandler extends MouseAdapter
   {
     public void mousePressed( MouseEvent e )
     {
       x1 = e.getX();
       y1 = e.getY();

       x2=x1;
       y2=y1;
       
     }

     public void mouseReleased( MouseEvent e )
     {

       x2= e.getX();
       y2= e.getY();
       repaint();
       
     }
     
     
   }
   
   
   
   @Override
   public void paintComponent(Graphics g) { // desenare componenta
       super.paintComponent(g);

       doDrawing(g);
   }
   
   
   
   private void doDrawing(Graphics g) { 
	   
	   
	   //System.out.println(ToolBar.k);
	   if(ToolBar.k == 1) {
		   OutlinedOvalShape oval = new OutlinedOvalShape(x1,y1,x2,y2);
		   shapeArray.add(oval);
		 
	   }else if(ToolBar.k == 2) {
		   FilledRectangleShape fillRect = new FilledRectangleShape(x1,y1,x2,y2);
		   shapeArray.add(fillRect); 
	   }else if(ToolBar.k == 3) {
		   OutlinedRectangleShape rect = new OutlinedRectangleShape(x1,y1,x2,y2);
		   shapeArray.add(rect); 
	   }
	   
	   for(GraphicsShape gs : shapeArray) {
			  gs.drawIt(g); 
		   }
		   
	  
   }
   
   
  
   
   
   
}
