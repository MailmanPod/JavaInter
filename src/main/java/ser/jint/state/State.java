package ser.jint.state;

import java.io.Serializable;
import java.util.List;

import ser.jint.bo.OrderDetail;

/**
 * Created by Razorback on 09/07/2015.
 */
public interface State extends Serializable {
	
	String	CREATE_STATE	= "Create";
	String	DISPATCH_STATE	= "Dispatched";
	String	DELIVERED_STATE	= "Delivered";
	String	CANCELED_STATE	= "Canceled";
	String	NOT_ASSIGNED	= "Not Assigned yet";
	
	void assignDetails(List<OrderDetail> details);
	
	void assignDispatchCenter();
	
	void delivery();
	
	void cancel();
}
