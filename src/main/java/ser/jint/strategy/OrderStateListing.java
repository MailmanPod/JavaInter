package ser.jint.strategy;

import ser.jint.bo.Order;
import ser.jint.singleton.OrderManager;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Razorback on 18/07/2015.
 */
public class OrderStateListing extends ListingStrategyAdapter {

    private int listingMode;
    private OrderManager manager;

    public OrderStateListing(int listingMode) {
        this.listingMode = listingMode;
        this.manager = OrderManager.getInstance();
    }

    @Override
    public void listOrders() {
        Collections.sort(manager.getOrderList(), new OrderStateComparator());
    }

    private class OrderStateComparator implements Comparator<Order> {

        private Collator textComparator = Collator.getInstance();

        public int compare(Order o1, Order o2) {
            return (listingMode == ListingStrategy.ASC) ? textComparator.compare(o1.getContextState(), o2.getContextState()) :
                    textComparator.compare(o2.getContextState(), o1.getContextState());
        }
    }
}
