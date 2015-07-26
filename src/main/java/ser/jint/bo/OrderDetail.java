package ser.jint.bo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import ser.jint.persistence.CsvPersistence;
import ser.jint.persistence.Persistable;

/**
 * Created by Razorback on 09/07/2015.
 */
public class OrderDetail
		implements Comparable<OrderDetail>, Serializable, Persistable {
		
	private Items	item;
	private int		quantity;
	
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
		builder.append(CsvPersistence.SEPARATOR);
		builder.append(this.getQuantity());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getItem().persistenceString());
		
		return builder.toString();
	}
	
	public void rebuildObject(Stack<String> tokens)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
		this.setQuantity(new Integer(tokens.pop()));
		
		String cd = tokens.pop();
		String classForName = cd.substring(cd.indexOf("#") + 1,
				cd.lastIndexOf("#"));
		Items instance = (Items) Class.forName("ser.jint.bo." + classForName)
				.newInstance();
		instance.rebuildObject(tokens);
		
		this.setItem(instance);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("######## Order Detail #########\n");
		builder.append("Cantidad: " + this.getQuantity() + "\n");
		builder.append("Subtotal: " + this.getDetailTotal() + "\n");
		builder.append(this.getItem().toString() + "\n");
		
		return builder.toString();
	}
}
