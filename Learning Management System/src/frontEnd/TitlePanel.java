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

public class TitlePanel extends JPanel {
	private JTextField welcomeMessage;
	private JTextField ClientName;
	public TitlePanel() {
		setTitlePanel();
		populatePanel();
	}
	/**
	 * method to set the layout to FormLayout
	 */
	public void setTitlePanel()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("444px:grow"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("21px"),}));
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
		ClientName.setText("Client Name");
		add(ClientName, "2, 3, center, center");
		ClientName.setColumns(55);
	}

}
