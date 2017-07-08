import java.awt.Color;
import java.awt.Graphics;

public class SV_MissionSelect extends StageView {

	public SV_MissionSelect(View parentView) {
		super(parentView);

	}

	@Override
	public void paint(Graphics g) {
		//Draw background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
