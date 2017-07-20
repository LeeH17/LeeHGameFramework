import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;


public class S_MissionSelect extends Stage {

	SV_MissionSelect sView;
	
	ArrayList<Mission> missions;	//Keep track of available missions
				//Use arraylist so a custom mission-order can be used
					//TODO find way to load, likely from main
	
	public S_MissionSelect(View parentView) {
		super(parentView);
		
		// TODO Auto-generated constructor stub
		sView = new SV_MissionSelect(parentView);
		
		loadMissions();
	}
	
	/**
	 * TODO move mission creation elsewhere
	 */
	private void tempMakeMissions(){
		missions = new ArrayList<Mission>();
		//missions.add(new Mission("Mission 1", "Msn1 Desc", 1));
		//missions.add(new Mission("Mission 2", "Msn2 Desc", 2));
		for(int i=0; i<20;i++){
			missions.add(new Mission("Mission " + i, "Msn desc" + i, i));
		}
	}
	
	/**
	 * Load in all the missions available
	 */
	private void loadMissions(){
		//TODO replace this temp method with actually loading them in
		tempMakeMissions();
		
		//Go through all the missions, and: ...
		for(int i=0;i<missions.size();i++) {
			Mission msn = missions.get(i);
			//Add to the scrolling list
			
			JButton newButton = new JButton(msn.getName());
			newButton.setActionCommand("msn" + i);
			newButton.addActionListener(this);
			
			/*MenuButton newButton = new MenuButton(msn.getName(),
					parent, this, msn.getID()) {
				public void buttonFunction() {
					System.out.println("Selected mission: "
							+ this.getLabel());
					//TODO fill out to put description in description pane
					//parent.describeMission(this.getName());
					//parent.sView.add
			}};*/
			sView.addToList(newButton);
			
			
			//TODO and add map pins
		}
	}
	
	/**
	 * Find and return the stored mission by the given name
	 * @param missionName: The name of the mission to find
	 * @return: Returns the first mission with given missionName
	 */
	public Mission getMission(String missionName){
		//TODO maybe consider hidden way to sort,
		//	rather than search each time
		//Simple O(n) search
		for(Mission msn: missions){
			if(msn.getName().equals(missionName)){
				return msn;
			}
		}
		System.err.println("Error: S_MissionSelect, "
				+ "searched for invalid mission: "
				+ missionName);
		return null;
	}
	
	public void describeMission(Mission mission){
		System.out.println(mission.getDesc());
	}

	@Override
	public StageView getStageView() { return sView;	}

	@Override
	public void setUpKeyBinds(JComponent component) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//First, determine command type
		if(e.getActionCommand().substring(0, 3).equals("msn")){
			//We have determined this is a mission selected command
			int missionNumber = Integer.parseInt(e.getActionCommand().substring(3));
			describeMission(missions.get(missionNumber));
		} else {
			System.out.println("Unknown ActionEvent: " + e);
		}
	}

}