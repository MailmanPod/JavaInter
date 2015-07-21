package ser.jint.persistence;

import ser.jint.bo.Items;
import ser.jint.bo.Order;
import ser.jint.singleton.ItemManager;
import ser.jint.singleton.OrderManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Razorback on 20/07/2015.
 */
public class CsvWriter {
    public static final String SEPARATOR = ";";
    public static final String LINE_SEPARATOR = "\n";
    private static final String FILE_NAME_ITEM = "persistenceItems.csv";
    private static final String FILE_NAME_ORDER = "persistenceOrders.csv";
    private OrderManager orderManager;
    private ItemManager itemManager;
    private BufferedWriter bufferedWriter;

    public CsvWriter() {
        this.orderManager = OrderManager.getInstance();
        this.itemManager = ItemManager.getInstance();
    }

    public void persistItems() throws IOException {
        Iterator<Items> iterator = this.itemManager.getItemsList().iterator();

        while (iterator.hasNext()) {
            persist(iterator.next(), FILE_NAME_ITEM);
        }

    }

    private void persist(Persistable persistable, String fileName) throws IOException {
        this.bufferedWriter = new BufferedWriter(new FileWriter((new File(fileName))));
        this.bufferedWriter.write(persistable.persistenceString());
    }

    public void persistOrders() throws IOException {
        Iterator<Order> iterator = this.orderManager.getOrderList().iterator();

        while (iterator.hasNext()) {
            persist(iterator.next(), FILE_NAME_ORDER);
        }
    }
}
