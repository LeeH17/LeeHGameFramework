package units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import stages.S_Mission;

/**
 * Generic zombie enemy
 * @author HarrisonLee
 */
public class U_Zombie extends Unit {

	public U_Zombie(int initX, int initY, S_Mission parent) {
		super(initX, initY, parent);
		
		//Set zombie-specific values
		name="";	//Unnamed zombie
		moveSpeed = 15;
		reset = 300;	//Re-target every 0.3 seconds
		width = 30;
		height = 30;
		allied = false;
		
		setUpUnitView(this, parent);
	}
	
	/**
	 * MoveTo() nearest hero
	 * @param heroes: The list of heroes to attack
	 */
	public void attack(U_Hero[] heroes){
		if(delay > 0)
			return;	//Don't react yet
		
		Unit target = heroes[0];	//Initial target
		int shortestDistance = getDistance(target);
		int newDistance;
		for(int i=1;i<heroes.length;i++){ //Already got heroes[0]
			newDistance = getDistance(heroes[i]);
			if(shortestDistance > newDistance) {
				//Replace with new target if new target is closer
				//TODO consider replacing with "threat levels"? sound/decoys?
				shortestDistance = newDistance;
				target = heroes[i];
			}
		}
		
		moveTo(target.getX(), target.getY());
	}

	@Override
	public void paintUnit(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, statusOffset, getWidth(), getHeight());
	}

}
