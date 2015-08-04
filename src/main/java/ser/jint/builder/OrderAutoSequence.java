package ser.jint.builder;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import ser.jint.persistence.CsvPersistence;

/**
 * Created by Razor15 on 10/07/2015.
 */
public final class OrderAutoSequence implements Serializable, Sequencer {
	
	private static final int			START_SEQUENCE	= 1000;
	private static OrderAutoSequence	sequence		= null;
	private AtomicInteger				atomic;
	
	private OrderAutoSequence(Integer initialSequence) {
		this.atomic = new AtomicInteger(initialSequence);
	}
	
	public static OrderAutoSequence getInstance() {
		if (sequence == null) {
			sequence = new OrderAutoSequence(START_SEQUENCE);
		}
		
		return sequence;
	}
	
	public String persistenceString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("#" + this.getClass().getSimpleName() + "#");
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(atomic.get());
		builder.append(CsvPersistence.SEPARATOR);
		
		return builder.toString();
	}
	
	public void rebuildObject(Stack<String> tokens)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
			
		this.atomic = new AtomicInteger(new Integer(tokens.pop()));
	}
	
	public int getNextSequence() {
		return atomic.getAndIncrement();
	}
	
	public void setNextSequence(Integer seq) {
		this.atomic = new AtomicInteger(seq);
	}
}
