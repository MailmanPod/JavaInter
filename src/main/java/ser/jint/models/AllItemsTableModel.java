package ser.jint.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * Created by Razorback on 01/08/2015.
 */
public class AllItemsTableModel extends AbstractTableModel {
	
	public static final int	VALUE_COLUMN	= 0;
	public static final int	CHECK_COLUMN	= 1;
	
	private Vector<String>			allITems;
	private Map<String, Boolean>	mapItems;
	
	public AllItemsTableModel() {
		this.allITems = new Vector<>();
		this.mapItems = new HashMap<>();
	}
	
	public void addItems(String key, Boolean value) {
		allITems.add(key);
		mapItems.put(key, value);
		
		int index = allITems.size();
		
		fireTableRowsInserted(index, index);
	}
	
	public void removeItem(String key) {
		this.allITems.remove(key);
		this.mapItems.remove(key);
		
		int index = this.allITems.size();
		
		fireTableRowsDeleted(index, index);
	}
	
	public boolean containsItem(String key) {
		return allITems.contains(key);
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
			case AllItemsTableModel.VALUE_COLUMN:
				return "Item";
			case AllItemsTableModel.CHECK_COLUMN:
				return "Seleccion";
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (AllItemsTableModel.CHECK_COLUMN == columnIndex);
	}
	
	@Override
	public int getRowCount() {
		return allITems.size();
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case AllItemsTableModel.VALUE_COLUMN:
				return String.class;
			case AllItemsTableModel.CHECK_COLUMN:
				return Boolean.class;
		}
		return null;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		String key;
		Boolean value;
		
		key = this.allITems.elementAt(rowIndex);
		value = this.mapItems.get(key);
		
		switch (columnIndex) {
			case AllItemsTableModel.VALUE_COLUMN:
				return key;
			case AllItemsTableModel.CHECK_COLUMN:
				return value;
		}
		
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String key;
		
		key = this.allITems.elementAt(rowIndex);
		
		switch (columnIndex) {
			case AllItemsTableModel.VALUE_COLUMN:
				break;
			case AllItemsTableModel.CHECK_COLUMN:
				this.mapItems.replace(key, (Boolean) aValue);
				break;
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
