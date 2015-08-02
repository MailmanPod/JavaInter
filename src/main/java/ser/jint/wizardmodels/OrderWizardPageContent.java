package ser.jint.wizardmodels;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.*;

import org.netbeans.spi.wizard.WizardController;

import ser.jint.actions.AllItemsTableAction;
import ser.jint.actions.SelectedItemComboAction;
import ser.jint.bo.Order;
import ser.jint.bo.Validator;
import ser.jint.models.AllItemsTableModel;
import ser.jint.models.OtherModels;
import ser.jint.models.SelectedItemsTableModel;

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
			"Libros", "Ropa", "Electronica" };
	private final FocusListener		listFocusListener		= new FocusAdapter() {
		public void focusGained(FocusEvent e) {
			JComponent c = (JComponent) e.getComponent();
			c.scrollRectToVisible(
					new Rectangle(0, 0, c.getWidth(), c.getHeight()));
		}
	};
	private Map						global;
	private Order					newOrder;
	private Action					itemAction;
	private Action					comboAction;

	private SelectedItemsTableModel	selectedItemsTableModel;
	private AllItemsTableModel		allItemsTableModel;
	private AllItemsTableAction		allItemsTableAction;
	
	public OrderWizardPageContent(Map p) {
		global = p;
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
		
		lblClientName.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		lblClientIdtype.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		lblClientIdNum.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		txtClientName.setMaximumSize(this.TEXT_ITEM_DIMENSION);
		txtClientIdNum.setMaximumSize(this.TEXT_ITEM_DIMENSION);
		cmbClientIdType.setMaximumSize(this.COMBO_ITEM_DIMENSION);
		
		cmbClientIdType.setModel(dcbm);
		
		JPanel pnlClientData = new JPanel();
		pnlClientData.setBorder(
				BorderFactory.createTitledBorder("Datos del cliente"));
		pnlClientData.setLayout(new BoxLayout(pnlClientData, BoxLayout.Y_AXIS));
		
		pnlClientData.add(lblClientName);
		pnlClientData.add(txtClientName);
		
		pnlClientData.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		
		pnlClientData.add(lblClientIdtype);
		pnlClientData.add(cmbClientIdType);
		
		pnlClientData.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		
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
		
		lblOrderAddress.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		lblOrderZipAddress.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		lblOrderCreationDate.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		// lblContactPhone.setMaximumSize(this.LABEL_ITEM_DIMENSION);
		txtOrderAddress.setMaximumSize(this.TEXT_ITEM_DIMENSION);
		txtOrderZipAddress.setMaximumSize(this.TEXT_ITEM_DIMENSION);
		txtOrderCreationDate.setMaximumSize(this.TEXT_ITEM_DIMENSION);
		// txtContactPhone.setMaximumSize(this.TEXT_ITEM_DIMENSION);
		
		JPanel pnlOrderData = new JPanel();
		pnlOrderData.setBorder(
				BorderFactory.createTitledBorder("Datos del Pedido"));
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
					
					newOrder = new Order();
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
		conteiner.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		conteiner.add(pnlOrderData);
		conteiner.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		conteiner.add(pnlValidator);
		
		return conteiner;
	}
	
	public JComponent getItemsSelectionPage(WizardController wizardController) {
		JPanel pnlListItems = new JPanel();
		pnlListItems.setLayout(new BoxLayout(pnlListItems, BoxLayout.Y_AXIS));
		pnlListItems.setMaximumSize(new Dimension(300, 300));
		
		this.selectedItemsTableModel = new SelectedItemsTableModel();
		this.allItemsTableModel = new AllItemsTableModel();
		
		this.comboAction = new SelectedItemComboAction(
				this.selectedItemsTableModel, this.listFocusListener,
				this.allItemsTableModel);
				
		this.allItemsTableAction = new AllItemsTableAction(
				this.allItemsTableModel, this.selectedItemsTableModel);
				
		JTable tblAllItems = new JTable(this.allItemsTableModel);
		tblAllItems.setColumnModel(OtherModels.createColumnModelAll());
		tblAllItems.addMouseListener(this.allItemsTableAction);
		
		JScrollPane scrListItems = new JScrollPane(tblAllItems);
		scrListItems.getVerticalScrollBar().setUnitIncrement(3);
		scrListItems.setMaximumSize(new Dimension(300, 280));
		
		JComboBox cmbSelected = new JComboBox();
		cmbSelected.setModel(new DefaultComboBoxModel(this.STRINGS));
		cmbSelected.setMaximumSize(this.COMBO_ITEM_DIMENSION);
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
		
		JTable listItems = new JTable(this.selectedItemsTableModel);
		listItems.setColumnModel(OtherModels.createColumnModel());
		
		JScrollPane scrOrderPane = new JScrollPane(listItems);
		scrOrderPane.getVerticalScrollBar().setUnitIncrement(3);
		scrOrderPane.setMaximumSize(new Dimension(250, 300));
		
		pnlOrderItems.add(scrOrderPane);
		
		/* ############# ALL ITEMS AND SELECTED ITEMS ################## */
		JPanel containter = new JPanel();
		containter.setLayout(new BoxLayout(containter, BoxLayout.X_AXIS));
		
		containter.add(pnlContainerList);
		containter.add(Box.createRigidArea(this.PANEL_RIGID_AREA));
		containter.add(pnlOrderItems);
		
		return containter;
	}
}