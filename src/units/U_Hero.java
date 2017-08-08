package units;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import stages.S_Mission;

public class U_Hero extends Unit{

	int range;	//How far can this hero attack from?
	
	public U_Hero(String newName, int initX, int initY,
			S_Mission parent) {
		super(initX, initY, 40, 40, parent);
		
		//Set hero-specific values
		name = newName;
		moveSpeed = 30;
		reset = 500;	//0.5 seconds
		width = 40;
		height = 40;
		allied = true;
		maxHp = 100;
		hp = maxHp;
		dmg = 10;
		
		range = 300;
		
		setUpUnitView(this, parent);
	}
	
	/**
	 * Heroes shoot at targets
	 * @param: targets: The arraylist of zombies to shoot at
	 */
	public void attack(ArrayList<Unit> targets) {
		if(delay > 0 || targets.size() <= 0)
			return;		//Shooting delay or no targets
		//TODO shoot and move? -> implement movement modifier
		
		Unit target = getClosest(targets);
		if(getDistance(target) > range){
			return; //Can't reach/do anything yet.
			//TODO consider, range affect fire rate (+delay)/accuracy?
		}
		
		//target.takeDamage(dmg);
		//TODO: fix unit removal w/ collide, make less bouncy, and update sort
		
		//Draw bullet streak - just a simple yellow line with 0 duration
		Line2D bulletStreak = new Line2D.Double(
				this.getX(), this.getY(),
				target.getX(), target.getY());
		ParticleEffect effect = new ParticleEffect(bulletStreak, Color.YELLOW, 15);
		parent.getStageView().drawEffect(effect);
	}
	
	public void die(){
		parent.getHeroes().remove(this);
		uView.getParent().remove(uView);
		//Will get collected by garbage collection?
		Ellipse2D bloodstain = new Ellipse2D.Double(x+10, y+10, 20, 20);
		parent.getStageView().drawEffect(
				new ParticleEffect(bloodstain, Color.RED, 500));
	}
	
	public void paintUnit(Graphics2D g){
		//Place holder graphics
		g.setColor(Color.BLUE);
		g.fillRect(0, statusOffset, (int) getWidth(), (int) getHeight());
	}
}
