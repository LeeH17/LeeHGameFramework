import java.util.ArrayList;

import javax.swing.JComponent;

public class S_MissionSelect extends Stage {

	SV_MissionSelect sView;
	
	ArrayList<Mission> missions;	//Keep track of available missions
					//TODO find way to load, likely from main
	
	public S_MissionSelect(View parentView) {
		// TODO Auto-generated constructor stub
		sView = new SV_MissionSelect(parentView);
		
		loadMissions();
	}
	
	/**
	 * TODO move mission creation elsewhere
	 */
	private void tempMakeMissions(){
		missions = new ArrayList<Mission>();
		missions.add(new Mission("Mission 1", "Msn1 Desc"));
		missions.add(new Mission("Mission 2", "Msn2 Desc"));
	}
	
	/**
	 * Load in all the missions available
	 */
	private void loadMissions(){
		//TODO replace this temp method with actually loading them in
		tempMakeMissions();
		
		//Go through all the missions, and: ...
		for(Mission msn: missions) {
			//Add to the scrolling list
			sView.addScrollButton(msn);
			
			//TODO and add map pins
		}
	}

	@Override
	public StageView getStageView() { return sView;	}

	@Override
	public void setUpKeyBinds(JComponent component) {
		// TODO Auto-generated method stub

	}

}
