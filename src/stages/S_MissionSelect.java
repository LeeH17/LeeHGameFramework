package stages;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

import gameplayElements.Mission;
import overhead.Stage;
import overhead.StageView;
import overhead.View;


public class S_MissionSelect extends Stage {

	SV_MissionSelect sView;
	
	ArrayList<Mission> missions;	//Keep track of available missions
				//Use arraylist so a custom mission-order can be used
					//TODO find way to load, likely from main
	
	Mission currMsn;
	JButton beginBtn;
	
	public S_MissionSelect(View parentView) {
		super(parentView);
		
		sView = new SV_MissionSelect(parentView);
		
		loadMissions();
		
		//Set up begin current mission button
		beginBtn = new JButton("Begin Mission!");
		beginBtn.setActionCommand("begin");
		beginBtn.addActionListener(this);
		beginBtn.setVisible(false);
		beginBtn.setBounds(	(int) (sView.getWidth()*0.6-70),
								(int) (sView.getHeight()*0.9),
								140, 40);
		//sView.add(beginBtn);
		sView.addToLayer(beginBtn, 1);
	}
	
	/**
	 * TODO move mission creation elsewhere
	 */
	private void tempMakeMissions(){
		missions = new ArrayList<Mission>();
		//missions.add(new Mission("Mission 1", "Msn1 Desc", 1));
		//missions.add(new Mission("Mission 2", "Msn2 Desc", 2));
		for(int i=0; i<20;i++){
			missions.add(new Mission("Mission " + i, 
					"Placeholder mission description " + i, i));
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

	@Override
	public StageView getStageView() { return sView;	}
	
	/* Don't do anything, all parts are gui handled */
	public void update(int deltaTime) {}

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
			sView.setActiveMission(missions.get(missionNumber));
			currMsn = missions.get(missionNumber);
			beginBtn.setVisible(true);
		} else if(e.getActionCommand().equals("begin")) {
			//parent.switchStages(new S_Mission(parent, currMsn));
			parent.switchStages(new S_Mission(parent));
		} else {
			System.out.println("Unknown ActionEvent: " + e);
		}
	}

}