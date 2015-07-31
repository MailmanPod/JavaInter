package ser.jint.wizardmodels;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import ser.jint.bo.Order;
import ser.jint.models.SelectItemListAction;
import ser.jint.models.SelectedItemsListModel;

/**
 * Created by Razor15 on 29/07/2015.
 */
public class OrderWizardPageContent {

	private static final Dimension	TEXT_ITEM_DIMENSION		= new Dimension(250,
			20);
	private static final Dimension	LABEL_ITEM_DIMENSION	= new Dimension(300,
			20);
	private static final Dimension	COMBO_ITEM_DIMENSION	= new Dimension(100,
			20);
	private static final Dimension	PANEL_RIGID_AREA		= new Dimension(30,
			5);
	private final FocusListener		listFocusListener		= new FocusAdapter() {
		public void focusGained(FocusEvent e) {
			JComponent c = (JComponent) e.getComponent();
			c.scrollRectToVisible(
					new Rectangle(0, 0, c.getWidth(), c.getHeight()));
		}
	};
    private Map global;
    private Order newOrder;
	private Action					itemAction;
	private SelectedItemsListModel	listModel;

	public OrderWizardPageContent(Map p) {
        global = p;
    }

    public JComponent getWelcomePage(){
        JLabel lbl1 = new JLabel();
        lbl1.setText("BIENVENIDO AL ASISTENTE DE CREACION DE UNA NUEVA ORDEN");
        JPanel pn1 = new JPanel();
        pn1.add(lbl1);

        JLabel lbl2 = new JLabel();
        lbl2.setText("ESTE ASISTENTE LO GUIARA DURANTE EL PROCESO DE CREACION");
        JPanel pn2 = new JPanel();
        pn2.add(lbl2);

        JLabel lbl3 = new JLabel();
        lbl3.setText("PRECIONE CONTINUAR PARA EMPEZAR: ");
        JPanel pn3 = new JPanel();
        pn3.add(lbl3);

        JPanel container = new JPanel();
        BorderLayout bl = new BorderLayout();

        container.setLayout(bl);
        container.add(pn1, BorderLayout.NORTH);
        container.add(pn2, BorderLayout.CENTER);
        container.add(pn3, BorderLayout.SOUTH);

        return container;
    }

