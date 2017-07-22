package overhead;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * The view component of a stage
 * @author Harrison Lee
 */
public abstract class StageView extends JPanel{

	//Background background TODO store background art here!
	protected int winW, winH; //Define the window's width and height

	JLayeredPane layeredPane;
	
	public StageView(View parentView){
		setBounds(0, 0, parentView.getWidth(), parentView.getHeight());
		setFocusable(false);	//Ensure that this can't take focus from View (JFrame)
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));
		this.setOpaque(false);
		
		//Set up layeredPane as well
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(800, 600));
		layeredPane.setBounds(0, 0, getWidth(), getHeight());
		layeredPane.setLayout(null);
		layeredPane.setVisible(true);
		this.add(layeredPane);
		/*JLabel temp = new JLabel();
		temp.setBackground(Color.black);
		temp.setOpaque(true);
		temp.setBounds(100, 100, 100, 100);
		layeredPane.add(temp, new Integer(1));*/
		
		//Load other resources
		loadResources();
	}
	
	//Handle Graphics
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	/**
	 * Add to a specific layer, using JLayeredPane.
	 * Should always be used over other add(JComponent) methods,
	 *  to keep layer consistency.
	 * @param component: The component being added
	 * @param layer: The layer to add at
	 */
	public void addToLayer(Component component, int layer){
		Integer layerInt = new Integer(layer);
		layeredPane.add(component, new Integer(layer));
/*
		add(layeredPane);
		layeredPane.revalidate();
		layeredPane.repaint();
		revalidate();
		repaint();*/
	}
	
	/** Load all resources needed for this stage's view.
	 * Examples include background images */
	protected abstract void loadResources();
}
