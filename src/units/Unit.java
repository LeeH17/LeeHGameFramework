package units;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.SortedSet;

import stages.S_Mission;

/**
 * To represent a given moving entity; hero or enemy
 * @author Harrison Lee
 */
public abstract class Unit extends Rectangle{
	UnitView uView;
	StatusBar status;
	S_Mission parent;
	int statusOffset;
	int targetX;
	int targetY;

	int delay;	//Refers to delay until next action
	
	//Non-Unit-defined values
	String name;
	boolean allied;
	int moveSpeed;
	int reset;
	//Refers to value to reset delay to.
	//Essentially millisecond cooldown between actions
	
	int hp, maxHp;
	int dmg;	//The amount of damage that can be dealt
	
	/* Subclasses must assign: name, allied, moveSpeed,
	 * reset, width, height, maxHp, hp, dmg */
	public Unit(int initX, int initY,
			int initWidth, int initHeight,
			S_Mission newParent){
		super(initX, initY, initWidth, initHeight);
		//Set initial values
		targetX = initX;
		targetY = initY;
		delay = 0;
		parent = newParent;
		//Set default values
		statusOffset = 15;	//Redefine pending status type
	}

		/* Unit-type specific behaviors (model-side) */
	public abstract void attack(ArrayList<Unit> targets);
	public void die() {
		parent.removeUnit(this);
		uView.getParent().remove(status);
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
	public void collide(Unit other, Rectangle intersection){
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
	 * Use this to set up unit view, the default way
	 * @param newUView: The specific unit view to be used
	 * @param parent: The parent stage of the unit
	 */
	protected void setUpUnitView(Unit unit, S_Mission parent){
		uView = new UnitView(unit);
		parent.getStageView().addToLayer(uView, 1);
		uView.addMouseListener(parent);
		
		//Set location and size, accounting for status bar
		uView.setLocation(x, y);
		uView.setSize(width, height);
		
		//Repeat for status bar
		status = new StatusBar(unit);
		parent.getStageView().addToLayer(status, 2);
		status.setLocation(x, y-statusOffset);
		status.setSize(width, statusOffset);
	}
	
		/* Unit-type specific behaviors (view-side) */
	/* Subtypes will define how unit is painted */
	public abstract void paintUnit(Graphics2D g);
	
	/**
	 * Function to paint the status bar and name tag
	 * 	for this unit.
	 * Intended for usage in paint
	 * @param g: The graphics object we are painting with
	 */
	public void paintStatusBar(Graphics2D g){
		//Draw name tag
		g.setColor(Color.DARK_GRAY);
		if(allied) {
			//Note: drawString draws from the bottom left
			g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
			g.drawString(getName(), 0, statusOffset-3);
		}
		
		//Draw max HP bar
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.RED);
		g.drawLine(0, statusOffset-3, width, statusOffset-3);
		
		//Draw actual HP bar
		g.setColor(Color.GREEN);
		double hpPercent = hp;
		hpPercent = hpPercent/maxHp;
		g.drawLine(0, statusOffset-3,
				(int) (width*hpPercent), statusOffset-3);
	}
	//TODO private abstract void paintSupplyBar 
	
	/**
	 * At every game tick, do this. This default form
	 * 	currently only steps unit towards target position
	 * 	and updates delay.
	 * @param deltaTime
	 */
	public void update(int deltaTime){
		if(delay <= 0) {
			delay = reset;
		} else {
			delay -= deltaTime;
		}
		
		if(targetX != x || targetY != y) {
			step(deltaTime);
		}
	}
	
	/**
	 * Sets the given position as the target to move to
	 * 	over time.
	 * @param newX: The x pixel position
	 * @param newY: The y pixel position
	 */
	public void moveTo(int newX, int newY){
		targetX = newX;
		targetY = newY;
	}
	
	/**
	 * Step along towards the current target location.
	 * Calculate distance changed based off the time given.
	 * @param deltaTime: The change in time to move in.
	 */
	public void step(int deltaTime){
		//How much can we move in given time?
		int deltaPos = (int) (moveSpeed * deltaTime * 0.01);

		//Check if we have reached/not set target
		int newX = moveAlongAxis(x, deltaPos, targetX);
		int newY = moveAlongAxis(y, deltaPos, targetY);
		
		setXY(newX, newY);
	}
	
	/**
	 * Generically move along an axis (e.g. the x or y axis)
	 * 	from the starting position, with given rate of
	 * 	change, to the target position. 
	 * Does not overshoot the target position
	 * Returns the new position on the axis.
	 * @param start: The starting position
	 * @param deltaPos: The maximum change possible (∆ position)
	 * @param target: The target position
	 * @return The new position
	 */
	private int moveAlongAxis(int start, int deltaPos,
			int target){
		//Check if we have past or before the target,
		//	accounting for deltaPos.
		if(start > target + deltaPos){
			//Move down
			return start - deltaPos;
		} else if(start < target - deltaPos){
			//Move up
			return start + deltaPos;
		} else {
			//We are close enough to the target that we can
			//	reach it now.
			return target;
		}
	}
	
	/* Simple distance check. */ 
	public int getDistance(Unit target){
		return (int) Math.sqrt((target.x-this.x)*(target.x-this.x)
				+ (target.y-this.y)*(target.y-this.y));
	}
	/**
	 * Simple method to find closest potential target
	 * @param targets: An arraylist of potential targets to choose from
	 * @return the closest of the targets with > 0 hp
	 */
	public Unit getClosest(ArrayList<Unit> targets){
		if(targets.size() <= 0)
			return null;
		
		Unit target = targets.get(0);	//Initial target
		int shortestDistance = getDistance(target);
		int newDistance;
		if(targets.size() > 1) {
			for(int i=1;i<targets.size();i++){ //Already got heroes[0]
				if(target.getHp() > 0) {
					newDistance = getDistance(targets.get(i));
					if(shortestDistance > newDistance) {
						//Replace with new target if new target is closer
						//TODO consider replacing with "threat levels"? sound/decoys?
						shortestDistance = newDistance;
						target = targets.get(i);
					}
				}
			}
		}
		return target;
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
		status.setLocation(newX, newY - statusOffset);
	}
	
	/* Simple getter functions */
	public String getName() { return name;	}
	public int getHp()		{ return hp;	}
	public int getStatusOffset() 	{ return statusOffset; }
	public boolean isControllable()	{ return allied; }
	
}

	
	
	
	
	
	
	