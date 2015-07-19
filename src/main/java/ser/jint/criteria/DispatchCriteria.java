package ser.jint.criteria;

import ser.jint.bo.Order;

import java.text.Collator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Razorback on 19/07/2015.
 */
public class DispatchCriteria implements Criteria {

    private String dispatcher;

    public DispatchCriteria(String dispatcher){
        this.dispatcher = dispatcher;
    }

    public List<Order> matchCriteria(List<Order> entry) {
        List<Order> matches = new LinkedList<Order>();
        Collator txtcmp = Collator.getInstance();

        for(Order e: entry){
            if(txtcmp.compare(e.getDispatchCenter(), this.dispatcher) == 0){
                matches.add(e);
            }
        }

        return matches;
    }
}
