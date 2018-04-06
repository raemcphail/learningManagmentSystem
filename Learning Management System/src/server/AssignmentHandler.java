package server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AssignmentHandler 
{
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
		AssignmentHandler(ObjectInputStream in, ObjectOutputStream out)
		{
			this.in = in;
			this.out = out;
		}
		
	public void addHandler()
	{
		try
		{
		//String name = (String)in.readObject();
		String ex = (String)in.readObject();
		String name = (String)in.readObject();
		String path = recieveFile(ex);
		Course course = (Course)in.readObject();
		//need to get the id for the course
		
		updateDatabase();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void updateDatabase()
	{
		
	}
	public String recieveFile (String ex)
	{
		String STORAGEPATH = "C:\\Users\\raemc\\Desktop\\lmsServer\\";
		String NAME = "test";
		String EXTENSION = ex;
		String path = STORAGEPATH + NAME + EXTENSION;
		System.out.println("Server is recieving file");
		try
		{
			System.out.println("recieving file");
			byte[] content = (byte[]) in.readObject();
			File newFile = new File(STORAGEPATH + NAME + EXTENSION );
			if(! newFile.exists())
			{
				newFile.createNewFile();
			}
			FileOutputStream writer = new FileOutputStream (newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(content);
			bos.close();
			return path;
		}catch(IOException e)
		{
			e.printStackTrace();
			return path;
		}		
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return path;
		}
	}
}
