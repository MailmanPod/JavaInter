package ser.jint.state;

import ser.jint.bo.Order;

import java.io.Serializable;

/**
 * Created by Razor15 on 10/07/2015.
 */
public class DeliveredState extends OrderStateAdapter implements Serializable {

    private Order orderContext;

    public DeliveredState(Order orderContext) {
        this.orderContext = orderContext;
    }

    @Override
    public void delivery() {
        orderContext.setDelivered(true);
        this.orderContext.setContextState(State.DELIVERED_STATE);
    }
}
