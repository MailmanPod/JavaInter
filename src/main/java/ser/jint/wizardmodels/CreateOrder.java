package ser.jint.wizardmodels;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.modules.wizard.MergeMap;
import org.netbeans.spi.wizard.Wizard;

import ser.jint.bo.Order;

/**
 * Created by Razor15 on 29/07/2015.
 */
public class CreateOrder {

	private static final int	FORM_WIDTH	= 800;
    private static final int FORM_HEIGHT = 650;

    public CreateOrder(){

    }
    public void main(){
        Runnable r = new Runnable() {
            public void run() {
				
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| UnsupportedLookAndFeelException
						| IllegalAccessException e) {
					e.printStackTrace();
				}
				
                WizardOrder wo = new WizardOrder();
                Wizard wizard = wo.createWizard();

				Dimension tamPantalla = Toolkit.getDefaultToolkit()
						.getScreenSize();
						
				Rectangle r = new Rectangle((tamPantalla.width - FORM_WIDTH) / 2,
						(tamPantalla.height - FORM_HEIGHT) / 2, FORM_WIDTH, FORM_HEIGHT);
						
                Map prop = new MergeMap("propiedades");
                prop.put("newOrder", new Order());

                WizardDisplayer.showWizard(wizard, r, null, prop);
            }
        };
        Thread th = new Thread(r);
        th.start();
    }
}
