package units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

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
		moveSpeed = 10;
		reset = 300;	//Re-target every 0.3 seconds
		width = 30;
		height = 30;
		allied = false;
		maxHp = 40;
		hp = 30;
		dmg = 10;
		
		setUpUnitView(this, parent);
	}
	
	/**
	 * Zombies just moveTo() nearest hero
	 * @param targets: The list of targets to attack
	 */
	public void attack(ArrayList<Unit> targets){
		if(delay > 0)
			return;	//Don't react yet
		
		Unit target = getClosest(targets);
		
		moveTo(target.getX(), target.getY());
	}
	
	public void die(){
		parent.getZombies().remove(this);
		uView.getParent().remove(uView);
		//Will get collected by garbage collection?
		Ellipse2D bloodstain = new Ellipse2D.Double(x+10, y+10, 10, 10);
		parent.getStageView().drawEffect(
				new ParticleEffect(bloodstain, Color.GRAY, 300));
	}

	@Override
	public void paintUnit(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, statusOffset, getWidth(), getHeight());
	}

}
