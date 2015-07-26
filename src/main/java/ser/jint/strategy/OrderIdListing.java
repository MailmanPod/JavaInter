package ser.jint.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ser.jint.bo.Order;

/**
 * Created by Razorback on 18/07/2015.
 */
public class OrderIdListing extends ListingStrategyAdapter {
	private int listingMode;
	
	public OrderIdListing(int listingMode) {
		this.listingMode = listingMode;
	}
	
	@Override
	public void listOrders(List<Order> list) {
		Collections.sort(list, new OrderIdComparator());
	}
	
	private class OrderIdComparator implements Comparator<Order> {
		
		public int compare(Order o1, Order o2) {
			return (listingMode == ListingStrategy.ASC)
					? o1.getOrderNumber() - o2.getOrderNumber()
					: o2.getOrderNumber() - o1.getOrderNumber();
		}
	}
}
