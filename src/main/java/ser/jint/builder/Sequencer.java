package ser.jint.builder;

import ser.jint.persistence.Persistable;

/**
 * Created by Razorback on 26/07/2015.
 */
public interface Sequencer extends Persistable {
	
	public int getNextSequence();
}
