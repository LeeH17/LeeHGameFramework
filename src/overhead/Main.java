package overhead;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import stages.*;

/**
 * Main: Hold and binds the other classes in MVC pattern
 * Start here for code analysis?
 * @author Harrison Lee
 */

public class Main {

	static Stage currStage;
	
	public Main(){
		
	}
	
	/**
	 * Start program here
	 * @param args
	 */
	public static void main(String[] args) {
		//Start up model/controller
		Main main = new Main();
		
		//Manage view thread
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){	//Note: This runs once
				//Set up the view with currStage
				View view = new View();//main.currStage);
				main.setCurrStage(new S_Mission(view));
				view.switchStages(main.currStage);
				
				//Set up resizing stuff
				view.setMinimumSize(new Dimension(800, 600));
				view.pack();
				
				//Set up update/repainting timer
				Timer timer = new Timer(Main.updateRate(), new ActionListener() {
					public void actionPerformed(ActionEvent e){
						//TODO run only when needed, don't update otherwise
						view.repaint();
						view.currStage.update(Main.updateRate());
					}
				});
				
				//Now we can open the frame/start updates timer 
				view.setVisible(true);
				timer.start();	//TODO consider starting AFTER loading?
			}
		});
	}
	
	/**
	 * Sets a new stage as the curr stage
	 * @param newStage: the stage to switch to
	 */
	public void setCurrStage(Stage newStage){
		currStage = newStage;
	}
	
	/**
	 * Represent the update rate in delta time.
	 * Use this to ensure a central value is used.
	 * @return Returns the frequency of game updates in milliseconds.
	 */
	public static int updateRate()	{ return 10;	}
}







