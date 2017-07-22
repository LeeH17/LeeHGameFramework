package overhead;


import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * The window we will be using to view things from.
 * Start here for painting new things
 * Also pass key events from here.
 * @author Harrison Lee
 */
public class View extends JFrame {

	int defaultWidth, defaultHeight;
	Stage currStage;
	
	/**
	 * Set up the view/initial window
	 */
	public View(){
		//Set up some default resolution sizes
		defaultWidth = 800;
		defaultHeight = 600;
		setSize(defaultWidth, defaultHeight);
		
		//Other stuff
		setTitle("Game FrameWork - by Harrison Lee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	//Center the window
		setPreferredSize(new Dimension(800, 600));
	}
	
	/**
	 * Instantiate view with the given stage.
	 * @param newStage: The stage to begin at.
	 */
	/*public View(Stage newStage){
		this();
		switchStages(newStage);
	}*/
	
	/**
	 * Removes the old stage/component and replaces it with the new one
	 */
	public void switchStages(Stage newStage){
		if(currStage != null) //Just a check, first switch will hit
			this.remove(currStage.getStageView());
		
		currStage = newStage;
		StageView currStageView = currStage.getStageView();
		this.add(currStageView);
		
		//Give our component, stageView, focus so that it can pick up actions
		currStageView.setFocusable(true);
		currStageView.requestFocus();
		
		//Apply key binds
		currStage.setUpKeyBinds(currStageView);
	}
}











