import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Main: Hold and binds the other classes in MVC pattern
 * Start here for code analysis?
 * @author Harrison Lee
 */

public class Main {

	Stage currStage;
	
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
			public void run(){
				//Set up the view with currStage
				View view = new View();//main.currStage);
				main.setCurrStage(new S_MissionSelect(view));
				view.switchStages(main.currStage);
				
				//Set up resizing stuff
				view.setMinimumSize(new Dimension(800, 600));
				view.pack();
				
				//Set up update/repainting timer
				Timer timer = new Timer(10, new ActionListener() {
					public void actionPerformed(ActionEvent e){
						//TODO run only when needed, don't update otherwise
						view.repaint();
					}
				});
				
				//Now we can open the frame/start updates timer 
				view.setVisible(true);
				timer.start();
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
}







