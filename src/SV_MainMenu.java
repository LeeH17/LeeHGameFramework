import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * StageView corresponding to the main menu
 * @author Harrison Lee
 */
public class SV_MainMenu extends StageView{
	
	/**
	 * Small class to help with selecting buttons
	 * @author Harrison Lee
	 */
	private class Selector{
		Rectangle bounds[];	//Keep track of where the buttons are
		int currButton;		//Keep track of which button is selected now

		/** Set up with space to store the given number of buttons	*/
			//TODO consider if new buttons added? or just main menu?
		public Selector(int numButtons){
			//How many buttons do we expect
			bounds = new Rectangle[numButtons];
			currButton = 0;	//Start at the first button, play button
		}
		
		/** Two simple functions to move the selected button 
		 *	Keeping in bounds 
		*/
		public void moveUp() {
			if(currButton <= 0)
				currButton = bounds.length - 1;
			else
				currButton -= 1;
		}
		public void moveDown() 	{
			if(currButton >= bounds.length-1)
				currButton = 0;
			else
				currButton += 1;
		}
		
		/**
		 * Render the selector around the selected button.
		 * @param g: The graphics object used in SV.paint()
		 */
		public void render(Graphics g){
			//Settings
			g.setColor(Color.BLACK);
			
			//Collect current button's location/size and modify for selector
			int x = bounds[currButton].x - 2;
			int y = bounds[currButton].y - 2;
			int width = bounds[currButton].width + 2;
			int height = bounds[currButton].height + 2;
			
			//Draw the selector around it.
			g.drawRect(x, y, width, height);
		}
		
		/**
		 * Add a button to the selector, so that it can scroll to it.
		 * @param button: The JButton to allow selection at
		 * @param buttonNumber: The number in selector's array to put button
		 */
		public void addButton(MenuButton button){
			bounds[button.getID()] = button.getBounds();
		}
	}
	
	//Keep track of some custom fonts
	Font titleFont;
	Font buttonFont;
	
	Selector selected;		//The selector shows which button is selected
	MenuButton[] buttons;	//
	
	public SV_MainMenu(int width, int height, JFrame parentFrame){
		super(width, height, parentFrame);
		
		//Set up fonts TODO actually think about
		titleFont = new Font(Font.SERIF, Font.ITALIC, 32);
		buttonFont = new Font(Font.DIALOG, Font.BOLD, 16);
		
		//Set up selector
		selected = new Selector(3);
		
		//Add Menu Buttons
		buttons = new MenuButton[3];
		buttons[0] = new MenuButton("Play!", pFrame, 0, 50, 300, 100, 40) {
			public void buttonFunction(){
				System.out.println(this.getLabel() + " ran");
			}};
		selected.addButton(buttons[0]);
		
		buttons[1] = new MenuButton("Options", pFrame, 1, 50, 350, 100, 40) {
			public void buttonFunction(){
				System.out.println(this.getLabel() + " ran");
			}};
		selected.addButton(buttons[1]);
		
		buttons[2] = new MenuButton("Exit", pFrame, 2, 50, 400, 100, 40) {
			public void buttonFunction(){
				System.out.println(this.getLabel() + " ran");
			}
		};
		selected.addButton(buttons[2]);
	}
	
		/** Call as appropriate to move selector */
	public void moveSelectedUp()	{ selected.moveUp(); 	}
	public void moveSelectedDown()	{ selected.moveDown(); 	}
	
	
		/** When a button is selected */
	/** Helper function, for when keyboard selector is used */
	public void select() {	select(selected.currButton);	}
	/**
	 * Enact whatever effects the given selected button has
	 * @param selection: The id in buttons[i] of the selected button
	 */
	public void select(int selection){
		buttons[selection].buttonFunction();
	}
	
	
	
		/** __Graphics Management__ */
	@Override
	public void paint(Graphics g){
		//Do all the basic stuff first
		super.paint(g); //Does parent stuff, e.g StageView's displayBackground

		g.setColor(Color.BLACK);
		
		//Draw Title Screen
		int titleX = (int) (winW * 0.3);
		int titleY = (int) (winH * 0.2);
		
		g.setFont(titleFont);
		g.drawString(" Game!", titleX, titleY);
		
		//Buttons
		g.setFont(buttonFont);
		g.drawString("Main Menu", titleX + 100, titleY + 36);
		
		selected.render(g);
	}
}
