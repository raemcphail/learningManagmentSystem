package frontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;

public class LoginFrame extends JFrame {
	private JPasswordField IUser;
	private JPasswordField IPassword;
	private JLabel Password;
	private JLabel User;
	private JLabel invalidMessage;
	public LoginFrame()
	{
		setSize(200, 200);
		setResizable(false);
		JPanel main = new JPanel();
		addComponents(main);
		setContentPane(main);
	}
	
	public void addComponents(JPanel main)
	{
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		User = new JLabel("Username");
		User.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(User);
		
		IUser = new JPasswordField(8);		
		IUser.setMaximumSize(new Dimension(370, 20));
		IUser.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(IUser);
		
		Password = new JLabel("Password");
		main.add(Password);
		
		IPassword = new JPasswordField(8);
		IPassword.setMaximumSize(new Dimension(370, 20));
		IPassword.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(IPassword);
		
		JButton btnLogin = new JButton("Login");
		main.add(btnLogin);
		
		invalidMessage = new JLabel("Invalid Username or Password");
		invalidMessage.setEnabled(false);
		invalidMessage.setForeground(Color.RED);
		main.add(invalidMessage);
	}
	
}
