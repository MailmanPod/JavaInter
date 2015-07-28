package ser.jint.bo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

import ser.jint.persistence.CsvPersistence;

/**
 * Created by Razor15 on 08/07/2015.
 */
public class Books extends Items implements Serializable {
	
	private String	editor;
	private int		pages;
	private Date	publishDate;
	
	public Books(int itemId, String itemDescription, double price, double tax,
			String editor, int pages, Date publishDate) {
		super(itemId, itemDescription, price, tax);
		this.editor = editor;
		this.pages = pages;
		this.publishDate = publishDate;
	}
	
	public Books(int itemId, String itemDescription, double price, double tax,
			int stock, String editor, int pages, Date publishDate) {
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
	
	@Override
	public String persistenceString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.persistenceString());
		
		builder.append(this.getEditor());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getPages());
		builder.append(CsvPersistence.SEPARATOR);
		builder.append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
				.format(this.getPublishDate()));
				
		return builder.toString();
	}
	
	public void rebuildObject(Stack<String> tokens)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
			
		super.rebuildObject(tokens);
		
		this.setEditor(tokens.pop());
		this.setPages(new Integer(tokens.pop()));
		try {
			this.setPublishDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
					.parse(tokens.pop()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(super.toString());
		builder.append("########## Books Data #########\n");
		builder.append("Editor name: " + this.getEditor() + "\n");
		builder.append("Pages: " + this.getPages() + "\n");
		builder.append(
				"Publish Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.format(this.getPublishDate()) + "\n");
						
		return builder.toString();
	}
}
