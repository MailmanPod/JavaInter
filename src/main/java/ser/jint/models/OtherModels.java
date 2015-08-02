package ser.jint.models;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Created by Razor15 on 31/07/2015.
 */
public class OtherModels {

    public static TableColumnModel createColumnModel() {
        DefaultTableColumnModel columnModel = new DefaultTableColumnModel();

        TableColumn column = new TableColumn();
        column.setModelIndex(SelectedItemsTableModel.ITEM_NAME_COLUMKN);
        column.setHeaderValue("Nombre");
		column.setPreferredWidth(25);
        columnModel.addColumn(column);


        column = new TableColumn();
		column.setModelIndex(SelectedItemsTableModel.ITEM_QUANTITY_COLUMNS);
        column.setHeaderValue("Cantidad");
        column.setPreferredWidth(120);
		columnModel.addColumn(column);
		
		return columnModel;
	}
	
	public static TableColumnModel createColumnModelAll() {
		DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
		
		TableColumn column = new TableColumn();
		column.setModelIndex(SelectedItemsTableModel.ITEM_NAME_COLUMKN);
		column.setHeaderValue("Item");
		column.setPreferredWidth(50);
		columnModel.addColumn(column);
		
		column = new TableColumn();
		column.setModelIndex(SelectedItemsTableModel.ITEM_QUANTITY_COLUMNS);
		column.setHeaderValue("Seleccion");
		column.setPreferredWidth(5);
        columnModel.addColumn(column);

        return columnModel;
    }
}
