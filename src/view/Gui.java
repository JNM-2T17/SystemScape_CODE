package view;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Gui extends JFrame {
	private HashMap<String, JPanel> screens;
	private static Point point = new Point();
	private DragUndecoratedWindow dragger;
	public Gui() {
		super();
		screens = new HashMap<String, JPanel>();
		this.setUndecorated(true);
		dragger = new DragUndecoratedWindow(this);
		
		MouseAdapter mouseHandler = new MouseAdapter() {

//            private Point offset;
//
//            protected boolean isWithinBorder(MouseEvent e) {
//                Point p = e.getPoint();
//                Component comp = e.getComponent();
//                return p.x < 10;
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                Component comp = e.getComponent();
//                if (isWithinBorder(e)) {
//                    comp.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
//                } else {
//                    comp.setCursor(Cursor.getDefaultCursor());
//                }
//            }

            @Override
            public void mouseDragged(MouseEvent e) {
            	dragger.moveWindow(e);
//                if (offset != null) {
//                    Point pos = e.getLocationOnScreen();
//
//                    int x = pos.x - offset.x;
//                    int y = pos.y - offset.y;
//
//                    SwingUtilities.getWindowAncestor(e.getComponent()).setLocation(x, y);
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            	dragger.onPress(e);
//                if (isWithinBorder(e)) {
//                    Point pos = e.getComponent().getLocationOnScreen();
//                    offset = new Point(e.getLocationOnScreen());
//                    offset.x -= pos.x;
//                    offset.y -= pos.y;
//                }
            }

        };

        this.getContentPane().addMouseListener(mouseHandler);
        this.getContentPane().addMouseMotionListener(mouseHandler);
        
		this.setVisible(true);
		//this.setSize(new Dimension(1500, 1000));
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension dim = toolKit.getScreenSize();
		setSize((int)(dim.width/1.7), (int)(dim.height/1.5));//1.7, 1.5
	}
	
	public void setPanel(String key){
		this.getContentPane().removeAll();
		this.getContentPane().add(screens.get(key));
		this.revalidate();
		this.repaint();
	}
	
	public void register(JPanel pan, String key){
		screens.put(key, pan);
		
	}
	
}
