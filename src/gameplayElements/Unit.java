package gameplayElements;
import stages.S_Mission;

/**
 * To represent a given moving entity; hero or enemy
 * @author Harrison Lee
 */
public class Unit {
	UnitView uView;
	
	int x, y;
	
	int moveSpeed;
	
	int targetX;
	int targetY;
	String name;
	
	public Unit(String newName, int initX, int initY,
			S_Mission parent){
		//Set initial values
		moveSpeed = 30;
		targetX = initX;
		targetY = initY;
		x = initX;
		y = initY;
		name = newName;
		
		//Set up graphics
		uView = new UnitView(this);
		uView.setSize(30, 40);
		uView.setLocation(initX, initY);
		parent.getStageView().addToLayer(uView, 1);
		uView.addMouseListener(parent);
	}
	
	public void update(int deltaTime){
		if(targetX != x || targetY != y) {
			step(deltaTime);
		}
	}
	
	/**
	 * Sets the given position as the target to move to
	 * 	over time.
	 * @param x: The x pixel position
	 * @param y: The y pixel position
	 */
	public void moveTo(int x, int y){
		targetX = x;
		targetY = y;
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
		
		x = newX;
		y = newY;
		uView.setLocation(newX, newY);
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
	
	/* Simple getter functions */
	public String getName() { return name; }
}

	
	
	
	
	
	
	