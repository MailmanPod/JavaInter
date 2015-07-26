package ser.jint.command;

import ser.jint.bo.Order;

/**
 * Created by Razor15 on 13/07/2015.
 */
public class DeliveryCommand implements Command {
	
	private Order order;
	
	public DeliveryCommand(Order order) {
		this.order = order;
	}
	
	public void changeStatus() {
		this.order.getCurrentState().delivery();
	}
}
