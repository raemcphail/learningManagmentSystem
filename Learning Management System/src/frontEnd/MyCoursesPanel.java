package frontEnd;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyCoursesPanel extends ContentPanel {

	JLabel emptyCourseMessage;
	
	MyCoursesPanel()
	{
		
		emptyCourseMessage = new JLabel("You have no courses at the moment!");
		emptyCourseMessage.setHorizontalAlignment(SwingConstants.CENTER);
		add(emptyCourseMessage);
		
	}
	
	@Override
	protected void setVisible() {
		this.setVisible(true);
	}
	

}
