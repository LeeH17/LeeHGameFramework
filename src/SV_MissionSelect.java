import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SV_MissionSelect extends StageView {

	//The scrolling list of missions
	JPanel listPanel;		//Holds buttons, goes in listScroll
	JScrollPane listScroll;	//Handle scrolling
	
	//The mission description panel
	JLabel descLabel;		//Handle descriptions
	JScrollPane descScroll;	//Handle scrolling in case of description overflow
	Mission currMsn;		//The current mission displayed
	
	//Missions Map
	BufferedImage imgMap;
	
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
		this.addToLayer(listScroll, 1);
		
		
			//Set up Mission Descriptions Panel
		descLabel = new JLabel();
		descLabel.setVerticalAlignment(JLabel.TOP);
		descScroll = new JScrollPane(descLabel);
		descScroll.setBounds(0, listScroll.getHeight(), listScroll.getWidth(),
				this.getHeight() - listScroll.getHeight()-20);
					//Intention is to fill rest of space below scrolling list
		//Set descLabel's sizing
		descLabel.setMinimumSize(new Dimension(descScroll.getWidth()-20, descScroll.getHeight()-10));
		descLabel.setPreferredSize(new Dimension(descScroll.getWidth()-20, descScroll.getHeight()-10));
		descLabel.setMaximumSize(new Dimension(descScroll.getWidth(), 2000));
		this.addToLayer(descScroll,1);
	}
	
	/**
	 * Sets the active mission to be displayed in the description box
	 * @param newMission: The mission to be displayed
	 */
	public void setActiveMission(Mission newMission){
		//TODO consider representation exposure, new mission(newMission)?
		currMsn = newMission;
		//Use html to format mission text
		descLabel.setText("<html><p>"
				+ currMsn.getName()
				+ "<br/><br/>" + currMsn.getDesc()
				+ "</p></html>");
	}
	
	/**
	 * Simple function to manage adding to the scrolling listPanel
	 * Also adjust button location according to mission number
	 * @param button: The button to add
	 * @param missionNumber: Define location based on what number mission this is
	 */
	public void addToList(JButton button){
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
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(imgMap, (int) (this.getWidth()*0.2), 0, (int) (getWidth()*0.8), getHeight(), null);
		
		//Draw mission description
		
		//Draw map nodes
		
		super.paint(g);
	}

	@Override
	protected void loadResources() {
		try{
			imgMap = ImageIO.read(new File("Resources/imgMissionSelectMap.png"));
		} catch (IOException e) {
			System.out.println("Failed to load in SV_MissionSelect.\n"+e);
		}
	}
}


















