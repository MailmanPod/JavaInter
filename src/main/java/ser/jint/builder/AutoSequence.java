package ser.jint.builder;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Razor15 on 10/07/2015.
 */
public final class AutoSequence implements Serializable {

    private static final int START_SEQUENCE = 1000;

    private static AutoSequence sequence = null;
    private AtomicInteger atomic;

    private AutoSequence(int initialSequence) {
        this.atomic = new AtomicInteger(initialSequence);
    }

    public static AutoSequence getInstance() {
        if (sequence == null) {
            sequence = new AutoSequence(START_SEQUENCE);
        }

        return sequence;
    }

    public int getNextSequence() {
        return atomic.getAndIncrement();
    }
}
