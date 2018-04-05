package frontEnd;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyCoursesPanel extends JPanel {

	JLabel emptyCourseMessage;
	
	MyCoursesPanel()
	{
		
		emptyCourseMessage = new JLabel("You have no courses at the moment!");
		emptyCourseMessage.setHorizontalAlignment(SwingConstants.CENTER);
		add(emptyCourseMessage);
		
	}
	
	
	protected void setVisible() {
		this.setVisible(true);
	}
	

}
