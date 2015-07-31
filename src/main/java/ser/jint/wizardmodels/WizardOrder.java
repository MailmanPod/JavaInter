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
	
	private OrderWizardPageContent content;

    public WizardOrder(){
        super(generalTitle,pageNames, pageTitles);
    }

    @Override
    protected JComponent createPanel(WizardController wizardController, String s, Map map) {
		this.content = new OrderWizardPageContent(map);

        switch(s){
            case "welcome":
				return content.getWelcomePage();
            case "orderData":
				return content.getOrderDataPage();
            case "orderItem":
				return content.getItemsSelectionPage();
            case "finalize":
                return new JPanel();
        }

        return null;
    }
}
