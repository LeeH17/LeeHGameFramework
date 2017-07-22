package stages;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import overhead.StageView;
import overhead.View;
import units.Unit;

public class SV_Mission extends StageView {

	Unit selected;
	
	public SV_Mission(View parentView) {
		super(parentView);
		this.setLayout(null);
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
		
		//Draw selection cursor
		if(selected != null){
			g.setColor(Color.WHITE);
			//Change stroke thickness
			Graphics2D gThick = (Graphics2D) g;
			gThick.setStroke(new BasicStroke(3));
			gThick.drawRect(selected.getX()-1, selected.getY()-1,
					selected.getWidth()+1, selected.getHeight()+1);
		}
		
		super.paint(g);
	}

	@Override
	protected void loadResources() {
		// TODO Auto-generated method stub

	}

}
