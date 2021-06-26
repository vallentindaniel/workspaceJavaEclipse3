package com.Finalproj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ActualDrawPanel extends JPanel  {
    final int OutlinedO= 1;
    final int OutlinedR=2;
    final int filledR=3;
    private  int currentShape=0;
    BufferedImage canavs;
    Graphics2D graphics2D;
    private Draw draw;
    int x1, x2, y1, y2;
    ArrayList<Integer> ShapeArray= new ArrayList<Integer>();



    public ActualDrawPanel(int ADPWidth, int ADPHeight, Draw draw){

        this.setSize(ADPWidth,ADPHeight);
        this.setLayout(null);
        setDoubleBuffered(true);
        setLocation(10, 10);
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();
       // this.addMouseListener(this);
      //  this.addMouseMotionListener(this);
        this.draw = draw;
        setVisible(true);

    }
//    public void DrawComponents(){
//      public MouseListener mouse = new MouseAdapter() {
//          @Override
//          public void mousePressed(MouseEvent e) {
//              x1=x2=e.getX();
//              y1=y2=e.getY();
//              repaint();
//          }
//
//          @Override
//          public void mouseReleased(MouseEvent e) {
//              x2=e.getX();
//              y2=e.getY();
//              repaint();
//
//          }
//      }
//
//    }


//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
   public void setCurrentShape(int currentShape) {
       this.currentShape = currentShape;
   }
}
