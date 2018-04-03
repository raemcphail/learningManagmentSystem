package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import dbManagers.UserManager;

import java.io.*;


public class LoginHandler {
	private Socket aSocket;
	BufferedReader in;
	PrintWriter out;
	
	LoginHandler(Socket s, BufferedReader r, PrintWriter w)
	{
		aSocket = s;
		in = r;
		out = w;
	}
	
	public void runHandler() 
	{
		System.out.println("Handler is running");
		try
		{
			while(true)
			{
			String username = in.readLine();
			if(username.equals("QUIT"))
			{
				break;
			}
			System.out.println(username);
			dbManagers.UserManager u = new UserManager();
			String password = u.findClientPassword(Integer.parseInt(username));
			out.println(password);
			}
			System.out.println("broke from loop");
		}catch(IOException e)
		{
			System.out.println("runHandler error");
		}
		
	}

}
