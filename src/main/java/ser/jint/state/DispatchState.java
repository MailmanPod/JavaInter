package ser.jint.state;

import ser.jint.bo.Order;

import java.io.*;
import java.util.Properties;

/**
 * Created by Razorback on 09/07/2015.
 */
public class DispatchState extends OrderStateAdapter implements Serializable {

    private static final String FILE_NAME = "props.properties";
    private Order orderContext;
    private Properties props;
    private String[] params = {"Ocasa", "Andreani", "OCA", "Correo Argentino", "FedEx", "UPS", "Western Union"};

    public DispatchState(Order orderContext) {
        this.orderContext = orderContext;
        props = new Properties();
        chargeParams();
    }

    private void chargeParams() {
        for (int i = 0; i < params.length; i++) {
            props.put(String.valueOf(i + 1), params[i]);
        }
    }

    private void createProps() {
        File file = new File(FILE_NAME);
        FileOutputStream fos = null;
        FileInputStream fis = null;

        try {
            if (!file.exists()) {
                fos = new FileOutputStream(file);
                props.store(fos, null);
            } else {
                fis = new FileInputStream(file);
                props.load(fis);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            //chargeParams();
        } finally {
            try {
                if(fos != null){
                    fos.close();

                }else if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void assignDispatchCenter() {
        createProps();
        int random = (int) (Math.random() * this.props.keySet().toArray().length - 1);
        this.orderContext.setDispatchCenter(this.props.getProperty(String.valueOf(this.props.keySet().toArray()[random])));

        this.orderContext.setContextState(State.DISPATCH_STATE);
        this.orderContext.setCurrentState(this.orderContext.getDeliveredState());
    }
}
