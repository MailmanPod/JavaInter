package ser.jint.strategy;

import ser.jint.bo.Items;
import ser.jint.bo.Order;

import java.util.List;

/**
 * Created by Razor15 on 15/07/2015.
 */
public abstract class ListingStrategyAdapter implements ListingStrategy {
    public List<Order> getOrderListing() {
        return null;
    }

    public List<Items> getItemListing() {
        return null;
    }
}
