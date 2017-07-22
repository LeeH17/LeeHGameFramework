package units;

import java.awt.Color;
import java.awt.Graphics2D;

import stages.S_Mission;

public class U_Hero extends Unit{

	public U_Hero(String newName, int initX, int initY,
			S_Mission parent) {
		super(initX, initY, parent);
		
		//Set hero-specific values
		name = newName;
		moveSpeed = 30;
		reset = 500;	//0.5 seconds
		width = 40;
		height = 40;
		allied = true;
		
		setUpUnitView(this, parent);
	}
	
	public void paintUnit(Graphics2D g){
		//Place holder graphics
		g.setColor(Color.BLUE);
		g.fillRect(0, statusOffset, getWidth(), getHeight());
	}
}
