package ser.jint.singleton;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import ser.jint.bo.Items;

/**
 * Created by Razorback on 18/07/2015.
 */
public class ItemManager implements Serializable {
	
	private static ItemManager	instance;
	private List<Items>			itemList;
	
	private ItemManager() {
		this.itemList = new LinkedList<Items>();
	}
	
	public static ItemManager getInstance() {
		if (instance == null) {
			instance = new ItemManager();
		}
		
		return instance;
	}
	
	public void addItem(Items i) {
		this.itemList.add(i);
	}
	
	public void removeItem(Items i) {
		int indx = this.itemList.indexOf(i);
		
		if (indx >= 0) {
			this.itemList.remove(indx);
		}
	}
	
	public List<Items> getItemsList() {
		return this.itemList;
	}
	
	public void setItemList(List<Items> entry) {
		this.itemList = entry;
	}
}
