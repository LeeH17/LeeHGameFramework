import java.awt.Color;
import java.awt.Graphics;

public class SV_Mission extends StageView {

	public SV_Mission(View parentView) {
		super(parentView);
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
