import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

/**
 * To represent the view of a given unit
 * @author Harrison Lee
 */
public class UnitView extends JLabel{
	//BufferedImage sprite
	//BufferedImage[] movementAnim
	//public void updateSpriteAnim
	//TODO so movement animation is controlled
	
	//Keep track of the corresponding unit in the model
	Unit unit;
	
	public UnitView(Unit newUnit){
		super();
		unit = newUnit;
	}
	
	/* Simple get function */
	public Unit getUnit() { return unit; }
	
	@Override
	public void paint(Graphics g){
		//Place holder graphics
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
		//g.drawImage...
	
		super.paint(g);
	}
}
