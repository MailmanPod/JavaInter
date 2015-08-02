package ser.jint.actions;

import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;

import javax.swing.*;

import ser.jint.bo.Items;
import ser.jint.facade.OrderFacadeSubject;
import ser.jint.models.AllItemsTableModel;
import ser.jint.models.SelectedItemsTableModel;

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
		Iterator<Items> iterator = OrderFacadeSubject.getInstance()
				.getItemList().iterator();
				
		while (iterator.hasNext()) {
			String aux = iterator.next().getItemDescription();
			
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
			case "Todos":
				clear();
				Iterator<Items> itemsIterator = OrderFacadeSubject.getInstance()
						.getItemList().iterator();
						
				while (itemsIterator.hasNext()) {
					Items aux = itemsIterator.next();
					addItemToList(aux.getItemDescription(), this.tableModel
							.containsKey(aux.getItemDescription()));
				}
				break;
			case "Musica":
				String typeR = ItemTypes.values()[0].getItemType();
				clear();
				Iterator<Items> iterator = OrderFacadeSubject.getInstance()
						.itemTypeSearch(typeR).iterator();
				while (iterator.hasNext()) {
					Items axu = iterator.next();
					addItemToList(axu.getItemDescription(), this.tableModel
							.containsKey(axu.getItemDescription()));
				}
				break;
			case "Electronica":
				String typeE = ItemTypes.values()[1].getItemType();
				clear();
				Iterator<Items> iter = OrderFacadeSubject.getInstance()
						.itemTypeSearch(typeE).iterator();
				while (iter.hasNext()) {
					String aux = iter.next().getItemDescription();
					addItemToList(aux, this.tableModel.containsKey(aux));
				}
				break;
			case "Libros":
				String typeL = ItemTypes.values()[2].getItemType();
				clear();
				Iterator<Items> iterL = OrderFacadeSubject.getInstance()
						.itemTypeSearch(typeL).iterator();
				while (iterL.hasNext()) {
					String aux = iterL.next().getItemDescription();
					addItemToList(aux, this.tableModel.containsKey(aux));
				}
				break;
		}
		
	}
	
	private enum ItemTypes {
		MUSIC("Music"), ELECTRONIC("Electronic"), BOOKS("Books");
		
		private String itemType;
		
		private ItemTypes(String type) {
			this.itemType = type;
		}
		
		public String getItemType() {
			return this.itemType;
		}
	}
}
