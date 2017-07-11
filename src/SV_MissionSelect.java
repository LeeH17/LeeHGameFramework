import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class SV_MissionSelect extends StageView {

	JScrollPane scrollPane;			//List view for available missions
	
	public SV_MissionSelect(View parentView) {
		super(parentView);
		
		setBackground(Color.GREEN);

			//Set up scrolling missions list
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, (int) (getWidth()*0.2), (int) (getHeight()*0.5));
		scrollPane.setVisible(true);
		this.add(scrollPane);
	}

	public void addScrollButton(Mission msn){
		JButton newScrollBtn = new JButton(msn.getName());
		scrollPane.add(newScrollBtn);
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


















