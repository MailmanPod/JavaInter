package ser.jint.strategy;

import ser.jint.bo.Order;
import ser.jint.singleton.OrderManager;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Razorback on 18/07/2015.
 */
public class OrderDateListing extends ListingStrategyAdapter {

    private int listingMode;
    private OrderManager manager;

    public OrderDateListing(int listingMode) {
        this.listingMode = listingMode;
        manager = OrderManager.getInstance();
    }

    @Override
    public void getOrderListing() {
        Collections.sort(manager.getOrderList(), new OrderDateComparator());
    }

    private class OrderDateComparator implements Comparator<Order> {
        public int compare(Order o1, Order o2) {
            return (listingMode == ListingStrategy.ASC) ? o1.getCreationDate().compareTo(o2.getCreationDate()) :
                    o2.getCreationDate().compareTo(o1.getCreationDate());
        }
    }
}
