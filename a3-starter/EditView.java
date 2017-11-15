import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.geom.Rectangle2D;


// the editable view of the terrain and landing pad
public class EditView extends JPanel implements Observer {
	private int rect_x = 330;
	private int rect_y = 100;
	private int rect_w = 40;
	private int rect_h = 10;
	private boolean mouse_pressed_inside = false;
	MouseEvent pressed = null;
	Point location = new Point();
    GameModel model;
    Rectangle2D landing_pad = new Rectangle2D.Double(330, 100, 40, 10);
    Boolean inside_pad = false;
	// terrain related vars
	private Polygon poly;
    
    TerrainView tv;

    public EditView(GameModel model) {
        this.model = model;
        // want the background to be black
        setBackground(Color.lightGray);

        tv = new TerrainView(model);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
            	if(me.getClickCount() == 2){
            		moveBox(me.getX(), me.getY());
                    repaint();
                    return;
            	}
                if(landing_pad.contains(me.getX(), me.getY())){
                    location.setLocation(me.getX(), me.getY());
                    inside_pad = true;
                    repaint();
                    return;
                }
                for(int i=0; i<20 ; i++){
                  if(tv.circles.get(i).contains(me.getX(), me.getY())){
                    tv.selected_circle_index = i;
                    break;
                  }
                  else{
                    tv.selected_circle_index = -1;
                  }
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if(landing_pad.contains(e.getX(), e.getY())){
                    moveBox(e.getX(), e.getY());
                    repaint();
                    return;
                }
                if (tv.selected_circle_index != -1){
                    tv.move_peak(tv.selected_circle_index, e.getY());
                    tv.move_circle(tv.selected_circle_index, e.getY());
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if(inside_pad){
                    inside_pad = false;
                    moveBox(e.getX(), e.getY());
                    repaint();
                }
                tv.selected_circle_index = -1;
                repaint();
            }
        });

    }

    private boolean is_inside(int x, int y){
    	int maxX = rect_x + rect_w;
    	int minX = rect_x;
    	int maxY = rect_y;
    	int minY = rect_y - rect_h;

    	if (( (x <= maxX) && (x >= minX) ) && ( (y <= maxY) && (y >= minY) )){
    		return true;
    	}
    	return false;
    }

    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((rect_x!=x) || (rect_y!=y)) {
            repaint(rect_x,rect_y,rect_w+OFFSET,rect_h+OFFSET);
            rect_x = x;
            rect_y = y;
            // rect_w = rect_w + OFFSET;
            // rect_h = rect_h + OFFSET;
            repaint(rect_x, rect_y, rect_w+OFFSET,rect_h+OFFSET);
        }
    }

    public void moveBox(int x, int y){
        double next_x = x - (landing_pad.getWidth()/2);
        double next_y = y - (landing_pad.getHeight()/2);
        if (next_x < 0){
            next_x = 0;
        }
        if (next_x > 660){
            next_x = 660;
        }
        if(next_y < 0){
            next_y = 0;
        }
        if(next_y > 190){
            next_y = 190;
        }
        landing_pad.setRect(next_x, next_y, 40, 10);
        // repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // do your superclass's painting routine first, and then paint on top of it.
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fill(landing_pad);   
        g2.draw(landing_pad);
        g2.setColor(Color.darkGray);
        tv.paintComponent(g2);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}


