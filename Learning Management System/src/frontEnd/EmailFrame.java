package frontEnd;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.security.auth.Subject;
import javax.swing.*;

import server.User;

/**
 * Main GUI class to display the main window, which asks User(Client) for input such as their email,
 * the receivers email, and the subject, then submit.
 * @version 1.0
 * @author louis
 * @since April 9 2018
 */
public class EmailFrame extends JFrame {
		/**
		 * The high-level container which contains everything
		 */
		Container c;
		/**
		 * Several panels to display the various text-fields and labels
		 */
		JPanel label, data, title, button;
		/**
		 * The input fields for the user to place their data
		 */
		public JTextField receiver, sender, subject;
		public JTextArea textBody;
		/**
		 * The send button to send out the data.
		 */
		JButton Submit;
		/**
		 * User that is sending the email
		 */
		User user;
		/**
		 * 
		 */
		ObjectInputStream in;
		ObjectOutputStream out;
		DashboardFrame Frame;
		/**
		 * Contructor to build up the frame, and adding the Panels with their corresponding components.
		 * These Components include textFields, Labels, and Buttons.
		 * @param cli-the frame needs to know which client called it.
		 */
		public EmailFrame (User u, ObjectInputStream in, ObjectOutputStream out, DashboardFrame Frame)
		{
			this.Frame = Frame;
			user = u;
			this.in = in;
			this.out = out;
			Submit = new JButton("Submit");
			MyActionListener listener = new MyActionListener(user, this);
			Submit.addActionListener(listener);
			
			
			
			setTitle("Email");
			setSize(500, 300);
			getContentPane().setLayout(new BorderLayout());
			title = new JPanel();
			Label label_1 = new Label("Send Email");
			label_1.setBackground(Color.LIGHT_GRAY);
			title.add(label_1);
			
			data = new JPanel();
			
			sender = new JTextField(35);
			sender.setText(user.getEmail());
			receiver = new JTextField(37);
			
			
			data.add(new Label("Sender's Email Address"));
			data.add(sender);
			data.add(new Label("Receiver's Email Address"));
			data.add(receiver);
			data.add(new Label("Subject"));
			
			subject = new JTextField();
			data.add(subject);
			subject.setColumns(15);
			
			textBody = new JTextArea();
			textBody.setEditable(true);
			textBody.setRows(5);
			textBody.setWrapStyleWord(true);
			textBody.setColumns(50);
			data.add(textBody);

			button = new JPanel();
			
			button.add(Submit);
			
			c = getContentPane();
			c.add("North", title);
			c.add("Center", data);
			c.add("South", button);
			setSize(600,350);
			setResizable(false);
			setVisible(true);
		}
		
			/**
			 * Inner Listener class that will wait for the button Submit to be pressed, then handle that event
			 * Implements ActionListener.
			 * @version 1.0
	 		 * @author louis
	 		 * @since March 15 2018
			 */
			class MyActionListener implements ActionListener{
				Frame frame;
				public MyActionListener (User u, Frame frame)
				{
					user = u;
					this.frame = frame;
				}
				/**
				 * the required inherited method to be completed, since MyActionListener implements ActionListener.
				 * Parses the data that the user inputted, assuming that all fields were filled out correctly and that there are numbers
				 * for Number 1 and 2 fields. If incorrect, display error message and do nothing. If correct, send the data to the client.
				 */
				public void actionPerformed (ActionEvent event)
				{
					try
					{
						String Sender = sender.getText();
						String Receiver = receiver.getText();
						String Subject = subject.getText();
						String Body = textBody.getText();
						if (Sender.equals(""))
						{
							JOptionPane.showMessageDialog(null, "The SenderField is empty", "send error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else if (Receiver.equals(""))
						{
							JOptionPane.showMessageDialog(null, "The ReceiverField is empty", "send error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else if (Subject.equals(""))
						{
							JOptionPane.showMessageDialog(null, "The SubjectField is empty", "send error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else if (Body.isEmpty())
						{
							JOptionPane.showMessageDialog(null, "The Body is empty", "send error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else	//if the checks pass, send information to EmailHandler to send email
						{
							try {
								out.writeObject("sendEmail");	//opcode to server
								
								out.writeObject(Sender);
								out.writeObject(Receiver);
								out.writeObject(Subject);
								out.writeObject(Body);
								
								
							} catch (IOException e) {
								e.printStackTrace();
							}
							frame.dispose();
						}
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "One or more fields is invalid", "Error",JOptionPane.ERROR_MESSAGE);
					}
					/*catch (IOException e)
					{
						e.printStackTrace();
					}
					catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}*/
						
					}	
				}

}
