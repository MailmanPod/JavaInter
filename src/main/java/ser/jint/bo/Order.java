package ser.jint.bo;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import ser.jint.persistence.CsvPersistence;
import ser.jint.persistence.Persistable;
import ser.jint.state.*;

/**
 * Created by Razorback on 09/07/2015.
 */
public class Order implements Comparable<Order>, Serializable, Persistable {
	
	private String	clientName;
	private String	clientIdentificationType;
	private int		clientIdentificationNumber;
	private int		orderNumber;
	private String	orderAddress;
	private String	orderZipAddress;
	private boolean	delivered;
	private boolean	canceled;
	private String	dispatchCenter;
	private String	contextState;
	private Date	creationDate;
	
	private List<OrderDetail> orderDetails;
	
	private State	createState;
	private State	deliveredState;
	private State	dispatchState;
	private State	canceledState;
	private State	currentState;
	
	public Order() {
		this.createState = new CreateState(this);
		this.deliveredState = new DeliveredState(this);
		this.dispatchState = new DispatchState(this);
		this.canceledState = new CancelState(this);
		
		this.currentState = this.createState;
		
		this.delivered = false;
		this.canceled = false;
		this.dispatchCenter = "Centro de Distribucion";
		this.contextState = State.NOT_ASSIGNED;
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getClientIdentificationType() {
		return clientIdentificationType;
	}
	
	public void setClientIdentificationType(String clientIdentificationType) {
		this.clientIdentificationType = clientIdentificationType;
	}
	
	public int getClientIdentificationNumber() {
		return clientIdentificationNumber;
	}
	
	public void setClientIdentificationNumber(int clientIdentificationNumber) {
		this.clientIdentificationNumber = clientIdentificationNumber;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getOrderAddress() {
		return orderAddress;
	}
	
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	
	public String getOrderZipAddress() {
		return orderZipAddress;
	}
	
	public void setOrderZipAddress(String orderZipAddress) {
		this.orderZipAddress = orderZipAddress;
	}
	
	public boolean isDelivered() {
		return delivered;
	}
	
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	
	public String getDispatchCenter() {
		return dispatchCenter;
	}
	
	public void setDispatchCenter(String dispatchCenter) {
		this.dispatchCenter = dispatchCenter;
	}
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public State getDispatchState() {
		return dispatchState;
	}
	
	public void setDispatchState(State dispatchState) {
		this.dispatchState = dispatchState;
	}
	
	public State getDeliveredState() {
		return deliveredState;
	}
	
	public void setDeliveredState(State deliveredState) {
		this.deliveredState = deliveredState;
	}
	
	public State getCreateState() {
		return createState;
	}
	
	public void setCreateState(State createState) {
		this.createState = createState;
	}
	
	public String getContextState() {
		return contextState;
	}
	
	public void setContextState(String contextState) {
		this.contextState = contextState;
	}
	
	public State getCanceledState() {
		return canceledState;
	}
	
	public void setCanceledState(State canceledState) {
		this.canceledState = canceledState;
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	public double getOrderTotal() {
		return orderTotal();
	}
	
	private double orderTotal() {
		double total = 0.0;
		
		Iterator<OrderDetail> iter = this.orderDetails.iterator();
		
		while (iter.hasNext()) {
			total += iter.next().getDetailTotal();
		}
		
		return total;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public boolean isCanceled() {
		return canceled;
	}
	
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	/*
	 * public void create(LinkedList<OrderDetail> details) {
	 * this.currentState.assignDetails(details); }
	 */
	
	/*
	 * public void dispatch() { this.currentState.assignDispatchCenter(); }
	 */
	
	/*
	 * public void delivery() { this.currentState.delivery(); }
	 */
	
	public int compareTo(Order o) {
		int inter = 0;
		
		if (this.getOrderNumber() > o.getOrderNumber()) {
			inter = 1;
		} else if (this.getOrderNumber() < o.getOrderNumber()) {
			inter = -1;
		} else if (this.getOrderNumber() == o.getOrderNumber()) {
			inter = 0;
		}
		
		return inter;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
			
		Order order = (Order) o;
		
		return orderNumber == order.orderNumber;
		
	}
	
	@Override
	public int hashCode() {
		int hash = 5;
		int result = 53 * hash + Objects.hashCode(this.orderNumber);
		
		return result;
	}
	
	private String persistenceOrderConstant() {
		StringBuilder builder = new StringBuilder();
		builder.append("#" + this.getClass().getSimpleName() + "#");
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getOrderNumber());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getClientName());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getClientIdentificationType());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getClientIdentificationNumber());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getOrderAddress());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getOrderZipAddress());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getDispatchCenter());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.isDelivered());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.isCanceled());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(this.getContextState());
		builder.append(CsvPersistence.SEPARATOR);
		
		builder.append(
				"#" + this.getCurrentState().getClass().getSimpleName() + "#");
		builder.append(CsvPersistence.SEPARATOR);
		
		return builder.toString();
	}
	
	public String persistenceString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(persistenceOrderConstant());
		
		Iterator<OrderDetail> iterator = this.getOrderDetails().iterator();
		
		while (iterator.hasNext()) {
			// builder.append(persistenceOrderConstant());
			builder.append(iterator.next().persistenceString());
			builder.append(CsvPersistence.SEPARATOR);
			// builder.append(CsvPersistence.LINE_SEPARATOR);
		}
		
		builder.append(CsvPersistence.LINE_SEPARATOR);
		
		return builder.toString();
	}
	
	public void rebuildObject(Stack<String> tokens)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
		this.orderDetails = new LinkedList<OrderDetail>();
		
		String v = tokens.pop();
		this.setOrderNumber(new Integer(v));
		
		this.setClientName(tokens.pop());
		this.setClientIdentificationType(tokens.pop());
		
		v = tokens.pop();
		this.setClientIdentificationNumber(new Integer(v));
		
		this.setOrderAddress(tokens.pop());
		this.setOrderZipAddress(tokens.pop());
		this.setDispatchCenter(tokens.pop());
		this.setDelivered(new Boolean(tokens.pop()));
		this.setCanceled(new Boolean(tokens.pop()));
		this.setContextState(tokens.pop());
		
		String aux = tokens.pop();
		String classForName = aux.substring(aux.indexOf("#") + 1,
				aux.lastIndexOf("#"));
		Constructor cons = Class.forName("ser.jint.state." + classForName)
				.getConstructor(Order.class);
				
		State eh = (State) cons.newInstance(this);
		this.setCurrentState(eh);
		
		while (!tokens.empty()) {
			String cd = tokens.pop();
			classForName = cd.substring(cd.indexOf("#") + 1,
					cd.lastIndexOf("#"));
			OrderDetail instance = (OrderDetail) Class
					.forName("ser.jint.bo." + classForName).newInstance();
			instance.rebuildObject(tokens);
			this.orderDetails.add(instance);
		}
		
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("######### Client Data ########\n");
		builder.append("Client Name: " + this.getClientName() + "\n");
		builder.append("Client Type/Identification number: "
				+ this.getClientIdentificationType() + " / "
				+ this.getClientIdentificationNumber() + "\n");
		builder.append("######## Order Data #########\n");
		builder.append("Order number: " + this.getOrderNumber() + "\n");
		builder.append("Order address: " + this.getOrderAddress() + "\n");
		builder.append("Order zip code:" + this.getOrderZipAddress() + "\n");
		builder.append(
				"Order dispatch center: " + this.getDispatchCenter() + "\n");
		builder.append("Is Delivered: " + this.isDelivered() + "\n");
		builder.append("Is canceled: " + this.isCanceled() + "\n");
		builder.append("Order State: " + this.getContextState() + "\n");
		builder.append(
				"Order instance: " + this.getCurrentState().getClass() + "\n");
		builder.append("######### Order Details ########\n");
		Iterator<OrderDetail> iterator = this.getOrderDetails().iterator();
		
		while (iterator.hasNext()) {
			builder.append(iterator.next().toString());
		}
		
		return builder.toString();
	}
}
