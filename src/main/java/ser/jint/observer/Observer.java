package ser.jint.observer;

import ser.jint.bo.Order;
import ser.jint.bo.OrderDetail;

import java.util.List;

/**
 * Created by Razor15 on 14/07/2015.
 */
public interface Observer {

    public void update(Order order, List<OrderDetail> details);
}
