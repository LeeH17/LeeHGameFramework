package gameplayElements;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	Font font;
	
	public UnitView(Unit newUnit){
		super();
		unit = newUnit;
		font = new Font(Font.DIALOG, Font.BOLD, 12);
		this.setText(unit.getName());
	}
	
	/* Simple get function */
	public Unit getUnit() { return unit; }
	
	@Override
	public void paint(Graphics g){
		//Place holder graphics
		g.setColor(Color.BLUE);
		g.fillRect(0, 10, getWidth(), getHeight()-10);
		//g.drawImage...
	
		//Draw name tag
		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		g.drawString(unit.getName(), 0, 9);
		//TODO this is kinda hacky, magic numbers, adjust? Separate "status bar" object?
		
		super.paint(g);
	}
}




