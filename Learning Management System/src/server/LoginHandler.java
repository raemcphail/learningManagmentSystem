package server;

import java.net.Socket;
import java.util.Arrays;

import dbManagers.UserManager;

import java.io.*;


public class LoginHandler {
	private Socket aSocket;
	BufferedReader in;
	PrintWriter out;
	User user;
	ObjectOutputStream outObj = null;
	
	LoginHandler(Socket s)
	{
		aSocket = s;
		try {
			in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			out = new PrintWriter(aSocket.getOutputStream(),true);
			outObj = new ObjectOutputStream(aSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runHandler(User user) 
	{
		this.user = user;
		System.out.println("Handler is running");
		try
		{
			while(true)
			{
			String username = in.readLine();
			char [] password = in.readLine().toCharArray();
			
			int userN = Integer.parseInt(username);

			System.out.println(username);
			System.out.println(password);
			dbManagers.UserManager u = new UserManager();
			String actualPassword = u.findClientPassword(userN);
			System.out.println("Actual password is:" + actualPassword);
			boolean match = checkPassword(password, actualPassword);
			
			if (match)
			{
				char[] type = u.findClientType(userN);
				/*if (type[0] == 'S')
				{
					
				}
				else if (type[0] == 'P')
				{
					
				}*/
				String email = u.findClientEmail(userN);
				user.setEmail(email);
				
				String firstname = u.findClientFirstname(userN);
				user.setfirstName(firstname);
				
				String lastname = u.findClientLastname(userN);
				user.setlastName(lastname);
				
				user.setID(userN);
				user.setPassword(actualPassword);
				
				user.setType(type[0]);
				out.println("success");

				outObj.writeObject(user);
				outObj.flush();
				//outObj.close();
				break;
			}
			else
			{
				out.println("failure");
			}
			
			}
			System.out.println("broke from loop");
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("runHandler error");
		}
		
	}
	
	 public boolean checkPassword(char [] password, String actualPassword)
	 {
		 if (actualPassword == null)
		 {
			 return false;
		 }
		 char [] AcPassword = actualPassword.toCharArray();
		 return Arrays.equals(password, AcPassword);
	 }

}
