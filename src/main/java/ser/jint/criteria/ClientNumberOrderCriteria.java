package ser.jint.criteria;

import java.util.LinkedList;
import java.util.List;

import ser.jint.bo.Order;

/**
 * Created by Razorback on 19/07/2015.
 */
public class ClientNumberOrderCriteria implements OrderCriteria {
	
	private int clientNmb;
	
	public ClientNumberOrderCriteria(int nro) {
		this.clientNmb = nro;
	}
	
	public List<Order> matchCriteria(List<Order> entry) {
		List<Order> matches = new LinkedList<Order>();
		
		for (Order e : entry) {
			if (e.getClientIdentificationNumber() == this.clientNmb) {
				matches.add(e);
			}
		}
		
		return matches;
	}
}
