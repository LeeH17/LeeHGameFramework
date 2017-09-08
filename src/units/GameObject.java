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
	public void takeDamage(int dmg){
		hp -= dmg;
		if(hp <= 0) { //Check for death
			die();
		}
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
	protected void setUpUnitView(GameObject obj, S_Mission parent){
		uView = new UnitView(obj);
		parent.getStageView().addToLayer(uView, 1);
		
		//Set location and size, accounting for status bar
		uView.setLocation(x, y);
		uView.setSize(width, height);
		uView.setVisible(true);
	}
	
	/* Delegate painting to subtypes. */
	public abstract void paint(Graphics2D g);
	
	/**
	 * Simple distance check. Checks distance from this object to
	 * given other object
	 * @param target: The object to find distance to
	 * @return Returns the pixel distance to the other object if it is not null.
	 * 			Returns -1 if target is null.
	 * */
	public int getDistance(GameObject target){
		if(target == null){
			return -1;	//Fail state
		}
		
		return (int) Math.sqrt((target.getCenterX()-this.getCenterX())*(target.getCenterX()-this.getCenterX())
				+ (target.getCenterY()-this.getCenterY())*(target.getCenterY()-this.getCenterY()));
	}
	
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







