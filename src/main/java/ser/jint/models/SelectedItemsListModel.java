package ser.jint.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;

/**
 * Created by Razorback on 31/07/2015.
 */
public class SelectedItemsListModel extends AbstractListModel {
	
	private Vector<String> items;
	private Map<String, Integer> selectedItems;
	
	public SelectedItemsListModel() {
		this.selectedItems = new HashMap<>();
		this.items = new Vector<>();
	}
	
	public void addItem(String key, Integer value) {
		this.selectedItems.put(key, value);
		this.items.add(key);
		System.out.println("Values for key: " + key + " is: " + this.selectedItems.get(key));
		updateModel();
	}
	
	public void removeItem(String key) {
		this.selectedItems.remove(key);
		this.items.remove(key);
		updateModel();
	}
	
	public void updateModel() {
		fireContentsChanged(this, 0, getSize());
	}
	
	@Override
	public int getSize() {
		return this.items.size();
	}
	
	@Override
	public Object getElementAt(int index) {
		return this.items.get(index);
	}
}
