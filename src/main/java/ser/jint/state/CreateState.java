package ser.jint.state;

import ser.jint.bo.Order;
import ser.jint.bo.OrderDetail;

import java.io.Serializable;
import java.util.List;

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
