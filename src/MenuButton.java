import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * General menu button, to consolidate functions from Main Menu (TODO update)
 * Abstract, so will have to extend and add button functionality 
 * @author Harrison Lee
 */
public abstract class MenuButton extends JButton {
	
	int id; 	//Keep track of the ID number of this button
	int layer;	//Keep track of the layer this button is kept in
	
	/* Keep track of these two parents, to facilitate buttonFunction() access to
	 *  the MVC-model.
	 * We will define most of the buttonFunction's effects in each Stage
	*/
	View view;
	Stage parent;
	
	public MenuButton(String label, View frame, Stage parentStage,
			int idNum, int layer, Rectangle location){
		super(label);
		this.setBounds(location);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//select(idNum);
				buttonFunction();
			}
		});
		
		parentStage.getStageView().addToLayer(this, layer);
		
		//Set variables
		view = frame;
		parent = parentStage;
		id = idNum;
		
		//Ensure that this can't take focus from View (JFrame)
		this.setFocusable(false);
	}
	//Version to manage Rectangle Creation
	public MenuButton(String label, View frame, Stage parentStage,
			int idNum, int layer, int x, int y, int width, int height){
		this(label, frame, parentStage, idNum, layer,
				new Rectangle(x, y, width, height));
	}
	
	/**
	 * Expect to either make a new class extending or defining on the spot.
	 */
	public abstract void buttonFunction();
	
	//Getter functions
	public int getID() 				{ return id;		}
	//Use JButton.getLabel() to get label
	//Same for getBounds()
}




