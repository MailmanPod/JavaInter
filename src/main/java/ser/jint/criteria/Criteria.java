package ser.jint.criteria;

import java.util.List;

import ser.jint.bo.Order;

/**
 * Created by Razorback on 19/07/2015.
 */
public interface Criteria {
	
	public List<Order> matchCriteria(List<Order> entry);
}
