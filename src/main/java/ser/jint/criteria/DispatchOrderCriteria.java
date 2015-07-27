package ser.jint.criteria;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

import ser.jint.bo.Order;

/**
 * Created by Razorback on 19/07/2015.
 */
public class DispatchOrderCriteria implements OrderCriteria {
	
	private String dispatcher;
	
	public DispatchOrderCriteria(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	public List<Order> matchCriteria(List<Order> entry) {
		List<Order> matches = new ArrayList<Order>();
		Collator txtcmp = Collator.getInstance();
		
		for (Order e : entry) {
			if (txtcmp.compare(e.getDispatchCenter(), this.dispatcher) == 0) {
				matches.add(e);
			}
		}
		
		return matches;
	}
}
