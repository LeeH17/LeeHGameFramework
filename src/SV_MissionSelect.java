import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
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
		//listPanel.setLayout(null);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listScroll = new JScrollPane(	listPanel,
										JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScroll.setBounds(0, 0, (int) (getWidth()*0.2), (int) (getHeight()*0.5));
		listPanel.setBounds(0, 0, listScroll.getWidth()-20, listScroll.getHeight());
		this.add(listScroll);
	}
	
	/**
	 * Simple function to manage adding to the scrolling listPanel
	 * Also adjust button location according to mission number
	 * @param button: The button to add
	 * @param missionNumber: Define location based on what number mission this is
	 */
	public void addToList(MenuButton button, int missionNumber){
		//All three of these lines necessary to set size properly with boxLayout
		button.setMinimumSize(new Dimension(listPanel.getWidth(), 50));
		button.setPreferredSize(new Dimension(listPanel.getWidth(), 50));
		button.setMaximumSize(new Dimension(listPanel.getWidth(), 50));

		listPanel.add(button);
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


















