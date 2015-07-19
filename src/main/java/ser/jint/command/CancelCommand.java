package ser.jint.command;

import ser.jint.bo.Order;

/**
 * Created by Razor15 on 16/07/2015.
 */
public class CancelCommand implements Command {

    private Order order;

    public CancelCommand(Order order) {
        this.order = order;
    }

    public void changeStatus() {
        this.order.setCurrentState(this.order.getCanceledState());
        this.order.getCurrentState().cancel();
    }
}
