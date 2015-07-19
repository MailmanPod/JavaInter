package ser.jint.singleton;

import ser.jint.bo.Order;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Razor15 on 14/07/2015.
 */
public final class OrderManager implements Serializable {

    private static OrderManager instance;
    private List<Order> orderList;

    private OrderManager() {
        this.orderList = new LinkedList<Order>();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }

        return instance;
    }

    public void addOrder(Order newOrder) {
        this.orderList.add(newOrder);
    }

    public void removeOrder(Order oldOrder) {
        int indx = this.orderList.indexOf(oldOrder);

        if (indx >= 0) {
            this.orderList.remove(indx);
        }
    }

    public List<Order> getOrderList() {
        return this.orderList;
    }
}
