package server;

import java.net.Socket;

public class Student extends User
{
	Socket aSocket;
	
	public Student(Socket socket, int id, String password, String email,
					 String first, String last, char type)
	{
		super(id, password, email, first, last, type);
		aSocket = socket;
	}
}
