package ser.jint.observer;

/**
 * Created by Razor15 on 14/07/2015.
 */
public interface Subject {

    public void notifyAllObserver();

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);
}
