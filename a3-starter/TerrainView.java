import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class TerrainView extends JPanel implements Observer{

	// private Polygon poly;
  Polygon terrain_view = new Polygon(); 
  java.util.List<Point>peaks = new ArrayList<Point>();
  ArrayList<Ellipse2D>circles = new ArrayList<>();
  int selected_circle_index = -1; 

	public TerrainView(GameModel model) {
       Random r = new Random();
       double peak_x;
       double peak_y;
       Point peak;
       for(int i=0;i<20;i++){
          peak_x = i * (700/19);
          peak_y = r.nextInt(100) + 100;
          peak = new Point((int)peak_x, (int)peak_y);
          peaks.add(peak);
       }
       for(Point p : peaks){
          peak_x = p.getX();
          peak_y = p.getY();
          terrain_view.addPoint((int)peak_x, (int)peak_y);
          Ellipse2D e = new Ellipse2D.Double(peak_x-15, peak_y-15, 30, 30);
          circles.add(e);
       }
       terrain_view.addPoint(700, 200);
       terrain_view.addPoint(0, 200);

       // addMouseListener(new MouseAdapter() {
       //      public void mousePressed(MouseEvent me) {
       //        for(int i=0; i<20 ; i++){
       //           if(circles.get(i).contains(me.getX(), me.getY())){
       //             // move_circle(i, y);
       //             selected_circle_index = i;
       //            }
       //        }
       //        repaint();
       //      }
       //  });

        // addMouseMotionListener(new MouseAdapter() {
        //     public void mouseDragged(MouseEvent e) {
        //        move_peak(selected_circle_index, e.getY());
        //        repaint();
        //        // move_circle(e.getY());
        //     }
        // });
  }

  public void move_circle(int i, int y){
    if (y < 0){
      y = 0;
    }
    if (y > 200){
      y = 200;
    }
    double x = peaks.get(i).x - 15;
    // x = Math.min(x, 200);
    // double y = peaks.get(i).y - 15;
    Ellipse2D e = new Ellipse2D.Double(x, y-15, 30, 30);
    circles.set(selected_circle_index, e);
  }

  public void move_peak(int i, int y){
    if(y<0){
      y=0;
    }
    if(y>200){
      y=200;
    }
    terrain_view.ypoints[i] = y;
    peaks.get(i).y = y;
    repaint();
  }

  protected void paintComponent(Graphics g) {
      super.paintComponent(g); // do your superclass's painting routine first, and then paint on top of it.
      Graphics2D g2 = (Graphics2D) g;
      // g2.drawPolygon(terrain_view);
      g2.setColor(Color.darkGray);
      g2.fillPolygon(terrain_view);
      g2.setColor(Color.gray);
      for(int i=0;i<20;i++){
        g2.draw(circles.get(i));
      }

  }

   @Override
  public void update(Observable o, Object arg) {

  }

}