package ser.jint.wizardmodels;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.*;

import ser.jint.bo.Order;

/**
 * Created by Razor15 on 29/07/2015.
 */
public class OrderWizardPageContent {

    private Map global;
    private Order newOrder;

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

		// txtClientName.setPreferredSize(new Dimension(100, 20));
		txtClientName.setMaximumSize(new Dimension(600, 20));

        cmbClientIdType.setModel(dcbm);

        JPanel pnlClientData = new JPanel();
        pnlClientData.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));
		pnlClientData.setLayout(new BoxLayout(pnlClientData, BoxLayout.Y_AXIS));

        pnlClientData.add(lblClientName);
        pnlClientData.add(txtClientName);

		pnlClientData.add(Box.createRigidArea(new Dimension(30, 5)));
		
        pnlClientData.add(lblClientIdtype);
        pnlClientData.add(cmbClientIdType);

		pnlClientData.add(Box.createRigidArea(new Dimension(30, 5)));
		
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

        JPanel pnlOrderData = new JPanel();
        pnlOrderData.setBorder(BorderFactory.createTitledBorder("Datos del Pedido"));
		pnlOrderData.setLayout(new BoxLayout(pnlOrderData, BoxLayout.Y_AXIS));

        pnlOrderData.add(lblOrderAddress);
        pnlOrderData.add(txtOrderAddress);
		
		pnlOrderData.add(Box.createRigidArea(new Dimension(30, 5)));

        pnlOrderData.add(lblOrderZipAddress);
        pnlOrderData.add(txtOrderZipAddress);

		pnlOrderData.add(Box.createRigidArea(new Dimension(30, 5)));
		
        pnlOrderData.add(lblOrderCreationDate);
        pnlOrderData.add(txtOrderCreationDate);
		
		pnlOrderData.add(Box.createRigidArea(new Dimension(30, 5)));

        pnlOrderData.add(lblContactPhone);
        pnlOrderData.add(txtContactPhone);

        JPanel conteiner = new JPanel();
		conteiner.setLayout(new BoxLayout(conteiner, BoxLayout.Y_AXIS));
		conteiner.add(pnlClientData);
		conteiner.add(pnlOrderData);

        return conteiner;
    }
}