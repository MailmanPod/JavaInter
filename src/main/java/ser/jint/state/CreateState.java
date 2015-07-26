package ser.jint.state;

import java.io.Serializable;
import java.util.List;

import ser.jint.bo.Order;
import ser.jint.bo.OrderDetail;

/**
 * Created by Razorback on 09/07/2015.
 */
public class CreateState extends OrderStateAdapter implements Serializable {
	
	private Order orderContext;
	
	public CreateState(Order orderContext) {
		this.orderContext = orderContext;
	}
	
	public void assignDetails(List<OrderDetail> details) {
		this.orderContext.setOrderDetails(details);
		
		this.orderContext.setContextState(State.CREATE_STATE);
		this.orderContext.setCurrentState(this.orderContext.getDispatchState());
	}
}
