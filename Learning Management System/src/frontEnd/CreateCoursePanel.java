package frontEnd;

import java.awt.Dimension;

import javax.swing.*;

public class CreateCoursePanel extends JPanel {
	private JLabel lblCourseName;
	private JTextField ICourseName;
	private JLabel CourseNumber;
	private JTextField ICourseNumber;
	protected JRadioButton rdbtnYes;
	protected JRadioButton rdbtnNo;
	protected JButton btnCreate;

	CreateCoursePanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		lblCourseName = new JLabel("Course Name:");
		add(lblCourseName);
		
		ICourseName = new JTextField();
		add(ICourseName);
		ICourseName.setColumns(10);
		ICourseName.setMaximumSize(new Dimension(500, 20));
		
		CourseNumber = new JLabel("Course Number:");
		add(CourseNumber);
		
		ICourseNumber = new JTextField();
		add(ICourseNumber);
		ICourseNumber.setColumns(10);
		ICourseNumber.setMaximumSize(new Dimension(500, 20));
		
		JLabel lblActive = new JLabel("Active?");
		add(lblActive);
		
		ButtonGroup group = new ButtonGroup();
		rdbtnYes = new JRadioButton("yes");
		rdbtnYes.setSelected(true);
		group.add(rdbtnYes);
		rdbtnNo = new JRadioButton("no");
		group.add(rdbtnNo);
		
		add(rdbtnYes);
		
		add(rdbtnNo);

		
		btnCreate = new JButton("Create");
		add(btnCreate);
	}
	
	
	protected void setVisible() {
		this.setVisible(true);
	}
	public String getName()
	{
		return ICourseName.getText();
	}
	public String getNumber()
	{
		return ICourseNumber.getText();
	}

}
