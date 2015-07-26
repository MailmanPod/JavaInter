package ser.jint.persistence;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

/**
 * Created by Razor15 on 20/07/2015.
 */
public interface Persistable {
	
	public String persistenceString();
	
	public void rebuildObject(Stack<String> tokens)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException;
}
