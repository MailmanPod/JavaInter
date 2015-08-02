package ser.jint.actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import ser.jint.models.AllItemsTableModel;
import ser.jint.models.SelectedItemsTableModel;

/**
 * Created by Razorback on 01/08/2015.
 */
public class AllItemsTableAction extends MouseAdapter {
	
	private AllItemsTableModel		allItemsTableModel;
	private SelectedItemsTableModel	selectedItemsListModel;
	
	public AllItemsTableAction(AllItemsTableModel allItemsTableModel,
			SelectedItemsTableModel selectedItemsListModel) {
		this.allItemsTableModel = allItemsTableModel;
		this.selectedItemsListModel = selectedItemsListModel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable input = (JTable) e.getSource();
		
		Object inputKey = input.getValueAt(input.getSelectedRow(),
				input.getSelectedColumn() - 1);
		Object inputValue = input.getValueAt(input.getSelectedRow(),
				input.getSelectedColumn());
				
		if (inputKey != null) {
			String key = (String) inputKey;
			Boolean value = (Boolean) inputValue;
			
			if (value) {
				String qt = JOptionPane.showInputDialog(null,
						"Ingrese la cantidad deseada", "Cantidad Items",
						JOptionPane.INFORMATION_MESSAGE);
						
				try {
					Integer q = new Integer(qt);
					
					if (q > 0) {
						this.selectedItemsListModel.addItem(key, q);
					} else {
						JOptionPane.showMessageDialog(null,
								"La cantidad debe ser mayor a cero",
								"Cantidad no Valida",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "No ingreso un numero",
							"Error", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				this.selectedItemsListModel.removeItem(key);
			}
		}
	}
}
