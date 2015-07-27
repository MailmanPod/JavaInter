package ser.jint.facade;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ser.jint.bo.Items;
import ser.jint.bo.Order;
import ser.jint.bo.OrderDetail;
import ser.jint.builder.ItemAutoSequence;
import ser.jint.builder.OrderAutoSequence;
import ser.jint.command.*;
import ser.jint.criteria.*;
import ser.jint.observer.Observer;
import ser.jint.observer.Subject;
import ser.jint.persistence.CsvPersistence;
import ser.jint.persistence.ObjectSerializer;
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
	private OrderCriteria		clientNumberOrders;
	private OrderCriteria		orderNumberOrders;
	private OrderCriteria		dispatchOrders;
	private ItemCriteria		typeCriteria;
	private ItemCriteria		idCriteria;
	
	private OrderFacadeSubject() {
		this.registeredObservers = new ArrayList<Observer>();
		this.createCommand = new CreateCommand(this);
		this.orderManager = OrderManager.getInstance();
	}
	
	// <editor-fold defaultstate="collapse" desc="Singleton/Facade Pattern">
	public static OrderFacadeSubject getInstance() {
		if (instance == null) {
			instance = new OrderFacadeSubject();
		}
		
		return instance;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="Collapsed" desc="Observer/State Pattern -
	// Create Orders">
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
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Command/State Pattern -
	// Change State Orders">
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
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Strategy Pattern - Listing
	// Elements">
	public void setStrategy(ListingStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void listOrders() {
		this.strategy.listOrders(this.orderManager.getOrderList());
	}
	
	public void listItems() {
		this.strategy.listItems(this.itemManager.getItemsList());
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Criteria Pattern - Search
	// Elements">
	public List<Order> cltNmbSearch(int cltNmb) {
		this.clientNumberOrders = new ClientNumberOrderCriteria(cltNmb);
		return this.clientNumberOrders
				.matchCriteria(orderManager.getOrderList());
	}
	
	public List<Order> orderNumberSearch(int orderNmb) {
		this.orderNumberOrders = new ClientNumberOrderCriteria(orderNmb);
		return this.clientNumberOrders
				.matchCriteria(orderManager.getOrderList());
	}
	
	public List<Order> dispatchCenterSearch(String dispatchCenter) {
		this.dispatchOrders = new DispatchOrderCriteria(dispatchCenter);
		return this.dispatchOrders.matchCriteria(orderManager.getOrderList());
	}
	
	public List<Items> itemTypeSearch(String itemType) {
		this.typeCriteria = new TypeItemCriteria(itemType);
		return this.typeCriteria.matchCriteria(itemManager.getItemsList());
	}
	
	public List<Items> idTypeSearch(int idItem) {
		this.idCriteria = new IdItemCriteria(idItem);
		return this.idCriteria.matchCriteria(itemManager.getItemsList());
	}
	// </editor-fold>
	
	public void updateItem(Items newItemCopy) {
		if (itemManager.getItemsList().contains(newItemCopy)) {
			int indexof = itemManager.getItemsList().indexOf(newItemCopy);
			
			itemManager.getItemsList().set(indexof, newItemCopy);
		}
	}
	
	// <editor-fold defaultstate="collapse" desc="RAW Persistence /
	// Serialization">
	public void rawPersistence() throws IOException {
		CsvPersistence csv = new CsvPersistence();
		csv.persistObjects(orderManager.getOrderList(),
				CsvPersistence.FILE_NAME_ORDER);
		csv.persistObjects(itemManager.getItemsList(),
				CsvPersistence.FILE_NAME_ITEM);
		csv.persistObjects(CsvPersistence.FILE_NAME_SEQUENCER,
				OrderAutoSequence.getInstance(),
				ItemAutoSequence.getInstance());
	}
	
	public void getRawPersistence() throws IllegalAccessException,
			InvocationTargetException, IOException, InstantiationException,
			NoSuchMethodException, ClassNotFoundException {
		CsvPersistence csv = new CsvPersistence();
		this.orderManager.setOrderList((List<Order>) csv.recreateObjects(
				CsvPersistence.FILE_NAME_ORDER, CsvPersistence.BO_PATH));
		this.itemManager.setItemList((List<Items>) csv.recreateObjects(
				CsvPersistence.FILE_NAME_ITEM, CsvPersistence.BO_PATH));
		csv.recreateObjects(CsvPersistence.FILE_NAME_SEQUENCER,
				CsvPersistence.AUTO_PATH, true);
	}
	
	public void serialize() throws IOException {
		CsvPersistence csv = new CsvPersistence();
		
		ObjectSerializer os = new ObjectSerializer();
		os.serializeObjects(this.orderManager, ObjectSerializer.SERIAL_ORDER);
		os.serializeObjects(this.itemManager, ObjectSerializer.SERIAL_ITEMS);
		csv.persistObjects(CsvPersistence.FILE_NAME_SEQUENCER,
				OrderAutoSequence.getInstance(),
				ItemAutoSequence.getInstance());
	}
	
	public void deSerialize() throws IllegalAccessException,
			InvocationTargetException, IOException, InstantiationException,
			NoSuchMethodException, ClassNotFoundException {
		CsvPersistence csv = new CsvPersistence();
		ObjectSerializer os = new ObjectSerializer();
		this.orderManager = (OrderManager) os
				.deserializeObject(ObjectSerializer.SERIAL_ORDER);
		this.itemManager = (ItemManager) os
				.deserializeObject(ObjectSerializer.SERIAL_ITEMS);
				
		csv.recreateObjects(CsvPersistence.FILE_NAME_SEQUENCER,
				CsvPersistence.AUTO_PATH, true);
	}
	// </editor-fold>
}
