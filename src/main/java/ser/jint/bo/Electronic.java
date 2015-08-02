package ser.jint.bo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import ser.jint.persistence.CsvPersistence;

/**
 * Created by Razor15 on 08/07/2015.
 */
public class Electronic extends Items implements Serializable {
	
	private String	mark;
	private String	type;
	
	public Electronic(int itemId, String itemDescription, double price,
			double tax, int stock, String mark, String type) {
		super(itemId, itemDescription, price, tax, stock);
		this.mark = mark;
		this.type = type;
	}
	
	public Electronic(int itemId, String itemDescription, double price,
			double tax, String mark, String type) {
		super(itemId, itemDescription, price, tax);
		this.mark = mark;
		this.type = type;
	}
	
	public Electronic(String mark, String type) {
		this.mark = mark;
		this.type = type;
	}
	
	public Electronic() {
		// super();
	}
	
	public String getMark() {
		return mark;
	}
	
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public double getPriceWithTax() {
		if (this.getTax() == 0) {
			return this.getPrice() + (this.getPrice() * 2.5);
		} else {
			return this.getPrice() + ((this.getPrice() * this.getTax()) * 2.5);
		}
	}
	
	@Override
	public String persistenceString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.persistenceString());
		
		builder.append(this.getType());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getMark());
		
		return builder.toString();
	}
	
	@Override
	public String getItemType() {
		return this.getClass().getSimpleName();
	}
	
	public void rebuildObject(Stack<String> tokens)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
			
		super.rebuildObject(tokens);
		
		this.setType(tokens.pop());
		this.setMark(tokens.pop());
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Type: " + this.getType() + "\n");
		builder.append("Mark: " + this.getMark() + "\n");
		
		return builder.toString();
	}
}
