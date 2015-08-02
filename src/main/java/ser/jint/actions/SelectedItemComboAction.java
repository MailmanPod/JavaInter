package ser.jint.actions;

import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;

import javax.swing.*;

import ser.jint.models.AllItemsTableModel;
import ser.jint.models.SelectedItemsTableModel;
import ser.jint.testing.BuildTestingSets;

/**
 * Created by Razor15 on 31/07/2015.
 */
public class SelectedItemComboAction extends AbstractAction {
	private SelectedItemsTableModel	tableModel;
	private FocusListener			focusListener;
	private AllItemsTableModel		target;
	
	public SelectedItemComboAction(SelectedItemsTableModel tableModel,
			FocusListener listener, AllItemsTableModel target) {
		this.tableModel = tableModel;
		this.focusListener = listener;
		this.target = target;
	}
	
	private void addItemToList(String name, boolean selected) {
		target.addItems(name, selected);
	}
	
	private void removeItemToList(String name) {
		target.removeItem(name);
	}
	
	private void clear() {
		Iterator<String> iterator = BuildTestingSets.getAll().iterator();
		
		while (iterator.hasNext()) {
			String aux = iterator.next();
			
			if (target.containsItem(aux)) {
				target.removeItem(aux);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox combo = (JComboBox) e.getSource();
		int index = combo.getSelectedIndex();
		
		String in = (String) combo.getItemAt(index);
		
		switch (in) {
			case "Electronica":
				clear();
				Iterator<String> iterator = BuildTestingSets.getItemForList()
						.iterator();
				while (iterator.hasNext()) {
					String axu = iterator.next();
					addItemToList(axu, this.tableModel.containsKey(axu));
				}
				break;
			case "Libros":
				clear();
				Iterator<String> iter = BuildTestingSets.getItemForList1()
						.iterator();
				while (iter.hasNext()) {
					String aux = iter.next();
					addItemToList(aux, this.tableModel.containsKey(aux));
				}
				break;
		}
		
	}
}
