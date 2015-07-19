package ser.jint.criteria;

import ser.jint.bo.Order;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Razorback on 19/07/2015.
 */
public class ClientNumberCriteria implements Criteria {

    private int clientNmb;

    public ClientNumberCriteria(int nro){
        this.clientNmb = nro;
    }

    public List<Order> matchCriteria(List<Order> entry) {
        List<Order> matches = new LinkedList<Order>();

        for(Order e : entry){
            if(e.getClientIdentificationNumber() == this.clientNmb){
                matches.add(e);
            }
        }

        return matches;
    }
}
