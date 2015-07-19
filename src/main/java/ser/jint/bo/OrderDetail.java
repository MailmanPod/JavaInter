package ser.jint.bo;

import java.io.Serializable;

/**
 * Created by Razorback on 09/07/2015.
 */
public class OrderDetail implements Comparable<OrderDetail>, Serializable {

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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cantidad: " + this.getQuantity() + "\n");
        builder.append("Subtotal: " + this.getDetailTotal() + "\n");
        builder.append(this.getItem().toString() + "\n");

        return builder.toString();
    }
}
