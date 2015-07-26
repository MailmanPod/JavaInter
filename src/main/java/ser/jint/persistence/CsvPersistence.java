package ser.jint.persistence;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Razorback on 20/07/2015.
 */
public class CsvPersistence {
	public static final String	SEPARATOR		= ";";
	public static final String	LINE_SEPARATOR	= "\n";
	public static final String	FILE_NAME_ITEM	= "persistenceItems.csv";
	public static final String	FILE_NAME_ORDER	= "persistenceOrders.csv";
	public static final String	BO_PATH			= "ser.jint.bo.";
	
	private BufferedWriter	bufferedWriter;
	private BufferedReader	bufferedReader;
	
	public CsvPersistence() {
	}
	
	public void persistObjects(List<? extends Persistable> toSave,
			String fileName) throws IOException {
		this.bufferedWriter = new BufferedWriter(
				new FileWriter((new File(fileName))));
				
		Iterator<? extends Persistable> iterator = toSave.iterator();
		while (iterator.hasNext()) {
			this.bufferedWriter.write(iterator.next().persistenceString());
			this.bufferedWriter.write(CsvPersistence.LINE_SEPARATOR);
		}
		
		this.bufferedWriter.close();
	}
	
	public List<? extends Persistable> recreateObjects(String fileName)
			throws IOException, ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
			
		List<Persistable> objectList = new LinkedList<Persistable>();
		this.bufferedReader = new BufferedReader(
				new FileReader(new File(fileName)));
		
		String out = "";
		
		while (out != null) {
			out = this.bufferedReader.readLine();
			
			System.out.println(out);
			
			if (out != null) {
				String[] tokens = out.split(CsvPersistence.SEPARATOR);
				
				Stack<String> stack = new Stack<String>();
				
				for (int i = tokens.length - 1; i >= 0; i--) {
					stack.add(tokens[i]);
				}
				
				String aux = stack.pop();
				
				if (aux.startsWith("#")) {
					String classForName = aux.substring(aux.indexOf("#") + 1,
							aux.lastIndexOf("#"));
					Persistable p = (Persistable) Class
							.forName(CsvPersistence.BO_PATH + classForName)
							.newInstance();
							
					p.rebuildObject(stack);
					objectList.add(p);
				}
			}
		}
		
		this.bufferedReader.close();
		
		return objectList;
	}
}
