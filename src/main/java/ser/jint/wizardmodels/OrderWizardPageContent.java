package ser.jint.wizardmodels;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import org.netbeans.spi.wizard.WizardController;

import ser.jint.actions.AllItemsTableAction;
import ser.jint.actions.SelectedItemComboAction;
import ser.jint.bo.Items;
import ser.jint.bo.Order;
import ser.jint.bo.OrderDetail;
import ser.jint.bo.Validator;
import ser.jint.facade.OrderFacadeSubject;
import ser.jint.models.AllItemsTableModel;
import ser.jint.models.OtherModels;
import ser.jint.models.SelectedItemsTableModel;
import ser.jint.strategy.ListingStrategy;
import ser.jint.strategy.OrderIdListing;

/**
 * Created by Razor15 on 29/07/2015.
 */
public class OrderWizardPageContent {
	
	private static final Dimension	TEXT_ITEM_DIMENSION		= new Dimension(300,
			20);
	private static final Dimension	LABEL_ITEM_DIMENSION	= new Dimension(300,
			20);
	private static final Dimension	COMBO_ITEM_DIMENSION	= new Dimension(100,
			20);
	private static final Dimension	PANEL_RIGID_AREA		= new Dimension(50,
			5);
	private static final String[]	STRINGS					= new String[] {
			"Todos", "Libros", "Musica", "Electronica" };
	private static List<Items>				buyItems;
	private static SelectedItemsTableModel	selectedItemsTableModel;
	private static AllItemsTableModel		allItemsTableModel;
	private final FocusListener		listFocusListener		= new FocusAdapter() {
		public void focusGained(FocusEvent e) {
			JComponent c = (JComponent) e.getComponent();
			c.scrollRectToVisible(
					new Rectangle(0, 0, c.getWidth(), c.getHeight()));
		}
	};
	private Order							newOrder;
	private Map						global;
	private Action					itemAction;
	private Action					comboAction;
	private AllItemsTableAction		allItemsTableAction;
	
	public OrderWizardPageContent(Map p) {
		global = p;
		this.buyItems = new LinkedList<>();
		newOrder = new Order();
	}
	
