package ser.jint.builder;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import ser.jint.persistence.CsvPersistence;

/**
 * Created by Razorback on 26/07/2015.
 */
public class ItemAutoSequence implements Serializable, Sequencer {
	
	private static final int		START_SEQUENCE	= 100;
	private static ItemAutoSequence	instance;
	private AtomicInteger			sequencer;
	
	private ItemAutoSequence(Integer initialSequence) {
		this.sequencer = new AtomicInteger(initialSequence);
	}
	
	public static ItemAutoSequence getInstance() {
		if (instance == null) {
			instance = new ItemAutoSequence(START_SEQUENCE);
		}
		
		return instance;
	}
	
	public String persistenceString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("#" + this.getClass().getSimpleName() + "#");
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.sequencer.get());
		builder.append(CsvPersistence.SEPARATOR);
		
		return builder.toString();
	}
	
	public void rebuildObject(Stack<String> tokens)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
			
		this.sequencer = new AtomicInteger(new Integer(tokens.pop()));
	}
	
	public int getNextSequence() {
		return this.sequencer.getAndIncrement();
	}
	
	public void setNextSequence(Integer seq) {
		this.sequencer = new AtomicInteger(seq);
	}
}
