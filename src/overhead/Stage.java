package overhead;

import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * A abstract for each stage
 * Each stage individually manages control/ActionEvents
 * @author Harrison Lee
 */
public abstract class Stage implements ActionListener{
	//The sView manages the visual part of the stage
	protected StageView sView;
	
	//Maintain a connection to the containing View object
	protected View parent;
	
	public Stage(View viewParent){
		parent = viewParent;
	}
	
	/**
	 * Update the model side of the game to change according
	 *  to the given amount of change in milliseconds
	 * @param deltaTime: The number of milliseconds that have
	 * 						passed since the last update
	 */
	public abstract void update(int deltaTime);
	
	/**
	 * Gives a reference to sView.
	 * This way, changes can be made to it during gameplay.
	 * Note!: Be careful, example of reference exposure
	 * @return A reference to the stageView
	 */
	public StageView getStageView() { return sView; }
	
	/**
	 * Set up all necessary key binds, adding the actions to the input map
	 */
	public abstract void setUpKeyBinds(JComponent component);
	
	/**
	 * Simple function to simplify adding an action
	 * @param actionName: The name of the action to be added
	 * @param action: The action to be added to the ActionMap
	 * @param keystroke: The key to trigger the action and be added to InputMap
	 * @param component: The JComponent to add the keybind to
	 */
	protected void addAction(String actionName, AbstractAction action, 
			KeyStroke keystroke, JComponent component){
		component.getInputMap().put(keystroke, actionName);
		component.getActionMap().put(actionName, action);
	}
}
