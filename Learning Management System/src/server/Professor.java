package server;
import java.net.Socket;

/**
 * This class represents all the data for a Professor
 * @author raemc
 *
 */
public class Professor extends User
{
	//Socket aSocket;
	public Professor(int id, String password, String email,
					 String first, String last, char type)
	{
		super(id, password, email, first, last, type);
		//aSocket = socket;
	}
}
