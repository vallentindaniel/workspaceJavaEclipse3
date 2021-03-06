package com.Finalproj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar implements ActionListener {
    private JToolBar toolBar;
    private JButton outlinedRectangle;
    private JButton filledRectangle;
    private JButton outlinedOval;
    private JComboBox comboBox;
    private Draw draw;
    private ActualDrawPanel actualDrawPanel;


    public ToolBar(Draw draw) {
        this.draw = draw;
        this.initToolBar();

    }

    public void initToolBar() {

        toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        toolBar.setFloatable(false);
        toolBar.setLayout(new GridLayout(1, 3));
        toolBar.setSize(100,200);

        outlinedRectangle = new JButton("OutlinedRectangle");
        filledRectangle = new JButton("filledRectangle");
        outlinedOval = new JButton("outlinedOval");

        toolBar.add(outlinedOval);
        toolBar.add(filledRectangle);
        toolBar.add(outlinedRectangle);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == outlinedOval){
            actualDrawPanel.setCurrentShape(actualDrawPanel.OutlinedO);
        }
        else if(source == filledRectangle ){
           actualDrawPanel.setCurrentShape(actualDrawPanel.filledR);

        }
        else if(source == outlinedRectangle){
            actualDrawPanel.setCurrentShape(actualDrawPanel.OutlinedR);
        }
    }

    public JToolBar getToolBar() {
        return toolBar;
    }
}
