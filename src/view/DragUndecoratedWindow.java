/***********************************************************************************************
 * DRAG UNDECORATED WINDOW CLASS
 *
 * @author Stanislav Zorjan
 * @copyright (c)Copyright 2007 - 2008 - Stanislav Zorjan newcomponents@neobee.net
 *
 * As the name of the class says, this class drags undecorated or decorated window (JFrame)
 * Create new instance of the class and than call "onPress" method with "mousePressed" event
 * and then "moveWindow" with "mouseDragged" event.
 */
/***********************************************************************************************/
/*change package name to Your package name*/
package view;

import java.awt.Point;

import javax.swing.JFrame;

public class DragUndecoratedWindow {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	private int positionx;
	private int positiony;

	private JFrame frame;

	DragUndecoratedWindow(JFrame frame) {
		this.frame = frame;
	}

	public void moveWindow(java.awt.event.MouseEvent evt) {

		this.positionx = evt.getXOnScreen();
		this.positiony = evt.getYOnScreen();

		Point pt = frame.getLocationOnScreen();

		if (positiony < pt.getY() + 75) {

			if (this.positionx > this.x1) {
				this.x2 = this.positionx - this.x1;
				System.out.println("x " + positionx);
				this.frame.setLocation(this.frame.getX() + this.x2,
						this.frame.getY());
			} else if (this.positionx < this.x1) {
				this.x2 = this.x1 - this.positionx;
				System.out.println("x " + positionx);
				this.frame.setLocation(this.frame.getX() - this.x2,
						this.frame.getY());
			}
			if (this.positiony > this.y1) {
				this.y2 = this.positiony - this.y1;
				System.out.println("y " + positiony);
				this.frame.setLocation(this.frame.getX(), this.frame.getY()
						+ this.y2);
			} else if (this.positiony < this.y1) {
				this.y2 = this.y1 - this.positiony;
				System.out.println("y " + positiony);
				this.frame.setLocation(this.frame.getX(), this.frame.getY()
						- this.y2);
			}
			this.x1 = this.positionx;
			this.y1 = this.positiony;
		}
	}

	public void onPress(java.awt.event.MouseEvent evt) {
		this.x1 = evt.getXOnScreen();
		this.y1 = evt.getYOnScreen();
	}
}