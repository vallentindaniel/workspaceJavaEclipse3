
import java.awt.*;

public class OutlinedOvalShape implements GraphicsShape {
	
	private int x, y, width, height;
	
	public OutlinedOvalShape(int x1, int y1, int x2, int y2) {
		    x = Math.min(x1, x2);
		    y = Math.min(y1,y2);
		    width = Math.abs(x1-x2);
		    height = Math.abs(y1-y2);
		    System.out.println("constructor oval");
	}
	
	
    @Override
    public void drawIt(Graphics g) {
    	
    	g.setColor(Color.BLACK);
	    g.drawOval(x,y,width,height);
	    
	    
 	   System.out.println("da oval");
    }

	
}
