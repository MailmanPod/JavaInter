package ser.jint.models;

import java.util.Vector;

import javax.swing.*;

/**
 * Created by Razorback on 31/07/2015.
 */
public class SelectedItemsListModel extends AbstractListModel {
	
	private Vector<String> selectedItems;
	
	public SelectedItemsListModel() {
		this.selectedItems = new Vector<>();
	}
	
	public void addItem(String item) {
		this.selectedItems.add(item);
		updateModel();
	}
	
	public void removeItem(String item) {
		this.selectedItems.remove(item);
		updateModel();
	}
	
	public void updateModel() {
		fireContentsChanged(this, 0, getSize());
	}
	
	@Override
	public int getSize() {
		return this.selectedItems.size();
	}
	
	@Override
	public Object getElementAt(int index) {
		return this.selectedItems.get(index);
	}
}
