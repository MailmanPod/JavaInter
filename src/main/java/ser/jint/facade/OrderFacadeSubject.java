package ser.jint.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ser.jint.bo.Order;
import ser.jint.bo.OrderDetail;
import ser.jint.command.*;
import ser.jint.criteria.ClientNumberCriteria;
import ser.jint.criteria.Criteria;
import ser.jint.criteria.DispatchCriteria;
import ser.jint.observer.Observer;
import ser.jint.observer.Subject;
import ser.jint.singleton.ItemManager;
import ser.jint.singleton.OrderManager;
import ser.jint.strategy.ListingStrategy;

/**
 * Created by Razor15 on 14/07/2015.
 */
public class OrderFacadeSubject implements Subject {
	
	private static OrderFacadeSubject instance;
	
	private List<Observer>		registeredObservers;
	private Observer			createCommand;
	private Order				dataOrder;
	private List<OrderDetail>	dataDetails;
	private OrderManager		orderManager;
	private ItemManager			itemManager;
	private Command				command;
	private ListingStrategy		strategy;
	private Criteria			clientNumberOrders;
	private Criteria			orderNumberOrders;
	private Criteria			dispatchOrders;
	
	private OrderFacadeSubject() {
		this.registeredObservers = new ArrayList<Observer>();
		this.createCommand = new CreateCommand(this);
		this.orderManager = OrderManager.getInstance();
	}
	
	public static OrderFacadeSubject getInstance() {
		if (instance == null) {
			instance = new OrderFacadeSubject();
		}
		
		return instance;
	}
	
	public void notifyAllObserver() {
		ListIterator<Observer> iterator = this.registeredObservers
				.listIterator();
				
		while (iterator.hasNext()) {
			Observer o = iterator.next();
			o.update(this.dataOrder, this.dataDetails);
		}
	}
	
	public void registerObserver(Observer observer) {
		if (observer != null) {
			this.registeredObservers.add(observer);
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	public void removeObserver(Observer observer) {
		if (this.registeredObservers.contains(observer)) {
			this.registeredObservers.remove(observer);
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	public void updateData(Order order, List<OrderDetail> details) {
		this.dataOrder = order;
		this.dataDetails = details;
		notifyAllObserver();
	}
	
	public void dispathOrders(List<Order> orders) {
		Iterator<Order> iter = orders.iterator();
		while (iter.hasNext()) {
			Command c = new DispathCommand(iter.next());
			c.changeStatus();
		}
	}
	
	public void deliveryOrders(List<Order> orders) {
		Iterator<Order> iter = orders.iterator();
		while (iter.hasNext()) {
			Command command = new DeliveryCommand(iter.next());
			command.changeStatus();
		}
	}
	
	public void cancelOrder(List<Order> orders) {
		Iterator<Order> iter = orders.iterator();
		while (iter.hasNext()) {
			Command command = new CancelCommand(iter.next());
			command.changeStatus();
		}
	}
	
	public void setStrategy(ListingStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void listOrders() {
		this.strategy.listOrders(this.orderManager.getOrderList());
	}
	
	public void listItems() {
		this.strategy.listItems(this.itemManager.getItemsList());
	}
	
	public List<Order> cltNmbSearch(int cltNmb) {
		this.clientNumberOrders = new ClientNumberCriteria(cltNmb);
		return this.clientNumberOrders
				.matchCriteria(orderManager.getOrderList());
	}
	
	public List<Order> orderNumberSearch(int orderNmb) {
		this.orderNumberOrders = new ClientNumberCriteria(orderNmb);
		return this.clientNumberOrders
				.matchCriteria(orderManager.getOrderList());
	}
	
	public List<Order> dispatchCenterSearch(String dispatchCenter) {
		this.dispatchOrders = new DispatchCriteria(dispatchCenter);
		return this.dispatchOrders.matchCriteria(orderManager.getOrderList());
	}
}
