package ser.jint.state;

import ser.jint.bo.OrderDetail;

import java.io.Serializable;
import java.util.List;

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
