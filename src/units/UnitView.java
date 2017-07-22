package units;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

/**
 * To represent the view of a given unit
 * @author Harrison Lee
 */
public abstract class UnitView extends JLabel{
	//BufferedImage sprite
	//BufferedImage[] movementAnim
	//public void updateSpriteAnim
	//TODO so movement animation is controlled
	
	//Keep track of the corresponding unit in the model
	Unit unit;
	Font font;
	
	public UnitView(Unit newUnit){
		super();
		
		//Set default values
		unit = newUnit;
		font = new Font(Font.DIALOG, Font.BOLD, 12);
		setLocation(unit.getX(), unit.getY());
	}
	
	/* Simple get function */
	public Unit getUnit() { return unit; }
	
	@Override
	public void paint(Graphics g){
		paintUnit(g);
		
		paintStatusBar(g);
		
		super.paint(g);
	}
	
	/**
	 * Abstract function to force subclasses to define
	 * how to paint the character itself.
	 * Intended for usage in paint
	 * @param g: The graphics object we are painting with
	 */
	protected abstract void paintUnit(Graphics g);
	
	/**
	 * Function to paint the status bar and name tag
	 * 	for this unit.
	 * Intended for usage in paint
	 * @param g: The graphics object we are painting with
	 */
	protected void paintStatusBar(Graphics g){
		//Draw name tag
		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		g.drawString(unit.getName(), 0, 10);
		//TODO this is kinda hacky, magic numbers, adjust? Separate "status bar" object?
		
	}
	//TODO private abstract void paintSupplyBar 
}




