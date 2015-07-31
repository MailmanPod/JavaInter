package ser.jint.models;

import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Created by Razorback on 31/07/2015.
 */
public class SelectItemListAction extends AbstractAction {
	
	private SelectedItemsListModel itemsListModel;
	
	public SelectItemListAction(SelectedItemsListModel itemsListModel) {
		this.itemsListModel = itemsListModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JCheckBox) {
			JCheckBox ch = (JCheckBox) e.getSource();
			
			if (ch.isSelected()) {
				this.itemsListModel.addItem(ch.getText());
			} else {
				this.itemsListModel.removeItem(ch.getText());
			}
		}
	}
}
