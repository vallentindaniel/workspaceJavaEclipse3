
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar {
    private JToolBar toolBar;
    private JButton outlinedRectangle;
    private JButton filledRectangle;
    private JButton outlinedOval;
    private JComboBox comboBox;
    private Draw draw;
    private ActualDrawPanel actualDrawPanel;
    
    public static int k = 0;


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
        
        outlinedRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k=3;
			}
		});
        
        filledRectangle = new JButton("filledRectangle");
        filledRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k=2;
			}
		});
        outlinedOval = new JButton("outlinedOval");
        outlinedOval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k=1;
			}
		});

        toolBar.add(outlinedOval);
        toolBar.add(filledRectangle);
        toolBar.add(outlinedRectangle);


    }

   

    public JToolBar getToolBar() {
        return toolBar;
    }
}
