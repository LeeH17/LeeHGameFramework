package units;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

/**
 * Separately paints status bar, to allow it to be on a
 * separate layer from the parent unit's UnitView.
 * Display's parent unit's information, such as name or HP.
 * 
 * @author HarrisonLee
 */
public class StatusBar extends JLabel{

	Unit parent;
	
	public StatusBar(Unit parentUnit){
		super();
		parent = parentUnit;
	}
	
	/**
	 * Delegate painting to parent unit.
	 * Also reduces exposure.
	 */
	@Override
	public void paint(Graphics g){
		parent.paintStatusBar((Graphics2D) g);
		super.paint(g);
	}
}