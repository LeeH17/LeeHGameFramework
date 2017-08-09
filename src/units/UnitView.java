package units;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

/**
 * To represent the view of a given unit.
 * Generic and relies on a unit's paintUnit() to paint.
 * Also allows usage of built in mouse listener detection
 * @author Harrison Lee
 */
public class UnitView extends JLabel{
	//BufferedImage sprite
	//BufferedImage[] movementAnim
	//public void updateSpriteAnim
	//TODO so movement animation is controlled
	
	//Keep track of the corresponding unit in the model
	GameObject gameObject;
	Font font;
	
	public UnitView(Unit newUnit){
		super();
		
		//Set default values
		gameObject = newUnit;
		font = new Font(Font.DIALOG, Font.BOLD, 12);
	}
	
	/* Simple get function */
	public GameObject getGameObject() { return gameObject; }
	
	@Override
	public void paint(Graphics g){
		//Change to improve potential, use newer form
		Graphics2D g2 = (Graphics2D) g;
		
		gameObject.paint(g2);
		
		super.paint(g);
	}
}




