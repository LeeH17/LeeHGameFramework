package stages;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import gameplayElements.Mission;
import overhead.Stage;
import overhead.StageView;
import overhead.View;
import units.*;

public class S_Mission extends Stage implements MouseListener{
	SV_Mission sView;
	
	Mission currMsn;	//Keep track of current mission's data
	ArrayList<Unit> heroes;
	ArrayList<Unit> zombies;
	
	//Hold two arraylists of all units in order,
	//	sorted by x and y positions
	ArrayList<Unit> xSorted, ySorted;
	
	//Controls
	Unit selected;
	
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
		
		xSorted = new ArrayList<Unit>();
		ySorted = new ArrayList<Unit>();
		
		currMsn = loadMission;
		//Load in heroes
			//Currently place holder heroes
		heroes = new ArrayList<Unit>();
		this.addUnit(new U_Hero("1", 200, 200, this));
		this.addUnit(new U_Hero("2", 300, 200, this));
		this.addUnit(new U_Hero("3", 400, 200, this));
		
		//Initialize zombies
		zombies = new ArrayList<Unit>();
		this.addUnit(new U_Zombie(400, 500, this));
		zombieCounter = 5;
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
		
		//Make more zombies if needed
		if(zombies.size() <= 0){
			zombieCounter++;
			for(int i=0; i<zombieCounter; i++){
				U_Zombie newZombie = new U_Zombie(
					(int) (Math.random()*300), 
					(int) (Math.random()*300), this);
				this.addUnit(newZombie);
			}
		}
		
			//Check Collisions
		//Go through by axis, for X to X+Width, check
		//	intersects, prevent movement.
		//	Check in one direction to prevent repeat checks.
		
		//For each unit in both xSorted and ySorted,  check collisions
		for(int i = 0; i < xSorted.size(); i++){
			//Set temporary variables
			int j = i+1;
			Unit curr = xSorted.get(i);
			Unit selected;
			Rectangle intersection;
			
			while(j<xSorted.size()){	//Go through X
				selected = xSorted.get(j);
				if(selected.getX() > (curr.getX() + curr.getWidth()))
					break;
				
				intersection = selected.intersection(curr);
				if(!intersection.isEmpty()){
					curr.collide(selected, intersection);
					selected.collide(curr, intersection);
				}
				j++;
			}
		}	
		//Repeat for y
		for(int i=0; i<ySorted.size(); i++) {
			int j = i+1;
			Unit curr = ySorted.get(i);
			Unit selected;
			Rectangle intersection;
			
			while(j<ySorted.size()){	//Go through Y
				selected = ySorted.get(j);
				if(selected.getY() > (curr.getY() + curr.getHeight()))
					break;
				
				intersection = selected.intersection(curr);
				if(!intersection.isEmpty()){
					curr.collide(selected, intersection);
					selected.collide(curr, intersection);
				}
				j++;
			}
		}
		
		//Re-sort xSorted and ySorted
		//TODO just swap to a sorted list? Potential optimization
		for(int x=0;x<xSorted.size()-1;x++){	//For each unit
			if(xSorted.get(x).getX() > xSorted.get(x+1).getX()){
				//If x is greater than the next value
				Unit temp = xSorted.get(x);
				xSorted.remove(x);
				int i = x+1;
				//Move x all the way up to where it is either
				//	smaller than the next or to the end of the list
				while(temp.getX() > xSorted.get(i).getX() && i < xSorted.size()-1){
					i++;
				}
				xSorted.add(i, temp);
			}
		}
		
		//Repeat for y
		for(int y=0;y<ySorted.size()-1;y++){
			if(ySorted.get(y).getY() > ySorted.get(y+1).getY()){
				Unit temp = ySorted.get(y);
				ySorted.remove(y);
				int i = y+1;
				while(temp.getY() > ySorted.get(i).getY() && i < ySorted.size()-1){
					i++;
				}
				ySorted.add(i, temp);
			}
		}
	}
	
	private void addUnit(Unit newUnit){
			//Sort into category list
		if(newUnit.getClass().equals(U_Hero.class))
			heroes.add(newUnit);
		else if(newUnit.getClass().equals(U_Zombie.class))
			zombies.add(newUnit);
		else
			System.err.println("AddUnit(): Unrecognized unit type");
		
			//Sort into position list.
		// Initial adds
		if(xSorted.size() == 0){
			xSorted.add(newUnit);
			ySorted.add(newUnit);
			return;
		}
		
		for(int i=0;i<=xSorted.size();i++){
			if(i == xSorted.size()){
				xSorted.add(newUnit);
				break;
			}
			if(newUnit.getX() >= xSorted.get(i).getX()){
				xSorted.add(i, newUnit);
				break;
			}
		}
		
		for(int i=0;i<=ySorted.size();i++){
			if(i == ySorted.size()){
				ySorted.add(newUnit);
				break;
			}
			if(newUnit.getY() >= ySorted.get(i).getY()){
				ySorted.add(i, newUnit);
				break;
			}
		}
		
		/*
		boolean xDone = false;	//Keep track of which have been sorted
		boolean yDone = false;
		for(int i = 0; i < ySorted.size(); i++){
			//Both xSorted and ySorted are the same size,
			//	the total number of units.
			if(!xDone && newUnit.getX() >= xSorted.get(i).getX()){
				xSorted.add(i, newUnit);
				xDone = true;
				//Will assign i as index, shift old back
			}
			if(!yDone && newUnit.getY() >= ySorted.get(i).getY()){
				ySorted.add(i, newUnit);
				yDone = true;
			}
			
			if(xDone && yDone) {
				break;
			}
		}*/
	}
	
	/**
	 * Remove the given unit from all lists holding it.
	 * @param unit
	 */
	public void removeUnit(Unit unit){
		if(unit.getClass().equals(U_Zombie.class)){
			zombies.remove(unit);
		} else if(unit.getClass().equals(U_Hero.class)){
			heroes.remove(unit);
		}
		xSorted.remove(unit);
		ySorted.remove(unit);
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
