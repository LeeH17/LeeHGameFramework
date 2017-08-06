package units;

import java.util.ArrayList;

public class UnitManager {

	//ArrayList of ArrayLists. Primary data structure.
	//	Turned into and used as a 2d array by x + y*width.
	//	Each index of the 2d array will represent
	//	a 100x100 pixel square on the game's stage.
	ArrayList<ArrayList<Unit>> units;
	
	int width, height;	//Keep track of max indices
	static double divisions = 100;	//The division size
	
	//Also keep track of some special groups
	ArrayList<U_Hero> heroes;
	ArrayList<U_Zombie> zombies;
	
	/**
	 * Construct the unit manager with given play area size
	 * @param windowWidth: Pixel width of stage
	 * @param windowHeight: Pixel height of stage
	 */
	public UnitManager(int windowWidth, int windowHeight){
		//Calculate width and height, rounded up
		width = (int) Math.ceil(windowWidth / divisions);
		height = (int) Math.ceil(windowHeight / divisions);
		System.out.println(windowWidth + windowHeight);
		System.out.println(width + height);
		
		
		units = new ArrayList<ArrayList<Unit>>(width*height); 
	}
	
	
	public ArrayList<Unit> get(int x, int y){
		return units.get(x+(y*width));
	}
	
	public void addUnit(Unit newUnit){
		int x = (int) newUnit.getX()/100;
		int y = (int) newUnit.getY()/100;
		get(x, y).add(newUnit);
	}
	
	/* Simpler version of getAdjacent that can take a unit input */
	public ArrayList<Unit> getAdjacent(Unit source){
		//Translates the unit's pixel X and Y to chunk X and Y values
		return getAdjacent((int) source.getX()/100, (int) source.getY()/100);
	}
	
	/**
	 * Returns an ArrayList of all units in the 
	 *  surrounding chunks.
	 * @param x The x value of the source chunk
	 * @param y The y value of the source chunk
	 * @return An ArrayList<Unit> of all units in the 9 
	 * 	chunks around and including the given chunk coordinates.
	 */
	public ArrayList<Unit> getAdjacent(int x, int y){
		ArrayList<Unit> results = new ArrayList<Unit>();
		//The three above the source
		results.addAll(this.get(x-1,y-1));
		results.addAll(this.get(x  ,y-1));
		results.addAll(this.get(x+1,y-1));
		
		//The row of three the source is on
		results.addAll(this.get(x-1,y));
		results.addAll(this.get(x  ,y));
		results.addAll(this.get(x+1,y));
		
		//The three below the source
		results.addAll(this.get(x-1,y+1));
		results.addAll(this.get(x  ,y+1));
		results.addAll(this.get(x+1,y+1));
		
		return results;
	}
}















