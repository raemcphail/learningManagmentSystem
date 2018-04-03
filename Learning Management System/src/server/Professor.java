package server;
import java.net.Socket;
public class Professor extends User
{
	Socket aSocket;
	public Professor(Socket socket, int id, String password, String email,
					 String first, String last, char type)
	{
		super(id, password, email, first, last, type);
		aSocket = socket;
	}
}