	public JComponent getWelcomePage(WizardController wizardController) {
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
	
	public JComponent getOrderDataPage(
			final WizardController wizardController) {
			
		wizardController.setProblem(
				"No hay valores cargados; Cargar y validar para continuar");
				
		/* ########### CLIENT DATA SECTION ######### */
		JLabel lblClientName = new JLabel();
		JLabel lblClientIdNum = new JLabel();
		JLabel lblClientIdtype = new JLabel();
		final JTextField txtClientName = new JTextField();
		final JComboBox cmbClientIdType = new JComboBox();
		final JTextField txtClientIdNum = new JTextField();
		
		DefaultComboBoxModel dcbm = new DefaultComboBoxModel(
				new String[] { "DNI", "LE", "Pasaporte", "LC" });
				
		lblClientName.setText("Ingrese el nombre del cliente");
		lblClientIdtype.setText("Ingrese el tipo de documento del cliente");
		lblClientIdNum.setText("Ingrese el numero de documento del cliente");
		
		lblClientName.setMaximumSize(LABEL_ITEM_DIMENSION);
		lblClientIdtype.setMaximumSize(LABEL_ITEM_DIMENSION);
		lblClientIdNum.setMaximumSize(LABEL_ITEM_DIMENSION);
		txtClientName.setMaximumSize(TEXT_ITEM_DIMENSION);
		txtClientIdNum.setMaximumSize(TEXT_ITEM_DIMENSION);
		cmbClientIdType.setMaximumSize(COMBO_ITEM_DIMENSION);
		
		cmbClientIdType.setModel(dcbm);
		
		JPanel pnlClientData = new JPanel();
		pnlClientData.setBorder(
				BorderFactory.createTitledBorder("Datos del cliente"));
		pnlClientData.setLayout(new BoxLayout(pnlClientData, BoxLayout.Y_AXIS));
		
		pnlClientData.add(lblClientName);
		pnlClientData.add(txtClientName);
		
		pnlClientData.add(Box.createRigidArea(PANEL_RIGID_AREA));
		
		pnlClientData.add(lblClientIdtype);
		pnlClientData.add(cmbClientIdType);
		
		pnlClientData.add(Box.createRigidArea(PANEL_RIGID_AREA));
		
		pnlClientData.add(lblClientIdNum);
		pnlClientData.add(txtClientIdNum);
		
		/* ########## ORDER DATA SECTION ########## */
		JLabel lblOrderAddress = new JLabel();
		JLabel lblOrderZipAddress = new JLabel();
		JLabel lblOrderCreationDate = new JLabel();
		// JLabel lblContactPhone = new JLabel();
		final JTextField txtOrderAddress = new JTextField();
		final JTextField txtOrderZipAddress = new JTextField();
		final JTextField txtOrderCreationDate = new JTextField();
		// JTextField txtContactPhone = new JTextField();
		
		lblOrderAddress.setText("Ingrese la direccion de entrega del pedido");
		lblOrderZipAddress
				.setText("Ingrese el codigo postal de la direccion de entrega");
		lblOrderCreationDate.setText("Pedido creado el dia");
		// lblContactPhone.setText("Ingrese el numero de telefono de contacto");
		
		txtOrderCreationDate.setEnabled(false);
		txtOrderCreationDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
				.format(new Date(System.currentTimeMillis())));
		txtOrderCreationDate.setDisabledTextColor(new Color(255, 0, 0));
		
		lblOrderAddress.setMaximumSize(LABEL_ITEM_DIMENSION);
		lblOrderZipAddress.setMaximumSize(LABEL_ITEM_DIMENSION);
		lblOrderCreationDate.setMaximumSize(LABEL_ITEM_DIMENSION);
		// lblContactPhone.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		txtOrderAddress.setMaximumSize(TEXT_ITEM_DIMENSION);
		txtOrderZipAddress.setMaximumSize(TEXT_ITEM_DIMENSION);
		txtOrderCreationDate.setMaximumSize(TEXT_ITEM_DIMENSION);
		// txtContactPhone.setMaximumSize(this.TEXT_ITEM_DIMENSION);
		
		JPanel pnlOrderData = new JPanel();
		pnlOrderData.setBorder(
				BorderFactory.createTitledBorder("Datos del Pedido"));
		pnlOrderData.setLayout(new BoxLayout(pnlOrderData, BoxLayout.Y_AXIS));
		
		pnlOrderData.add(lblOrderAddress);
		pnlOrderData.add(txtOrderAddress);
		
		pnlOrderData.add(Box.createRigidArea(PANEL_RIGID_AREA));
		
		pnlOrderData.add(lblOrderZipAddress);
		pnlOrderData.add(txtOrderZipAddress);
		
		pnlOrderData.add(Box.createRigidArea(PANEL_RIGID_AREA));
		
		pnlOrderData.add(lblOrderCreationDate);
		pnlOrderData.add(txtOrderCreationDate);
		
		pnlOrderData.add(Box.createRigidArea(PANEL_RIGID_AREA));
		
		/*
		 * pnlOrderData.add(lblContactPhone); pnlOrderData.add(txtContactPhone);
		 */
		
		/* ############### VALIDATE BUTTON ################ */
		JPanel pnlValidator = new JPanel();
		pnlValidator.setLayout(new BoxLayout(pnlValidator, BoxLayout.Y_AXIS));
		
		JButton btnValidator = new JButton("Validar Campos");
		btnValidator.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String errors = "";
				int errorCount = 0;
				
				if (!Validator.isValidDNI(txtClientIdNum.getText())) {
					errors = errors + "DNI incorrecto;";
					errorCount += 1;
				}
				
				if (Validator.isTextEmpty(txtClientName.getText())) {
					errors = errors + "Nombre Cliente;";
					errorCount += 1;
				}
				
				if (Validator.isTextEmpty(txtOrderAddress.getText())) {
					errors = errors + "Direccion de entrega;";
					errorCount += 1;
				}
				
				if (!Validator.isValidZipCode(txtOrderZipAddress.getText())) {
					errors = errors + "Codigo Postal Argentino;";
					errorCount += 1;
				}
				
				if (errorCount > 0) {
					wizardController.setProblem(errors);
				} else {
					wizardController.setProblem(null);
					
					// newOrder = new Order();
					newOrder.setClientName(txtClientName.getText());
					newOrder.setClientIdentificationNumber(
							new Integer(txtClientIdNum.getText()));
					newOrder.setClientIdentificationType(
							(String) cmbClientIdType.getSelectedItem());
							
					newOrder.setOrderAddress(txtOrderAddress.getText());
					newOrder.setOrderZipAddress(txtOrderZipAddress.getText());
					try {
						newOrder.setCreationDate(
								new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
										.parse(txtOrderCreationDate.getText()));
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Error en fecha", JOptionPane.ERROR_MESSAGE);
						wizardController.setProblem(
								"Error en fecha: " + e1.getMessage());
					}
				}
			}
		});
		
		pnlValidator.add(btnValidator);
		
		JPanel conteiner = new JPanel();
		conteiner.setLayout(new BoxLayout(conteiner, BoxLayout.Y_AXIS));
		conteiner.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		conteiner.add(pnlClientData);
		conteiner.add(Box.createRigidArea(PANEL_RIGID_AREA));
		conteiner.add(pnlOrderData);
		conteiner.add(Box.createRigidArea(PANEL_RIGID_AREA));
		conteiner.add(pnlValidator);
		
		return conteiner;
	}
	
	private void loadItems() {
		Iterator<Items> itemsIterator = OrderFacadeSubject.getInstance()
				.getItemList().iterator();
				
		while (itemsIterator.hasNext()) {
			Items aux = itemsIterator.next();
			allItemsTableModel.addItems(aux.getItemDescription(), false);
		}
	}
	
	public JComponent getItemsSelectionPage(
			final WizardController wizardController) {
		wizardController
				.setProblem("Elija los productos a comprar para continuar");
				
		JPanel pnlListItems = new JPanel();
		pnlListItems.setLayout(new BoxLayout(pnlListItems, BoxLayout.Y_AXIS));
		pnlListItems.setMaximumSize(new Dimension(500, 300));
		
		selectedItemsTableModel = new SelectedItemsTableModel();
		allItemsTableModel = new AllItemsTableModel();
		
		this.comboAction = new SelectedItemComboAction(
selectedItemsTableModel,
				this.listFocusListener, allItemsTableModel);
				
		this.allItemsTableAction = new AllItemsTableAction(
allItemsTableModel,
				selectedItemsTableModel);
				
		loadItems();
		
		JTable tblAllItems = new JTable(allItemsTableModel);
		tblAllItems.setColumnModel(OtherModels.createColumnModelAll());
		tblAllItems.addMouseListener(this.allItemsTableAction);
		tblAllItems.setMaximumSize(new Dimension(500, 300));
		
		JScrollPane scrListItems = new JScrollPane(tblAllItems);
		scrListItems.getVerticalScrollBar().setUnitIncrement(3);
		scrListItems.setMaximumSize(new Dimension(300, 500));
		
		JComboBox cmbSelected = new JComboBox();
		cmbSelected.setModel(new DefaultComboBoxModel(STRINGS));
		cmbSelected.setMaximumSize(COMBO_ITEM_DIMENSION);
		cmbSelected.addActionListener(this.comboAction);
		
		JPanel pnlContainerList = new JPanel();
		pnlContainerList
				.setLayout(new BoxLayout(pnlContainerList, BoxLayout.Y_AXIS));
				
		pnlContainerList.add(cmbSelected);
		pnlContainerList.add(Box.createRigidArea(new Dimension(10, 1)));
		pnlContainerList.add(scrListItems);
		
		/* ############### ITEMS SELECTED ################## */
		JPanel pnlOrderItems = new JPanel();
		pnlOrderItems.setLayout(new BoxLayout(pnlOrderItems, BoxLayout.Y_AXIS));
		
		JTable listItems = new JTable(selectedItemsTableModel);
		listItems.setColumnModel(OtherModels.createColumnModel());
		
		JScrollPane scrOrderPane = new JScrollPane(listItems);
		scrOrderPane.getVerticalScrollBar().setUnitIncrement(3);
		scrOrderPane.setMaximumSize(new Dimension(300, 500));
		
		pnlOrderItems.add(scrOrderPane);
		
		/* ############## BUTTON VALIDATOR ################ */
		JPanel pnlValidate = new JPanel();
		pnlValidate.setLayout(new BoxLayout(pnlValidate, BoxLayout.Y_AXIS));
		
		JButton btnValidate = new JButton("Validar Datos");
		btnValidate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int charge = selectedItemsTableModel.getRowCount();
				
				if (charge < 1) {
					wizardController.setProblem(
							"Para continuar debe cargar algun item");
				} else {
					wizardController.setProblem(null);
				}
			}
		});
		
		pnlValidate.add(btnValidate);
		
		/* ############# ALL ITEMS AND SELECTED ITEMS ################## */
		JPanel containter = new JPanel();
		containter.setLayout(new BoxLayout(containter, BoxLayout.Y_AXIS));
		
		containter.add(pnlContainerList);
		containter.add(Box.createRigidArea(PANEL_RIGID_AREA));
		containter.add(pnlOrderItems);
		containter.add(Box.createRigidArea(PANEL_RIGID_AREA));
		containter.add(Box.createRigidArea(PANEL_RIGID_AREA));
		containter.add(pnlValidate);
		
		return containter;
	}
	
	public JComponent getFinishPage(WizardController wizardController) {
		Iterator<String> iterator = selectedItemsTableModel.getItemsSelected();
		List<OrderDetail> details = new LinkedList<>();
		
		while (iterator.hasNext()) {
			String aux = iterator.next();
			buyItems = OrderFacadeSubject.getInstance()
					.itemDescriptionSearch(aux);
					
			System.out.println("Item: " + aux + " = " + buyItems.size());
			
			if (buyItems.size() == 1) {
				Items input = buyItems.get(0);
				OrderDetail detail = new OrderDetail();
				detail.setItem(input);
				detail.setQuantity(selectedItemsTableModel.getValue(aux));
				details.add(detail);
			}
		}
		
		OrderFacadeSubject.getInstance().updateData(newOrder, details);
		
		OrderFacadeSubject.getInstance()
				.setStrategy(new OrderIdListing(ListingStrategy.DESC));
		Iterator<Order> orderIterator = OrderFacadeSubject.getInstance()
				.getOrderList().iterator();
				
		JTextArea result = new JTextArea();
		result.setHighlighter(null);
		result.setLineWrap(true);
		result.setWrapStyleWord(true);
		result.setEditable(false);
		
		while (orderIterator.hasNext()) {
			// System.out.println(orderIterator.next());
			result.setText(orderIterator.next().toString());
			break;
		}
		
		JScrollPane scrResult = new JScrollPane(result);
		scrResult.getVerticalScrollBar().setUnitIncrement(3);
		scrResult.setMaximumSize(new Dimension(300, 500));
		
		JPanel pnlResulPanel = new JPanel();
		pnlResulPanel.setLayout(new BoxLayout(pnlResulPanel, BoxLayout.Y_AXIS));
		
		pnlResulPanel.add(scrResult);
		pnlResulPanel.add(Box.createRigidArea(PANEL_RIGID_AREA));
		
		return pnlResulPanel;
	}
}