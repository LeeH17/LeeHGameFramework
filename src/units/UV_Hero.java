package units;

import java.awt.Color;
import java.awt.Graphics;

public class UV_Hero extends UnitView{

	public UV_Hero(Unit newUnit) {
		super(newUnit);

		//Set hero values
		setSize(40, 60);
	}

	@Override
	protected void paintUnit(Graphics g) {
		//Place holder graphics
		g.setColor(Color.BLUE);
		g.fillRect(0, 15, getWidth(), getHeight()-10);
	}

}
