package ser.jint.criteria;

import ser.jint.bo.Order;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Razorback on 19/07/2015.
 */
public class OrderNumberCriteria implements Criteria {
    private int orderNmb;

    public OrderNumberCriteria(int orderNmb){
        this.orderNmb = orderNmb;
    }

    public List<Order> matchCriteria(List<Order> entry) {
        List<Order> matches = new LinkedList<Order>();

        for(Order e : entry){
            if(e.getOrderNumber() == this.orderNmb){
                matches.add(e);
            }
        }

        return matches;
    }
}
