import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * General menu button, to consolidate functions from Main Menu (TODO update)
 * Abstract, so will have to extend and add button functionality 
 * @author Harrison Lee
 */
public abstract class MenuButton extends JButton {
	
	int id; //Keep track of the ID number of this button
	
	public MenuButton(String label, JFrame frame, int idNum, 
			Rectangle location){
		super(label);
		this.setBounds(location);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//select(idNum);
				buttonFunction();
			}
		});
		
		frame.add(this);
		
		id = idNum;
	}
	//Version to manage Rectangle Creation
	public MenuButton(String label, JFrame frame, int idNum,
			int x, int y, int width, int height){
		this(label, frame, idNum, new Rectangle(x, y, width, height));
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




