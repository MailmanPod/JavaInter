package ser.jint.testing;

import ser.jint.bo.Electronic;
import ser.jint.bo.Order;
import ser.jint.bo.OrderDetail;
import ser.jint.facade.OrderFacadeSubject;
import ser.jint.persistence.CsvWriter;
import ser.jint.singleton.ItemManager;
import ser.jint.singleton.OrderManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Razor15 on 16/07/2015.
 */
public class MainTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
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

        while (iter.hasNext()) {
            Order z = iter.next();
            System.out.println(z);
        }

        System.out.println("Despachando ordenes");
        ofs.dispathOrders(manager.getOrderList());
        iter = manager.getOrderList().iterator();

        while (iter.hasNext()) {
            Order z = iter.next();
            System.out.println(z);
        }

        //System.out.println("Cancelando orden");
        //ofs.cancelOrder(manager.getOrderList());
        System.out.println("Entregando Pedidos realizados");
        ofs.deliveryOrders(manager.getOrderList());
        iter = manager.getOrderList().iterator();

        while (iter.hasNext()) {
            Order z = iter.next();
            System.out.println(z);
        }

        ItemManager managerItem = ItemManager.getInstance();

        Electronic rh = new Electronic();

        rh.setStock(89);
        rh.setTax(0.21);
        rh.setPrice(3000.00);
        rh.setItemDescription("Samsung Gxy 6 LTE");
        rh.setMark("Samsung");
        rh.setType("GSM LTE");
        rh.setItemId(200568);

        managerItem.addItem(rh);

        rh = new Electronic();

        rh.setStock(99);
        rh.setTax(0.21);
        rh.setPrice(1500.00);
        rh.setItemDescription("LG G3 LTE");
        rh.setMark("LGE");
        rh.setType("GSM LTE");
        rh.setItemId(200568);

        managerItem.addItem(rh);

        CsvWriter csv = new CsvWriter();
        csv.persistItems();

        csv.persistOrders();


        Object lk = Class.forName("ser.jint.bo." + "Electronic").newInstance();

        for (Method method : lk.getClass().getMethods()) {
            System.out.println((method.getName().startsWith("set")) ? method.getName() : "");
            System.out.println((method.getName().startsWith("set")) ? method.invoke(method.getName(), "Marca Pulenta") : "no es setter");
        }

        System.out.println(lk.toString());
    }
}
