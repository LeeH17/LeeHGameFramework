import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Stage to correspond to the Main Menu.
 * @author Harrison Lee
 */
public class S_MainMenu extends Stage {
	
	SV_MainMenu sView;		//The StageView that corresponds to the main menu
	JButton[] buttons;	//An array of all the buttons that can be selected
	int currButton;			//The current button
	
	public S_MainMenu(View parent){
		super(parent);
		
		sView = new SV_MainMenu(parent);
		
		//Add Menu Buttons
		buttons = new JButton[3];
		//The play button, for entering the game
		buttons[0] = new JButton("Play!");					//Make the button
		buttons[0].setActionCommand(buttons[0].getText());	//Set as getText() to ensure consistency with select()
		buttons[0].addActionListener(this);					//Sets/adds the action listener, now uses ActionPerformed()
		buttons[0].setBounds(50, 300, 100, 40);				//Sets the position and size of the button
		sView.addToLayer(buttons[0], 1);					//Add to stageView
		
		//Options button, TODO make options menu
		buttons[1] = new JButton("Options");
		buttons[1].setActionCommand(buttons[1].getText());
		buttons[1].addActionListener(this);
		buttons[1].setBounds(50, 350, 100, 40);
		sView.addToLayer(buttons[1], 1);
		
		//Exit: Exit the game, close TODO "are you sure" prompt
		buttons[2] = new JButton("Exit");
		buttons[2].setActionCommand(buttons[2].getText());
		buttons[2].addActionListener(this);
		buttons[2].setBounds(50, 400, 100, 40);
		sView.addToLayer(buttons[2], 1);
		
		sView.setSelected(buttons[0].getBounds());
	}

	/**
	 * Enact whatever effects the current selected button has
	 * @param selection: The id in buttons[i] of the selected button
	 */
	public void select() {	
		actionPerformed( new ActionEvent(
				buttons[currButton],
				ActionEvent.ACTION_PERFORMED,
				buttons[currButton].getText()));
	}
	
	/**
	 * A simple get function to retrieve a reference to the stageView
	 * @return returns the stageView
	 */
	public StageView getStageView()	{ return sView;	}
	//TODO figure out why new version of getStageView() was needed

	/* Don't do anything, all parts are gui handled */
	public void update(int deltaTime) {}
	
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand() + " was pressed.");
		//Figure out which button was pressed
		if(e.getActionCommand().equals("Play!")){
			parent.switchStages(new S_MissionSelect(parent));
		} else if(e.getActionCommand().equals("Options")){
			
		} else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		} else {
			System.out.println("Unknown ActionEvent: " + e);
		}
	}
}



