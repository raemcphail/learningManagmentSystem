package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * class to send emails to the user,
 * information is send through the actionevent class
 * which is an inner class within EmailFrame.
 * @author louis
 *
 */
public class EmailHandler {
	User user;
	Session session;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	EmailHandler(User u, ObjectInputStream in, ObjectOutputStream out)
	{
		this.in = in;
		this.out = out;
		
		user = u;
		
	}
	
	public void runHandler() throws IOException, ClassNotFoundException
	{
		try
		{
			
			String senderAddress = (String)in.readObject();
			String receiverAddress = (String)in.readObject();
			String subject = (String)in.readObject();
			String body = (String)in.readObject();
			
			Properties properties = new Properties();
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			
			session = Session.getInstance(properties,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderAddress, user.getPassword());
				}
			});
			System.out.println(senderAddress);
			System.out.println(user.getPassword());
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderAddress));
			if (receiverAddress.contains(";"))
			{
				String [] receivers = receiverAddress.split(";");
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receivers[0]));
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(receivers[1]));
			}
			else
			{
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverAddress));
			}
			message.setSubject(subject);
			message.setText(body);
			
			Transport.send(message);  //send the email message
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
