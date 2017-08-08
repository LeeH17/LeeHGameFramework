package units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import stages.S_Mission;

/**
 * Generic zombie enemy
 * @author HarrisonLee
 */
public class U_Zombie extends Unit {
	
	public U_Zombie(int initX, int initY, S_Mission parent) {
		super(initX, initY, 30, 30, parent);
		
		//Set zombie-specific values
		name="";	//Unnamed zombie
		moveSpeed = 10;
		reset = 300;	//Re-target every 0.3 seconds
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
		moveTo((int) target.getX(), (int) target.getY());
	}
	
	@Override
	public void collide(Unit other, Rectangle intersection){
		super.collide(other, intersection);
		/*if(other.getClass().equals(U_Zombie.class)){
			U_Zombie test = (U_Zombie) other;
		} TODO: zombies pushing on each other -> buff dmg, buff enough to break walls with high enough clump size requirements 
		
		Also: deal damage on collide to heroes*/
	}
	
	@Override
	public void die(){
		super.die();
		Ellipse2D bloodstain = new Ellipse2D.Double(x+10, y+10, 10, 10);
		parent.getStageView().drawEffect(
				new ParticleEffect(bloodstain, Color.GRAY, 300));
	}

	@Override
	public void paintUnit(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, (int) getWidth(), (int) getHeight());
	}

}
