package ser.jint.wizardmodels;

import java.awt.*;
import java.util.Map;

import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.modules.wizard.MergeMap;
import org.netbeans.spi.wizard.Wizard;

import ser.jint.bo.Order;

/**
 * Created by Razor15 on 29/07/2015.
 */
public class CreateOrder {

    public CreateOrder(){

    }
    public void main(){
        Runnable r = new Runnable() {
            public void run() {
                WizardOrder wo = new WizardOrder();
                Wizard wizard = wo.createWizard();

                Rectangle r = new Rectangle(100,100,700,700);
                Map prop = new MergeMap("propiedades");
                prop.put("newOrder", new Order());

                WizardDisplayer.showWizard(wizard, r, null, prop);
            }
        };
        Thread th = new Thread(r);
        th.start();
    }
}
