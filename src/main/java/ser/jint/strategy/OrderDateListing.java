package ser.jint.strategy;

import ser.jint.bo.Order;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Razorback on 18/07/2015.
 */
public class OrderDateListing extends ListingStrategyAdapter {

    private int listingMode;

    public OrderDateListing(int listingMode) {
        this.listingMode = listingMode;
    }

    @Override
    public void listOrders(List<Order> list) {
        Collections.sort(list, new OrderDateComparator());
    }

    private class OrderDateComparator implements Comparator<Order> {
        public int compare(Order o1, Order o2) {
            return (listingMode == ListingStrategy.ASC) ? o1.getCreationDate().compareTo(o2.getCreationDate()) :
                    o2.getCreationDate().compareTo(o1.getCreationDate());
        }
    }
}
