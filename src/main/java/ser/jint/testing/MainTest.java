package ser.jint.testing;

import ser.jint.bo.*;
import ser.jint.facade.OrderFacadeSubject;
import ser.jint.singleton.OrderManager;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Razor15 on 16/07/2015.
 */
public class MainTest {

    public static void main(String[] args){
        OrderManager manager = OrderManager.getInstance();
        OrderFacadeSubject ofs = OrderFacadeSubject.getInstance();

        Order o = new Order();
        o.setClientIdentificationNumber(34441144);
        o.setClientIdentificationType("DNI");
        o.setClientName("YO");
        o.setOrderAddress("Av Colon 778");
        o.setOrderZipAddress("X500GBY");
        o.setCreationDate(new Date(System.currentTimeMillis()));

        Electronic e = new Electronic();
        e.setStock(89);
        e.setTax(0.21);
        e.setPrice(3000.00);
        e.setItemDescription("Samsung Gxy 6 LTE");
        e.setMark("Samsung");
        e.setType("GSM LTE");
        e.setItemId(200568);

        OrderDetail detail = new OrderDetail();
        detail.setItem(e);
        detail.setQuantity(2);

        List<OrderDetail> tmp = new LinkedList<OrderDetail>();
        tmp.add(detail);

        ofs.updateData(o, tmp);

        Iterator<Order> iter = manager.getOrderList().iterator();

        while(iter.hasNext()){
            Order z = iter.next();
            System.out.println(z);
        }

        System.out.println("Despachando ordenes");
        ofs.dispathOrders(manager.getOrderList());
        iter = manager.getOrderList().iterator();

        while(iter.hasNext()){
            Order z = iter.next();
            System.out.println(z);
        }

        //System.out.println("Cancelando orden");
        //ofs.cancelOrder(manager.getOrderList());
        System.out.println("Entregando Pedidos realizados");
        ofs.deliveryOrders(manager.getOrderList());
        iter = manager.getOrderList().iterator();

        while(iter.hasNext()){
            Order z = iter.next();
            System.out.println(z);
        }
    }
}
