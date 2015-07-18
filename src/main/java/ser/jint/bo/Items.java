package ser.jint.bo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Razor15 on 08/07/2015.
 */
public abstract class Items implements Serializable, Comparable<Items> {
    private int itemId;
    private String itemDescription;
    private double price;
    private double tax;
    private int stock;
    private String itemType;

    public Items(int itemId, String itemDescription, double price, double tax, int stock) {
        this.setItemId(itemId);
        this.setItemDescription(itemDescription);
        this.setPrice(price);
        this.setTax(tax);
        this.setStock(stock);
    }

    public Items(int itemId, String itemDescription, double price, double tax) {
        this(itemId, itemDescription, price, tax, 0);
    }

    public Items() {
        this(0, "Sin descripcion", 0.0, 0.0);
    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return this.tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public abstract double getPriceWithTax();
    public abstract String getItemType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Items items = (Items) o;

        return itemId == items.itemId;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        int result = 53 * hash + Objects.hashCode(this.itemId);

        return result;
    }

    public int compareTo(Items o) {
        if (this.getItemId() > o.getItemId()) {
            return 1;
        } else if (this.getItemId() < o.getItemId()) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("######## Item Data #########\n");
        builder.append("Item id: " + this.getItemId() + "\n");
        builder.append("Item Description: " + this.getItemDescription() + "\n");
        builder.append("Item Type: " + this.getItemType() + "\n");
        builder.append("Item price no tax: " + this.getPrice() + "\n");
        builder.append("Item tax: " + this.getTax() + "\n");
        builder.append("Item price with tax: " + this.getPriceWithTax() + "\n");
        builder.append("Item stock: " + this.getStock() + "\n");

        return builder.toString();
    }
}
