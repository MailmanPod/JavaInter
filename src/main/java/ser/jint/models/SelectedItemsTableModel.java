package ser.jint.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * Created by Razor15 on 31/07/2015.
 */
public class SelectedItemsTableModel extends AbstractTableModel {
	
	public static final int	ITEM_NAME_COLUMKN		= 0;
	public static final int	ITEM_QUENTITY_COLUMNS	= 1;
	
	private Vector<String>			items;
	private Map<String, Integer>	selectedItems;
	
	public SelectedItemsTableModel() {
		this.selectedItems = new HashMap<>();
		this.items = new Vector<>();
	}
	
	public void addItem(String key, Integer value) {
		this.selectedItems.put(key, value);
		this.items.add(key);
		int index = items.size();
		
		fireTableRowsInserted(index, index);
	}
	
	public void removeItem(String key) {
		this.selectedItems.remove(key);
		this.items.remove(key);
		
		int index = items.size();
		fireTableRowsDeleted(index, index);
	}
	
	@Override
	public int getRowCount() {
		return this.items.size();
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String key;
		Integer value;
		
		key = this.items.elementAt(rowIndex);
		value = this.selectedItems.get(key);
		
		switch (columnIndex) {
			case ITEM_NAME_COLUMKN:
				return key;
			case ITEM_QUENTITY_COLUMNS:
				return value;
		}
		
		return null;
	}
}
