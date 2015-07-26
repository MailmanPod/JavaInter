package ser.jint.persistence;

import java.io.*;

/**
 * Created by Razorback on 20/07/2015.
 */
public class ObjectSerializer {
	
	public static final String	SERIAL_ORDER	= "orders.jdb";
	public static final String	SERIAL_ITEMS	= "items.jdb";
	
	public void serializeObjects(Serializable object, String type)
			throws IOException {
			
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(type)));
				
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
	}
	
	public Serializable deserializeObject(String type)
			throws IOException, ClassNotFoundException {
			
		ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(type)));
				
		Serializable s = (Serializable) objectInputStream.readObject();
		objectInputStream.close();
		
		return s;
	}
}
