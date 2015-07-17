package ser.jint.state;

import ser.jint.bo.Order;

/**
 * Created by Razorback on 16/07/2015.
 */
public class CancelState extends OrderStateAdapter {
    private Order order;

    public CancelState(Order order) {
        this.order = order;
    }

    @Override
    public void cancel() {
        this.order.setContextState(State.CANCELED_STATE);
        this.order.setCanceled(true);
    }
}
