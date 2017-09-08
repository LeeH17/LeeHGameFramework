package units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import stages.S_Mission;

/**
 * Generic zombie enemy
 * @author HarrisonLee
 */
public class U_Zombie extends Unit {
	
	//Keep track of units that have collided, to allow
	ArrayList<GameObject> adjObjects;
	
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
		adjObjects = new ArrayList<GameObject>();
		
		setUpUnitView(this, parent);
	}
	
	/* Custom, zombie specific update. */
	@Override
	public void update(int deltaTime){
		super.update(deltaTime);
		
		//Update adjacent units.
		Iterator<GameObject> itr = adjObjects.iterator();
		while(itr.hasNext()){
			GameObject current = itr.next();
			if(this.getDistance(current) > 65){
				itr.remove();
			}
		}
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
		
		if(adjObjects.contains(target)){
			target.takeDamage(dmg);
			
			Line2D swipe = new Line2D.Double(
					this.getCenterX(), this.getCenterY(),
					target.getCenterX(), target.getCenterY());
			parent.getStageView().drawEffect(
					new ParticleEffect(swipe, Color.YELLOW, 15));
		}
	}
	
	@Override
	public void collide(GameObject other, Rectangle intersection){
		super.collide(other, intersection);
		/*if(other.getClass().equals(U_Zombie.class)){
			U_Zombie test = (U_Zombie) other;
		} TODO: zombies pushing on each other -> buff dmg, buff enough to break walls with high enough clump size requirements 
		*/
		
		//Also: deal damage on collide to heroes/player controlled
		
		//Keep track of adjacent units
		if(!adjObjects.contains(other)){
			adjObjects.add(other);
		}
	
/*		if(other.isControllable()){
			//other.takeDamage(dmg);
			Ellipse2D swipe = new Ellipse2D.Double(
					intersection.getCenterX()-5, 
					intersection.getCenterY()-5,
					10, 10);
			parent.getStageView().drawEffect(
					new ParticleEffect(swipe, Color.YELLOW, 15));
					
		}*/
	}
	
	@Override
	public void die(){
		super.die();
		Ellipse2D bloodstain = new Ellipse2D.Double(x+10, y+10, 10, 10);
		parent.getStageView().drawEffect(
				new ParticleEffect(bloodstain, Color.GRAY, 300));
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, (int) getWidth(), (int) getHeight());
	}

}
