package ser.jint.criteria;

import java.util.LinkedList;
import java.util.List;

import ser.jint.bo.Order;

/**
 * Created by Razorback on 19/07/2015.
 */
public class OrderNumberOrderCriteria implements OrderCriteria {
	private int orderNmb;
	
	public OrderNumberOrderCriteria(int orderNmb) {
		this.orderNmb = orderNmb;
	}
	
	public List<Order> matchCriteria(List<Order> entry) {
		List<Order> matches = new LinkedList<Order>();
		
		for (Order e : entry) {
			if (e.getOrderNumber() == this.orderNmb) {
				matches.add(e);
			}
		}
		
		return matches;
	}
}
