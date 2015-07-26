package ser.jint.strategy;

import java.util.List;

import ser.jint.bo.Items;
import ser.jint.bo.Order;

/**
 * Created by Razor15 on 15/07/2015.
 */
public interface ListingStrategy {
	
	public static final int	ASC		= 1;
	public static final int	DESC	= 2;
	
	void listOrders(List<Order> list);
	
	void listItems(List<Items> list);
}
