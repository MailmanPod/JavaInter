package ser.jint.builder;

import java.io.Serializable;
import java.util.Date;

import ser.jint.bo.Order;

/**
 * Created by Razor15 on 10/07/2015.
 */
public class OrderBuilder implements Serializable {
	
	private static int	CLIENT_ID_NUMBER	= 2;
	private static int	CLIENT_ID_TYPE		= 4;
	private static int	CLIENT_NAME			= 8;
	private static int	ORDER_ADDRESS		= 16;
	private static int	ORDER_NUMBER		= 32;
	private static int	ORDER_ZIP_ADDRESS	= 64;
	
	private int requiredInfo;
	
	private Order newOrder;
	
	public OrderBuilder() {
		this.newOrder = new Order();
		this.requiredInfo = 0;
	}
	
	public void buildClientIdNum(int clientIdNumber) {
		this.newOrder.setClientIdentificationNumber(clientIdNumber);
	}
	
	public void buildClientIdType(String clientIdType) {
		this.newOrder.setClientIdentificationType(clientIdType);
	}
	
	public void buildClientName(String clientName) {
		this.newOrder.setClientName(clientName);
	}
	
	public void buildOrderAddress(String orderAddress) {
		this.newOrder.setOrderAddress(orderAddress);
	}
	
	public void buildOrderNumber() {
		int number = OrderAutoSequence.getInstance().getNextSequence();
		
		this.newOrder.setOrderNumber(number);
	}
	
	public void buildOrderZipAddress(String zipAddress) {
		this.newOrder.setOrderZipAddress(zipAddress);
	}
	
	public void buildCreationDate(Date creationDate) {
		this.newOrder.setCreationDate(creationDate);
	}
	
	public Order getInstance() {
		return this.newOrder;
	}
}
