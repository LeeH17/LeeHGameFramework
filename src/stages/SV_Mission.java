package stages;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import overhead.StageView;
import overhead.View;
import units.ParticleEffect;
import units.Unit;

public class SV_Mission extends StageView {

	Unit selected;
	
	ArrayList<ParticleEffect> particles;
	
	public SV_Mission(View parentView) {
		super(parentView);
		this.setLayout(null);
		particles = new ArrayList<ParticleEffect>();
	}
	
	/**
	 * Give the stage view a unit to draw a selection cursor
	 * over.
	 * Also exposes the unit, passing it by reference,
	 * 	allowing cursor information to auto-update
	 * 	cursor drawing information. 
	 * @param unit: A reference to the selected unit
	 */
	public void selectedUnit(Unit unit){
		selected = unit;
		//NOTE: a reference, auto-updates but rep-exposure
	}
	/**
	 * Deselect the unit, turning it null again and
	 * 	removing the selection cursor.
	 */
	public void deselect(){
		selected = null;
	}
	
	@Override
	public void paint(Graphics g){
		//Draw background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());

		//Change stroke thickness
		Graphics2D gThick = (Graphics2D) g;
		gThick.setStroke(new BasicStroke(3));
		//Draw selection cursor
		if(selected != null){
			g.setColor(Color.WHITE);
			gThick.drawRect(selected.getX()-1, selected.getY()-1,
					selected.getWidth()+1, selected.getHeight()+1);
		}
		
		//Paint all particle effects queued
		for(ParticleEffect effect: particles){
			effect.paint(gThick);
			effect.reduceDuration(10);
		}
		//Clear expired particle effects queue
		//particles.clear();
		for(int i=particles.size()-1;i >= 0; i-= 1){
			if(particles.get(i).getDuration() < 0){
				particles.remove(i);
			}
		}
		
		super.paint(g);
	}
	
	/**
	 * Add to the list of particles to be drawn next repaint()
	 * @param particle: The particle effect to be drawn
	 */
	public void drawEffect(ParticleEffect particle){
		particle.setId(particles.size());
		particles.add(particle);
	}

	@Override
	protected void loadResources() {
		// TODO Auto-generated method stub

	}

}
