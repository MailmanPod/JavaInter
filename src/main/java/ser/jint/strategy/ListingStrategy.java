package ser.jint.strategy;

/**
 * Created by Razor15 on 15/07/2015.
 */
public interface ListingStrategy {

    public static final int ASC = 1;
    public static final int DESC = 2;

    void listOrders();

    void listItems();
}
