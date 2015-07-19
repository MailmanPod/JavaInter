package ser.jint.strategy;

import ser.jint.bo.Order;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Razorback on 18/07/2015.
 */
public class OrderStateListing extends ListingStrategyAdapter {

    private int listingMode;

    public OrderStateListing(int listingMode) {
        this.listingMode = listingMode;
    }

    @Override
    public void listOrders(List<Order> list) {
        Collections.sort(list, new OrderStateComparator());
    }

    private class OrderStateComparator implements Comparator<Order> {

        private Collator textComparator = Collator.getInstance();

        public int compare(Order o1, Order o2) {
            return (listingMode == ListingStrategy.ASC) ? textComparator.compare(o1.getContextState(), o2.getContextState()) :
                    textComparator.compare(o2.getContextState(), o1.getContextState());
        }
    }
}
