package units;

import stages.S_Mission;

public class U_Hero extends Unit{

	public U_Hero(String newName, int initX, int initY, S_Mission parent) {
		super(newName, initX, initY, parent);
		setUpUnitView(new UV_Hero(this), parent);
		moveSpeed = 30;
	}

}
