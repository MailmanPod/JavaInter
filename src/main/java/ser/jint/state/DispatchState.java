package ser.jint.state;

import java.io.*;
import java.util.Properties;

import ser.jint.bo.Order;

/**
 * Created by Razorback on 09/07/2015.
 */
public class DispatchState extends OrderStateAdapter implements Serializable {
	
	private static final String	FILE_NAME	= "props.properties";
	private Order				orderContext;
	private Properties			props;
	private String[]			params		= { "Ocasa", "Andreani", "OCA",
			"Correo Argentino", "FedEx", "UPS", "Western Union" };
			
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
	
	private void createProps() throws IOException {
		File file = new File(FILE_NAME);
		BufferedWriter bufferW = null;
		BufferedReader bufferR = null;
		
		try {
			if (!file.exists()) {
				bufferW = new BufferedWriter(new FileWriter(file));
				props.store(bufferW, null);
			} else {
				bufferR = new BufferedReader(new FileReader(file));
				props.load(bufferR);
			}
			
		} catch (IOException ex) {
			chargeParams();
		} finally {
			if (bufferR != null) {
				bufferR.close();
			} else if (bufferW != null) {
				bufferW.close();
			}
		}
	}
	
	@Override
	public void assignDispatchCenter() {
		try {
			createProps();
		} catch (IOException e) {
			chargeParams();
		}
		int random = (int) (Math.random() * this.props.keySet().toArray().length
				- 1);
		this.orderContext.setDispatchCenter(this.props.getProperty(
				String.valueOf(this.props.keySet().toArray()[random])));
				
		this.orderContext.setContextState(State.DISPATCH_STATE);
		this.orderContext
				.setCurrentState(this.orderContext.getDeliveredState());
	}
}
