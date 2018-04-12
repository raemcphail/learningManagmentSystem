package frontEnd;

import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.BoxLayout;
import javax.swing.DropMode;
import java.awt.Color;
/**
 * Panel in upper portion of dashboard frame, used to display the user and welcome them to the application
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class TitlePanel extends JPanel {
	/**
	 * the welcome message title 
	 */
	private JTextField welcomeMessage;
	/**
	 * the user name to be updated
	 */
	private JTextField ClientName;
	/**
	 * the name attribute when the panel is constructed
	 */
	private String theUserName;
	public TitlePanel(String firstname, String lastname) {
		theUserName = firstname + " " + lastname;
		setTitlePanel();
		populatePanel();
	}
	/**
	 * method to set the layout to FormLayout
	 */
	public void setTitlePanel()
	{
		/*setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("444px:grow"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("21px"),}));*/
		setBackground(new Color(176, 224, 230));
		setSize(700, 200);
	}
	/**
	 * method to initialize components on panel
	 */
	public void populatePanel()
	{
		welcomeMessage = new JTextField();
		welcomeMessage.setEditable(false);
		welcomeMessage.setForeground(new Color(255, 0, 0));
		welcomeMessage.setBackground(new Color(176, 224, 230));
		welcomeMessage.setText(" Good Afternoon,");
		add(welcomeMessage, "2, 2, left, top");
		welcomeMessage.setColumns(55);
		
		ClientName = new JTextField();
		ClientName.setEditable(false);
		ClientName.setForeground(new Color(255, 0, 0));
		ClientName.setBackground(new Color(176, 224, 230));
		ClientName.setText(theUserName);
		add(ClientName, "2, 3, center, center");
		ClientName.setColumns(55);
	}

}
