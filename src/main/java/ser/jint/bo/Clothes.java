package ser.jint.bo;

import java.io.Serializable;

/**
 * Created by Razor15 on 08/07/2015.
 */
public class Clothes extends Items implements Serializable {

    private String size;
    private String mark;

    public Clothes(int itemId, String itemDescription, double price, double tax, int stock, String size, String mark) {
        super(itemId, itemDescription, price, tax, stock);
        this.size = size;
        this.mark = mark;
    }

    public Clothes(int itemId, String itemDescription, double price, double tax, String size, String mark) {
        super(itemId, itemDescription, price, tax);
        this.size = size;
        this.mark = mark;
    }

    public Clothes(String size, String mark) {
        this.size = size;
        this.mark = mark;
    }

    public Clothes() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public double getPriceWithTax() {
        if (this.getTax() == 0) {
            return (this.getPrice() * 0.5);
        } else {
            return (this.getPrice() * this.getTax()) * 0.5;
        }
    }

    @Override
    public String getItemType() {
        return this.getClass().getSimpleName();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(super.toString());
        builder.append("Mark: " + this.getMark() + "\n");
        builder.append("Size: " + this.getSize() + "\n");

        return builder.toString();
    }
}
