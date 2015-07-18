package ser.jint.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Razor15 on 08/07/2015.
 */
public class Books extends Items implements Serializable {

    private String editor;
    private int pages;
    private Date publishDate;

    public Books(int itemId, String itemDescription, double price, double tax, String editor, int pages, Date publishDate) {
        super(itemId, itemDescription, price, tax);
        this.editor = editor;
        this.pages = pages;
        this.publishDate = publishDate;
    }

    public Books(int itemId, String itemDescription, double price, double tax, int stock, String editor, int pages, Date publishDate) {
        super(itemId, itemDescription, price, tax, stock);
        this.editor = editor;
        this.pages = pages;
        this.publishDate = publishDate;
    }

    public Books(String editor, int pages, Date publishDate) {
        this.editor = editor;
        this.pages = pages;
        this.publishDate = publishDate;
    }

    public Books() {
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public double getPriceWithTax() {
        if (this.getTax() == 0) {
            return (this.getPrice() * 1.5);
        } else {
            return (this.getPrice() * this.getTax()) * 1.5;
        }
    }

    @Override
    public String getItemType() {
        return this.getClass().getSimpleName();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");

        builder.append(super.toString());
        builder.append("########## Books Data #########");
        builder.append("Editor name: " + this.getEditor() + "\n");
        builder.append("Pages: " + this.getPages() + "\n");
        builder.append("Publish Date: " + format.format(this.getPublishDate().getTime()));

        return builder.toString();
    }
}
