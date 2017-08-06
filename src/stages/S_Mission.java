package stages;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import gameplayElements.Mission;
import overhead.Stage;
import overhead.View;
import units.*;

public class S_Mission extends Stage implements MouseListener{
	SV_Mission sView;
	
	Mission currMsn;	//Keep track of current mission's data
	ArrayList<Unit> heroes;
	ArrayList<Unit> zombies;
	
	//Controls
	Unit selected;
	
	UnitManager manager;
	
	//TODO temp placeholder, for testing only
	int zombieCounter;
	
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
		manager = new UnitManager(sView.getWidth(), sView.getHeight());
		
		//Load in heroes
			//Currently place holder heroes
		heroes = new ArrayList<Unit>();
		heroes.add(new U_Hero("1", 200, 200, this));
		heroes.add(new U_Hero("2", 300, 200, this));
		heroes.add(new U_Hero("3", 400, 200, this));
		
		//Initialize zombies
		zombies = new ArrayList<Unit>();
		zombies.add(new U_Zombie(400, 500, this));
		zombieCounter = 1;
	}
	
	public void update(int deltaTime){
		//Update heroes
		for(Unit hero: heroes){
			hero.update(deltaTime);
			hero.attack(zombies);
		}
		
		//Update zombies
		for(Unit zombie: zombies) {
			zombie.update(deltaTime);
			zombie.attack(heroes);
		}
		if(zombies.size() <= 0){
			zombieCounter++;
			for(int i=0; i<zombieCounter; i++){
				U_Zombie newZombie = new U_Zombie(
					(int) (Math.random()*300), 
					(int) (Math.random()*300), this);
				zombies.add(newZombie);
			}
		}
	}
	
	@Override
	public SV_Mission getStageView() { return sView; }

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
		
		if(UnitView.class.isAssignableFrom(
				e.getComponent().getClass())){
			//UnitView clicked
			
			//Translate unit
			UnitView uv = (UnitView) e.getComponent();
			Unit clicked = (Unit) uv.getUnit();
			
			if(SwingUtilities.isLeftMouseButton(e)) {
				//Left click, TODO consider click type checked first 
				//	or clicked object first?
				
				//Then select the unit, assuming it
				//	wasn't one we had already selected
				if(!clicked.equals(selected)) {
					selected = clicked;
					sView.selectedUnit(selected);
				} else {
					//De-select if we are clicking selected
					selected = null;
					sView.deselect();
				}
			}

		} else if(e.getComponent().getClass().equals(sView.getClass())){
			//Map clicked
			
			if(SwingUtilities.isRightMouseButton(e)) {
				//We right-clicked on the map.
				
				//Move selected, assuming it is not null
				//	and is a controllable unit
				if(selected != null && selected.isControllable()){
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
	
		/* Simple getter functions*/
	public ArrayList<Unit> getZombies()	{ return zombies; }
	public ArrayList<Unit> getHeroes()	{ return heroes; }
}
