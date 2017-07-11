import java.awt.Color;
import java.awt.Graphics;

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
		listPanel.add(listScroll);
		this.add(listPanel);
	}

	public void addScrollButton(Mission msn){
		JButton newScrollBtn = new JButton(msn.getName());
		listPanel.add(newScrollBtn);
		newScrollBtn.setVisible(true);
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


















