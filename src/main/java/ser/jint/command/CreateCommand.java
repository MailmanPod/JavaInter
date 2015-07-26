package ser.jint.command;

import java.util.LinkedList;
import java.util.List;

import ser.jint.bo.Order;
import ser.jint.bo.OrderDetail;
import ser.jint.builder.OrderBuilder;
import ser.jint.observer.Observer;
import ser.jint.observer.Subject;
import ser.jint.singleton.OrderManager;

/**
 * Created by Razor15 on 13/07/2015.
 */
public class CreateCommand implements Command, Observer {
	
	private Order				dataOrder;
	private List<OrderDetail>	dataDetail;
	private Subject				orderSubject;
	
	public CreateCommand(Subject subject) {
		this.orderSubject = subject;
		this.orderSubject.registerObserver(this);
	}
	
	public void changeStatus() {
		OrderBuilder orderBuilder = new OrderBuilder();
		
		orderBuilder.buildClientIdNum(
				this.dataOrder.getClientIdentificationNumber());
		orderBuilder.buildClientIdType(
				this.dataOrder.getClientIdentificationType());
		orderBuilder.buildClientName(this.dataOrder.getClientName());
		orderBuilder.buildOrderAddress(this.dataOrder.getOrderAddress());
		orderBuilder.buildOrderNumber();
		orderBuilder.buildOrderZipAddress(this.dataOrder.getOrderZipAddress());
		orderBuilder.buildCreationDate(this.dataOrder.getCreationDate());
		Order newOrder = orderBuilder.getInstance();
		
		List<OrderDetail> tmpDetails = new LinkedList<OrderDetail>();
		
		for (int i = 0; i < this.dataDetail.size(); i++) {
			tmpDetails.add(this.dataDetail.get(i));
		}
		
		newOrder.getCurrentState().assignDetails(tmpDetails);
		
		OrderManager manager = OrderManager.getInstance();
		manager.addOrder(newOrder);
		
	}
	
	public void update(Order order, List<OrderDetail> details) {
		this.dataOrder = order;
		this.dataDetail = details;
		changeStatus();
	}
}
