import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Harrison Lee
 */
public abstract class StageView extends JPanel{

	//Background background TODO store background art here!
	protected int winW, winH; //Define the window's width and height TODO changeable?
	
	//The parent frame, for adding additional components to.
	protected JFrame pFrame;
	
	public StageView(int width, int height, JFrame parentFrame){
		resizeWindow(width, height);
		pFrame = parentFrame;
	}
	
	//Handle Graphics
	public void paint(Graphics g){
		displayBackground(g);
	}
	
	/**
	 * Handle all background display stuff here (separate from UI overlays)
	 * @param g: The graphics object paint() is acting through.
	 */
	private void displayBackground(Graphics g){
		//Create background
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, winW, winH);
	}
	
	protected void resizeWindow(int width, int height){
		winW = width;
		winH = height;
		this.setSize(winW, winH);
	}
}
