package stages;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import gameplayElements.Mission;
import overhead.Stage;
import overhead.StageView;
import overhead.View;
import units.*;

public class S_Mission extends Stage implements MouseListener{

	Mission currMsn;	//Keep track of current mission's data
	Unit[] heroes;
	
	//Controls
	U_Hero selected;
	
	/**
	 * Placeholder testing constructor
	 * Makes up some empty mission
	 */
	public S_Mission(View viewParent){
		this(viewParent, new Mission("test", "test", -1));
	}
	
	public S_Mission(View viewParent, Mission loadMission) {
		super(viewParent);
		
		sView = new SV_Mission(viewParent);
		sView.addMouseListener(this);
		
		currMsn = loadMission;
		heroes = new Unit[3];
		heroes[0] = new U_Hero("1", 200, 200, this);
		heroes[1] = new U_Hero("2", 300, 200, this);
		heroes[2] = new U_Hero("3", 400, 200, this);
	}
	
	public void update(int deltaTime){
		for(Unit unit: heroes){
			unit.update(deltaTime);
		}
	}
	
	@Override
	public StageView getStageView() { return sView; }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpKeyBinds(JComponent component) {
		// TODO Hot keys for different heroes 1-6? commands for selected
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getComponent().getClass().equals(UV_Hero.class)){
			//Controllable hero clickd
			
			//Translate unit
			UV_Hero uv = (UV_Hero) e.getComponent();
			U_Hero clicked = (U_Hero) uv.getUnit();
			
			if(SwingUtilities.isLeftMouseButton(e)) {
				//Then prepare to move the unit, assuming it
				//	wasn't one we had already selected
				if(!clicked.equals(selected)) {
					selected = clicked;
				} else {
					//De-select if we are clicking selected
					selected = null;
				}
			}

		} else if(e.getComponent().getClass().equals(sView.getClass())){
			//Map clickd
			
			if(SwingUtilities.isRightMouseButton(e)) {
				//We right-clicked on the map.
				//Move selected, assuming it is not null
				if(selected != null){
					selected.moveTo(e.getX(), e.getY());
				}
			}
		} else {
			System.err.println("Error: Clicked unknown, \n"+e);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
