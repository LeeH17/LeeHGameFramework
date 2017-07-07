import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 * Stage to correspond to the Main Menu.
 * @author Harrison Lee
 */
public class S_MainMenu extends Stage {
	
	SV_MainMenu sView;
	
	public S_MainMenu(JFrame parentFrame){
		sView = new SV_MainMenu(800, 600, parentFrame); //Using default resolution
		//TODO returning to main menu? Resizing?
	}

	/**
	 * A simple get function to retrieve a reference to the stageView
	 * @return returns the stageView
	 */
	public StageView getStageView()	{return sView;	}
	//TODO figure out why new version of ^ method was needed

	/**
	 * Set up all relevant key binds for this stage at the given component
	 * @param component: The component to add keybinds to. Likely to be sView
	 */
	public void setUpKeyBinds(JComponent component){
		//Note to self: TODO go into KeyStroke docs, getKeyEvent, for setting new binds
		
		//Move Keys up and down
			//With arrow keys
		addAction("SelectDown", new SelectDown("SelectDownAction"), 
				KeyStroke.getKeyStroke("DOWN"), component);
		addAction("SelectUp", new SelectUp("SelectUpAction"), 
				KeyStroke.getKeyStroke("UP"), component);
			//With WASD
		addAction("SelectDown", new SelectDown("SelectDownAction"), 
				KeyStroke.getKeyStroke("S"), component);
		addAction("SelectUp", new SelectUp("SelectUpAction"), 
				KeyStroke.getKeyStroke("W"), component);
		
		//Select a button
		addAction("Select", new Select("SelectAction"), 
				KeyStroke.getKeyStroke("SPACE"), component);
		addAction("Select", new Select("SelectAction"), 
				KeyStroke.getKeyStroke("E"), component);
	}
	
	/** Available Actions **/
	//TODO consider not making a new class for every action?...
	private class SelectDown extends AbstractAction {
		public SelectDown(String name){	super(name); }
		public void actionPerformed(ActionEvent e) { sView.moveSelectedDown(); }
	}
	private class SelectUp extends AbstractAction {
		public SelectUp(String name){	super(name); }
		public void actionPerformed(ActionEvent e) { sView.moveSelectedUp(); }
	}
	private class Select extends AbstractAction{
		public Select(String name){		super(name); }
		public void actionPerformed(ActionEvent e) { sView.select();	}
	}
}



