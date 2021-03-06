
import java.awt.*;

public class OutlinedRectangleShape implements GraphicsShape{
	
	private int x, y, width, height;
	

	
	
    public OutlinedRectangleShape(int x1, int y1, int x2, int y2) {
	    x = Math.min(x1, x2);
	    y = Math.min(y1,y2);
	    width = Math.abs(x1-x2);
	    height = Math.abs(y1-y2);
    }




	@Override
    public void drawIt(Graphics g) {
		g.setColor(Color.BLACK);
	    g.drawRect(x,y,width,height);
    }
}
