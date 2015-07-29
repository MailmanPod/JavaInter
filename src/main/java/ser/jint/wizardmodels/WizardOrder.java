package ser.jint.wizardmodels;

import java.util.Map;

import javax.swing.*;

import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPanelProvider;

/**
 * Created by Razor15 on 29/07/2015.
 */
public class WizardOrder extends WizardPanelProvider {

    private static final String[] pageNames ={"welcome", "orderData", "orderItem", "finalize"};
    private static final String[] pageTitles ={"Bienvenido", "Datos de la Orden", "Items a comprar", "Finalizado"};
    private static final String generalTitle = "Asistente para la creacion de un pedido de delivery";

    public WizardOrder(){
        super(generalTitle,pageNames, pageTitles);
    }

    @Override
    protected JComponent createPanel(WizardController wizardController, String s, Map map) {

        switch(s){
            case "welcome":
                return new WizardPageContent(map).getWelcomePage();
            case "orderData":
                return new WizardPageContent(map).getOrderDataPage();
            case "orderItem":
                return new JPanel();
            case "finalize":
                return new JPanel();
        }

        return null;
    }
}
