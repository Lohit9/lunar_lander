import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class TerrainView extends JPanel implements Observer{

	private Polygon poly;

	public TerrainView(GameModel model) {
		// int[] xPoly = new int[21];
  //       int[] yPoly = new int[21];

        int xPoly[] = {150, 250, 325, 375, 450, 275, 100};
        int yPoly[] = {150, 100, 125, 225, 250, 375, 300};

  //       int absstepmax = 25;
  //       int ymin = 100 ;
  //       int ymax = 200;  
  //       int x = 700;
  //       int y = 200;

  //       Random r = new Random();
		// // int[] RandomNumbers = r.ints(20, 0, 2*absstepmax).toArray();

  //       for(int i=0; i<=20; i++){
  //       	y = y + (r.nextInt(2*absstepmax) - absstepmax - 1); 
  //       	y = Math.max(ymin, Math.min(ymax, y));
  //       	x = x + 5;
  //       	xPoly[i] = x;
  //       	yPoly[i] = y;
  //       }

        /*
			absstepmax = 25
			ymin = -100
			ymax = 100
			x = 0
			y = 5
			for i = 1, 20 do
			    y = y + (math.random(2*absstepmax) - absstepmax - 1)
			    y = math.max(ymin, math.min(ymax, y))
			    x = x + 5
			    print (x,y)
			end
        */

        poly = new Polygon(xPoly, yPoly, xPoly.length);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // do your superclass's painting routine first, and then paint on top of it.
        g.drawPolygon(poly);
    }

     @Override
    public void update(Observable o, Object arg) {

    }

}