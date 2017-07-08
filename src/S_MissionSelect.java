import javax.swing.JComponent;

public class S_MissionSelect extends Stage {

	SV_MissionSelect sView;
	
	public S_MissionSelect(View parentView) {
		// TODO Auto-generated constructor stub
		sView = new SV_MissionSelect(parentView);
	}

	@Override
	public StageView getStageView() { return sView;	}

	@Override
	public void setUpKeyBinds(JComponent component) {
		// TODO Auto-generated method stub

	}

}
