package ser.jint.command;

import ser.jint.bo.Order;

/**
 * Created by Razor15 on 13/07/2015.
 */
public class DispathCommand implements Command {

    private Order order;

    public DispathCommand(Order order) {
        this.order = order;
    }

    public void changeStatus() {
        order.getCurrentState().assignDispatchCenter();
    }
}
