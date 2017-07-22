package stages;
import java.awt.Color;
import java.awt.Graphics;

import overhead.StageView;
import overhead.View;

public class SV_Mission extends StageView {

	public SV_Mission(View parentView) {
		super(parentView);
		this.setLayout(null);
	}
	
	@Override
	public void paint(Graphics g){
		//Draw background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		super.paint(g);
	}

	@Override
	protected void loadResources() {
		// TODO Auto-generated method stub

	}

}
