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
	
	LoginHandler(Socket s, BufferedReader r, PrintWriter w)
	{
		aSocket = s;
		in = r;
		out = w;
		try {
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
			String password = in.readLine();
			
			int userN = Integer.parseInt(username);

			System.out.println(username);
			System.out.println(password);
			dbManagers.UserManager u = new UserManager();
			String actualPassword = u.findClientPassword(userN);
			
			boolean match = checkPassword(password, actualPassword);
			
			if (match)
			{
				out.println("success");
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
				
				outObj.writeObject(user);
				
				break;
			}
			else
			{
				out.println("failure");
			}
			
			out.println(password);
			}
			System.out.println("broke from loop");
		}catch(IOException e)
		{
			System.out.println("runHandler error");
		}
		
	}
	
	 public boolean checkPassword(String password, String actualPassword)
	 {
		 char [] cPassword = password.toCharArray();
		 char [] AcPassword = actualPassword.toCharArray();
		 return Arrays.equals(cPassword, AcPassword);
	 }

}
