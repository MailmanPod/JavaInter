package ser.jint.strategy;

import ser.jint.bo.Items;
import ser.jint.bo.Order;

import java.util.List;

/**
 * Created by Razor15 on 15/07/2015.
 */
public interface ListingStrategy {

    public static final int ASC = 1;
    public static final int DESC = 2;

    public List<Order> getOrderListing();

    public List<Items> getItemListing();
}
