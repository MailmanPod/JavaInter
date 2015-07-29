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
public class WizardPageContent {

    private Map global;
    private Order newOrder;

    public WizardPageContent(Map p){
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

        /*########### OTHER DATA #############*/
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Ingrese a continuacion los datos de la orden");
        JPanel pnlTitle = new JPanel();
        pnlTitle.setLayout(new GridLayout(1,1));

        pnlTitle.add(lblTitle);

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


        cmbClientIdType.setModel(dcbm);

        JPanel pnlClientData = new JPanel();
        pnlClientData.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));
        pnlClientData.setLayout(new GridLayout(3,2));

        pnlClientData.add(lblClientName);
        pnlClientData.add(txtClientName);

        pnlClientData.add(lblClientIdtype);
        pnlClientData.add(cmbClientIdType);

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
        pnlOrderData.setLayout(new GridLayout(20, 1));

        pnlOrderData.add(lblOrderAddress);
        pnlOrderData.add(txtOrderAddress);

        pnlOrderData.add(lblOrderZipAddress);
        pnlOrderData.add(txtOrderZipAddress);

        pnlOrderData.add(lblOrderCreationDate);
        pnlOrderData.add(txtOrderCreationDate);

        pnlOrderData.add(lblContactPhone);
        pnlOrderData.add(txtContactPhone);

        JPanel conteiner = new JPanel();
        conteiner.setLayout(new BorderLayout());
        conteiner.add(pnlTitle, BorderLayout.NORTH);
        conteiner.add(pnlClientData, BorderLayout.CENTER);
        conteiner.add(pnlOrderData, BorderLayout.SOUTH);

        return conteiner;
    }
}