package units;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import stages.S_Mission;

/**
 * A generic wall. All it will do is collide with things.
 * 
 * @author HarrisonLee
 */
public class GO_Wall extends GameObject{

	public GO_Wall(int initX, int initY, int initWidth, int initHeight, S_Mission newParent) {
		super(initX, initY, initWidth, initHeight, newParent);
		setUpUnitView(this, parent);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, (int) getWidth(), (int) getHeight());
	}
	
	/* The wall doesn't move when collided with */
	public void collide(GameObject other, Rectangle intersection) {}
}
