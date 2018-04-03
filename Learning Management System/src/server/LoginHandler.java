package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
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
			String username = in.readLine();
			System.out.println(username);
			String password = "password";
			out.println(password);
		}catch(IOException e)
		{
			System.out.println("runHandler error");
		}
		
	}

}
