package ser.jint.strategy;

import ser.jint.bo.Order;
import ser.jint.singleton.OrderManager;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Razorback on 18/07/2015.
 */
public class OrderIdListing extends ListingStrategyAdapter {
    private int listingMode;
    private OrderManager manager;

    public OrderIdListing(int listingMode) {
        this.listingMode = listingMode;
        manager = OrderManager.getInstance();
    }

    @Override
    public void getOrderListing() {
        Collections.sort(manager.getOrderList(), new OrderIdComparator());
    }

    private class OrderIdComparator implements Comparator<Order> {

        public int compare(Order o1, Order o2) {
            return (listingMode == ListingStrategy.ASC) ? o1.getOrderNumber() - o2.getOrderNumber() :
                    o2.getOrderNumber() - o1.getOrderNumber();
        }
    }
}
