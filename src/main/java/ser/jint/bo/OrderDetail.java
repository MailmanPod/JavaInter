package ser.jint.bo;

import ser.jint.persistence.CsvWriter;
import ser.jint.persistence.Persistable;

import java.io.Serializable;

/**
 * Created by Razorback on 09/07/2015.
 */
public class OrderDetail implements Comparable<OrderDetail>, Serializable, Persistable {

    private Items item;
    private int quantity;
    private double detailQuantity;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDetailQuantity() {
        return detailQuantity;
    }

    public void setDetailQuantity(double detailQuantity) {
        this.detailQuantity = detailQuantity;
    }

    public double getDetailTotal() {
        return this.item.getPrice() * this.getQuantity();
    }

    public int compareTo(OrderDetail o) {
        int result = 0;

        if (this.getItem().getItemId() > o.getItem().getItemId()) {
            result = 1;
        } else if (this.getItem().getItemId() < o.getItem().getItemId()) {
            result = -1;
        }

        return result;
    }

    public String persistenceString() {
        StringBuilder builder = new StringBuilder();
        builder.append("#" + this.getClass().getSimpleName() + "#");
        builder.append(CsvWriter.SEPARATOR);
        builder.append(this.getQuantity());
        builder.append(CsvWriter.SEPARATOR);
        builder.append(this.getDetailTotal());
        builder.append(CsvWriter.SEPARATOR);
        builder.append(this.getDetailQuantity());
        builder.append(CsvWriter.SEPARATOR);
        builder.append(this.getItem().persistenceString());

        return builder.toString();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cantidad: " + this.getQuantity() + "\n");
        builder.append("Subtotal: " + this.getDetailTotal() + "\n");
        builder.append(this.getItem().toString() + "\n");

        return builder.toString();
    }
}
