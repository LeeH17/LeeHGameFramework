import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SV_MissionSelect extends StageView {

	//The scrolling list of missions
	JPanel listPanel;		//Holds buttons
	JScrollPane listScroll;	//Handle scrolling
	
	public SV_MissionSelect(View parentView) {
		super(parentView);
		
		setBackground(Color.GREEN);

			//Set up scrolling missions list
		listPanel = new JPanel();
		listPanel.setBounds(0, 0, (int) (getWidth()*0.2), (int) (getHeight()*0.5));
		listScroll = new JScrollPane(	JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScroll.setBounds(0, listPanel.getWidth()-10, 10, listPanel.getHeight());
		listPanel.add(listScroll);
		this.add(listPanel);
	}
	
	/**
	 * Simple function to manage adding to the scrolling listPanel
	 * Also adjust button location according to mission number
	 * @param button: The button to add
	 * @param missionNumber: Define location based on what number mission this is
	 */
	public void addToList(MenuButton button, int missionNumber){
		Rectangle bounds = new Rectangle(0, missionNumber*50,
				(int) (getWidth()*0.2)-10, (int) (getHeight()*0.5));
		listPanel.add(button);
		button.setBounds(bounds);
		button.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		//Draw background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		super.paint(g);
	}

	@Override
	protected void loadResources() {
		// TODO Auto-generated method stub
		
	}
}


















