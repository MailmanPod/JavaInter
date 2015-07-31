package ser.jint.models;

import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Created by Razor15 on 31/07/2015.
 */
public class SelectedItemComboAction extends AbstractAction {
    private SelectedItemsTableModel tableModel;

    public SelectedItemComboAction(SelectedItemsTableModel tableModel)
    {
        this.tableModel = tableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox combo = (JComboBox) e.getSource();
        int index = combo.getSelectedIndex();

        System.out.println("Item selected: " + combo.getItemAt(index));

    }
}
