import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Stage to correspond to the Main Menu.
 * @author Harrison Lee
 */
public class S_MainMenu extends Stage {
	
	SV_MainMenu sView;		//The StageView that corresponds to the main menu
	MenuButton[] buttons;	//An array of all the buttons that can be selected
	int currButton;			//The current button
	
	public S_MainMenu(View parent){
		//TODO returning to main menu? Resizing?

		sView = new SV_MainMenu(parent);
		
		//Add Menu Buttons
		buttons = new MenuButton[3];
		
		buttons[0] = new MenuButton("Play!", parent, this, 0,
				1, 50, 300, 100, 40) {
			public void buttonFunction(){
				System.out.println(this.getLabel() + " ran");
				view.switchStages(new S_MissionSelect(view));
			}};
		
		buttons[1] = new MenuButton("Options", parent, this, 1, 
				1, 50, 350, 100, 40) {
			public void buttonFunction(){
				System.out.println(this.getLabel() + " ran");
			}};
		
		buttons[2] = new MenuButton("Exit", parent, this, 2,
				1, 50, 400, 100, 40) {
			public void buttonFunction(){
				System.out.println(this.getLabel() + " ran");
				//Assume everything is already saved, no need to prompt
				System.exit(0);
			}
		};
		
		sView.setSelected(buttons[0].getBounds());
	}

	/**
	 * Enact whatever effects the current selected button has
	 * @param selection: The id in buttons[i] of the selected button
	 */
	public void select() {	buttons[currButton].buttonFunction();	}
	
	/**
	 * A simple get function to retrieve a reference to the stageView
	 * @return returns the stageView
	 */
	public StageView getStageView()	{ return sView;	}
	//TODO figure out why new version of getStageView() was needed

	/**
	 * Set up all relevant key binds for this stage at the given component
	 * @param component: The component to add keybinds to. Likely to be sView
	 */
	public void setUpKeyBinds(JComponent component){
		//Note to self: TODO go into KeyStroke docs, getKeyEvent, for setting new binds
		
		//Move Keys up and down
			//With arrow keys
		addAction("SelectDown", new SelectDownAction("SelectDownAction"), 
				KeyStroke.getKeyStroke("DOWN"), component);
		addAction("SelectUp", new SelectUpAction("SelectUpAction"), 
				KeyStroke.getKeyStroke("UP"), component);
			//With WASD
		addAction("SelectDown", new SelectDownAction("SelectDownAction"), 
				KeyStroke.getKeyStroke("S"), component);
		addAction("SelectUp", new SelectUpAction("SelectUpAction"), 
				KeyStroke.getKeyStroke("W"), component);
		
		//Select a button
		addAction("Select", new SelectAction("SelectAction"), 
				KeyStroke.getKeyStroke("SPACE"), component);
		addAction("Select", new SelectAction("SelectAction"), 
				KeyStroke.getKeyStroke("E"), component);
		addAction("Select", new SelectAction("SelectAction"), 
				KeyStroke.getKeyStroke("ENTER"), component);
	}
	
		/** Available Actions **/
	//TODO consider not making a new class for every action?...
	private class SelectDownAction extends AbstractAction {
		public SelectDownAction(String name){	super(name); }
		public void actionPerformed(ActionEvent e) {
			//Change the selected button
			if(currButton >= buttons.length-1)
				currButton = 0;
			else
				currButton += 1;
				
			//Update where the selector is shown
			sView.setSelected(buttons[currButton].getBounds());
		}
	}
	private class SelectUpAction extends AbstractAction {
		public SelectUpAction(String name){	super(name); }
		public void actionPerformed(ActionEvent e) {
			//Change the selected button, accounting for the minimum and maximum
			if(currButton <= 0)
				currButton = buttons.length - 1;
			else
				currButton -= 1;
			
			//Update where the selector is shown 
			sView.setSelected(buttons[currButton].getBounds());
		}
	}
	private class SelectAction extends AbstractAction{
		public SelectAction(String name){		super(name); }
		public void actionPerformed(ActionEvent e) { select();	}
	}
}



