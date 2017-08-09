package units;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import stages.S_Mission;

/**
 * To represent all physical objects.
 * Examples of subclasses include: units, walls, barriers
 * 
 * @author HarrisonLee
 */
public abstract class GameObject extends Rectangle{
	int hp, maxHp;

	UnitView uView;
	S_Mission parent;
	
	public GameObject(	int initX, int initY,
					int initWidth, int initHeight,
					S_Mission newParent){
		super(initX, initY, initWidth, initHeight);
		
		//Set initial values
		parent = newParent;
		
	}
	
	public void die() {
		parent.remove(this);
		uView.getParent().remove(uView);
	}
	
	/**
	 * Enact the effects of colliding with another unit
	 * @param other: The other thing being collided with
	 * @param intersection: The overlap between the two
	 */
	public void collide(GameObject other, Rectangle intersection){
		//Basic effect is just to push away from each other,
		//	prevent walking through each other
		
		int newX = this.x;
		int newY = this.y;

		//First, find shortest push axis, x or y?
		int mod = 0;
		if(intersection.getWidth() < intersection.getHeight()){
			//X-Axis
			if(this.getX() < other.getX()){	//Before other
				mod = -1;
			} else {
				mod =  1;
			}
			
			newX += intersection.getWidth() * mod;
		} else {
			//Y-Axis
			if(this.getY() < other.getY()){	//Above other
				mod = -1;
			} else {
				mod =  1;
			}
			
			newY += intersection.getHeight() * mod;
		}
		setXY(newX, newY);
		//TODO pathfind around? Maybe unit specific
	}
	/**
	 * Use this to set up unit view, the default way.
	 * Must be called at end of lowest level object
	 * @param newUView: The specific unit view to be used
	 * @param parent: The parent stage of the unit
	 */
	protected void setUpUnitView(Unit unit, S_Mission parent){
		uView = new UnitView(unit);
		parent.getStageView().addToLayer(uView, 1);
		
		//Set location and size, accounting for status bar
		uView.setLocation(x, y);
		uView.setSize(width, height);
	}
	
	/* Delegate painting to subtypes. */
	public abstract void paint(Graphics2D g);
	
	/**
	 * Use this when changing x and y, to ensure change
	 * is applied to all parts, including:
	 * this, UnitView, and StatusBar
	 * @param x: The new X coordinate
	 * @param y: The new Y coordinate
	 */
	public void setXY(int newX, int newY){
		this.x = newX;
		this.y = newY;
		uView.setLocation(newX, newY);
	}
	
	/* Getter functions */
	public int getHp()				{ return hp;	}
	public boolean isControllable()	{ return false;	}
}







