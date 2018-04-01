package frontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DashboardFrame extends JFrame {
	public DashboardFrame() {
		setSize(700,700);
		JPanel mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		TitlePanel title = new TitlePanel();
		mainPanel.add(title);
		CoursePanel middleBar = new CoursePanel();
		mainPanel.add(middleBar);
		//this.pack();
	}
}
