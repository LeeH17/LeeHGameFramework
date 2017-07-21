import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class S_Mission extends Stage implements MouseListener{

	Mission currMsn;	//Keep track of current mission's data
	Unit[] heroes;
	
	//Controls
	Unit selected;
	
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
		heroes = new Unit[1];
		heroes[0] = new Unit(30, 200, 200, this);
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
		//If we have clicked a unit
		if(e.getComponent().getClass().equals(UnitView.class)){
			//Translate unit
			UnitView uv = (UnitView) e.getComponent();
			Unit clicked = uv.getUnit();
			
			//Then prepare to move the unit, assuming it
			//	wasn't one we had already selected
			if(!clicked.equals(selected)) {
				selected = clicked;
			} else {
				//De-select if we are clicking selected
				selected = null;
			}
			
		} else if(e.getComponent().getClass().equals(sView.getClass())){
			//We clicked on the map.
			//Move selected, assuming it is not null
			if(selected != null){
				selected.moveTo(e.getX(), e.getY());
			}
		} else {
			System.out.println("Error: Clicked unknown, \n"+e);
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
