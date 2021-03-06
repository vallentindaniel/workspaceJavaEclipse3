package com.Finalproj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class Draw extends JFrame {
    private JPanel panel;
    private final int CONTENT_WIDTH = 1300;
    private final int CONTENT_HEIGHT = 700;
    private JToolBar toolBar;
    private ActualDrawPanel ADP;



    public Draw(){
       panel =new JPanel();
       panel.setBackground(Color.gray);
        this.setSize(CONTENT_WIDTH, CONTENT_HEIGHT);
        toolBar = (new ToolBar(this)).getToolBar();

        this.add(toolBar,BorderLayout.NORTH);

        this.add(panel);

        ADP = new ActualDrawPanel(1300,500,this);
        this.add(ADP);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }});
        setVisible(true);
    }



    public JPanel getPanel() {
        return panel;
    }


}