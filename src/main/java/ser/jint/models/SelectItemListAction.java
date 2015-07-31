package ser.jint.models;

import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Created by Razorback on 31/07/2015.
 */
public class SelectItemListAction extends AbstractAction {
	
	//private SelectedItemsListModel itemsListModel;
	private SelectedItemsTableModel itemsListModel;
	
	public SelectItemListAction(SelectedItemsTableModel itemsListModel) {
		this.itemsListModel = itemsListModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JCheckBox) {
			JCheckBox ch = (JCheckBox) e.getSource();
			
			if (ch.isSelected()) {
				String input = JOptionPane.showInputDialog(null,
						"Ingrese la cantidad a ordenar", "Cantidad",
						JOptionPane.YES_OPTION);

				this.itemsListModel.addItem(ch.getText(), new Integer(input));
			} else {
				this.itemsListModel.removeItem(ch.getText());
			}
		}
	}
}
