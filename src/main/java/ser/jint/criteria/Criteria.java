package ser.jint.criteria;

import ser.jint.bo.Order;

import java.util.List;

/**
 * Created by Razorback on 19/07/2015.
 */
public interface Criteria {

    public List<Order> matchCriteria(List<Order> entry);
}
