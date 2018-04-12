package server;
import java.net.Socket;

/**
 * This class represents all the data for a Professor
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 */
public class Professor extends User
{
	public Professor(int id, String password, String email,
					 String first, String last, char type)
	{
		super(id, password, email, first, last, type);
	}
}
