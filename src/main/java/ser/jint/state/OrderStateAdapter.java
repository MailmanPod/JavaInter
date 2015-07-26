package ser.jint.state;

import java.io.Serializable;
import java.util.List;

import ser.jint.bo.OrderDetail;

/**
 * Created by Razorback on 09/07/2015.
 */
public abstract class OrderStateAdapter implements State, Serializable {
	
	public void assignDetails(List<OrderDetail> details) {
	
	}
	
	public void assignDispatchCenter() {
	
	}
	
	public void delivery() {
	
	}
	
	public void cancel() {
	
	}
}