    public JComponent getOrderDataPage(){

        /*########### CLIENT DATA SECTION #########*/
        JLabel lblClientName = new JLabel();
        JLabel lblClientIdNum = new JLabel();
        JLabel lblClientIdtype = new JLabel();
        JTextField txtClientName = new JTextField();
        JComboBox cmbClientIdType = new JComboBox();
        JTextField txtClientIdNum = new JTextField();

        DefaultComboBoxModel dcbm = new DefaultComboBoxModel(new String[]{"DNI", "LE", "Pasaporte", "LC"});

        lblClientName.setText("Ingrese el nombre del cliente");
        lblClientIdtype.setText("Ingrese el tipo de documento del cliente");
        lblClientIdNum.setText("Ingrese el numero de documento del cliente");

		lblClientName.setMaximumSize(this.LABEL_ITEM_DIMENSION);
        lblClientIdtype.setMaximumSize(this.LABEL_ITEM_DIMENSION);
        lblClientIdNum.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		txtClientName.setMaximumSize(this.TEXT_ITEM_DIMENSION);
        txtClientIdNum.setMaximumSize(this.TEXT_ITEM_DIMENSION);
        cmbClientIdType.setMaximumSize(this.COMBO_ITEM_DIMENSION);

        cmbClientIdType.setModel(dcbm);

        JPanel pnlClientData = new JPanel();
        pnlClientData.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));
		pnlClientData.setLayout(new BoxLayout(pnlClientData, BoxLayout.Y_AXIS));

        pnlClientData.add(lblClientName);
        pnlClientData.add(txtClientName);

		pnlClientData.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		
        pnlClientData.add(lblClientIdtype);
        pnlClientData.add(cmbClientIdType);

		pnlClientData.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		
        pnlClientData.add(lblClientIdNum);
        pnlClientData.add(txtClientIdNum);

        /*########## ORDER DATA SECTION ##########*/
        JLabel lblOrderAddress = new JLabel();
        JLabel lblOrderZipAddress = new JLabel();
        JLabel lblOrderCreationDate = new JLabel();
        JLabel lblContactPhone = new JLabel();
        JTextField txtOrderAddress = new JTextField();
        JTextField txtOrderZipAddress = new JTextField();
        JTextField txtOrderCreationDate = new JTextField();
        JTextField txtContactPhone = new JTextField();

        lblOrderAddress.setText("Ingrese la direccion de entrega del pedido");
        lblOrderZipAddress.setText("Ingrese el codigo postal de la direccion de entrega");
        lblOrderCreationDate.setText("Pedido creado el dia");
        lblContactPhone.setText("Ingrese el numero de telefono de contacto");

        txtOrderCreationDate.setEnabled(false);
        txtOrderCreationDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(System.currentTimeMillis())));
        txtOrderCreationDate.setDisabledTextColor(new Color(255, 0, 0));

        lblOrderAddress.setMaximumSize(this.LABEL_ITEM_DIMENSION);
        lblOrderZipAddress.setMaximumSize(this.LABEL_ITEM_DIMENSION);
        lblOrderCreationDate.setMaximumSize(this.LABEL_ITEM_DIMENSION);
        lblContactPhone.setMaximumSize(this.LABEL_ITEM_DIMENSION);
        txtOrderAddress.setMaximumSize(this.TEXT_ITEM_DIMENSION);
        txtOrderZipAddress.setMaximumSize(this.TEXT_ITEM_DIMENSION);
        txtOrderCreationDate.setMaximumSize(this.TEXT_ITEM_DIMENSION);
        txtContactPhone.setMaximumSize(this.TEXT_ITEM_DIMENSION);

        JPanel pnlOrderData = new JPanel();
        pnlOrderData.setBorder(BorderFactory.createTitledBorder("Datos del Pedido"));
		pnlOrderData.setLayout(new BoxLayout(pnlOrderData, BoxLayout.Y_AXIS));

        pnlOrderData.add(lblOrderAddress);
        pnlOrderData.add(txtOrderAddress);
		
		pnlOrderData.add(Box.createRigidArea(this.PANEL_RIGID_AREA));

        pnlOrderData.add(lblOrderZipAddress);
        pnlOrderData.add(txtOrderZipAddress);

		pnlOrderData.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		
        pnlOrderData.add(lblOrderCreationDate);
        pnlOrderData.add(txtOrderCreationDate);
		
		pnlOrderData.add(Box.createRigidArea(this.PANEL_RIGID_AREA));

        pnlOrderData.add(lblContactPhone);
        pnlOrderData.add(txtContactPhone);

        JPanel conteiner = new JPanel();
		conteiner.setLayout(new BoxLayout(conteiner, BoxLayout.PAGE_AXIS));
        conteiner.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		conteiner.add(pnlClientData);
		conteiner.add(pnlOrderData);

        return conteiner;
    }

	private List<String> getTestItems() {
		List<String> resultSet = new ArrayList<>();
		resultSet.add("1 Item");
		resultSet.add("2 Item");
		resultSet.add("3 Item");
		resultSet.add("4 Item");
		resultSet.add("5 Item");
		resultSet.add("6 Item");
		resultSet.add("7 Item");
		resultSet.add("8 Item");
		resultSet.add("9 Item");
		resultSet.add("10 Item");
		resultSet.add("11 Item");
		resultSet.add("12 Item");
		resultSet.add("13 Item");
		resultSet.add("14 Item");
		resultSet.add("15 Item");
		resultSet.add("16 Item");
		resultSet.add("17 Item");
		resultSet.add("18 Item");
		resultSet.add("19 Item");
		resultSet.add("20 Item");
		return resultSet;
	}
	
	private void addItemToList(String name, boolean selected, JPanel target) {
		JCheckBox check = (JCheckBox) target.add(new JCheckBox(name, selected));
		check.addActionListener(this.itemAction);
		
		if (selected) {
			this.listModel.addItem(name);
		}
		check.addFocusListener(this.listFocusListener);
	}
	
    public JComponent getItemsSelectionPage(){
		JPanel pnlListItems = new JPanel();
		pnlListItems.setLayout(new BoxLayout(pnlListItems, BoxLayout.Y_AXIS));
		pnlListItems.setMaximumSize(new Dimension(300, 300));
		
		this.listModel = new SelectedItemsListModel();
		this.itemAction = new SelectItemListAction(this.listModel);
		
		Iterator<String> iterator = this.getTestItems().iterator();
		
		while (iterator.hasNext()) {
			addItemToList(iterator.next(), false, pnlListItems);
		}
		
		JScrollPane scrListItems = new JScrollPane(pnlListItems);
		scrListItems.getVerticalScrollBar().setUnitIncrement(3);
		scrListItems.setMaximumSize(new Dimension(300, 300));
		
		JPanel pnlContainerList = new JPanel();
		pnlContainerList
				.setLayout(new BoxLayout(pnlContainerList, BoxLayout.Y_AXIS));
		pnlContainerList.add(Box.createRigidArea(new Dimension(10, 1)));
		pnlContainerList.add(scrListItems);
		
		JPanel pnlOrderItems = new JPanel();
		pnlOrderItems.setLayout(new BoxLayout(pnlOrderItems, BoxLayout.Y_AXIS));
		JList listItems = new JList();
		listItems.setModel(this.listModel);
		
		JScrollPane scrOrderPane = new JScrollPane(listItems);
		scrOrderPane.getVerticalScrollBar().setUnitIncrement(3);
		scrOrderPane.setMaximumSize(new Dimension(250, 300));
		
		pnlOrderItems.add(scrOrderPane);
		
		JPanel containter = new JPanel();
		containter.setLayout(new BoxLayout(containter, BoxLayout.X_AXIS));
		containter.add(pnlContainerList);
		containter.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		containter.add(pnlOrderItems);
		
		return containter;
    }
}