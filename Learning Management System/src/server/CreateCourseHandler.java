package server;

import java.io.*;
import java.net.Socket;

public class CreateCourseHandler {
Socket aSocket;
ObjectInputStream in = null;
	
	public CreateCourseHandler(Socket aSocket) throws IOException
	{
		this.aSocket = aSocket;
		in = new ObjectInputStream(aSocket.getInputStream());
	}
	
	public void runHandler()
	{
		try {
			Course course = (Course)in.readObject();
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFound");
		} catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("IOException in runHandler");
		}
	}
}
