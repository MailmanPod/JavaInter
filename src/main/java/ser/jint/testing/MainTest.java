package ser.jint.testing;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ser.jint.bo.*;
import ser.jint.builder.ItemAutoSequence;
import ser.jint.facade.OrderFacadeSubject;

/**
 * Created by Razor15 on 16/07/2015.
 */
public class MainTest {
	
	private static void addOrder1() {
		Order o = new Order();
		o.setClientIdentificationNumber(34441144);
		o.setClientIdentificationType("DNI");
		o.setClientName("Damian Bruera");
		o.setOrderAddress("Av Colon 778");
		o.setOrderZipAddress("X500GBY");
		// o.setOrderNumber(OrderAutoSequence.getInstance().getNextSequence());
		o.setCreationDate(new Date(System.currentTimeMillis()));
		
		/* ########### ITEM NUMBER 1 ############# */
		
		Electronic e = new Electronic();
		e.setStock(89);
		e.setTax(0.21);
		e.setPrice(3000.00);
		e.setItemDescription("Samsung Gxy 6 LTE");
		e.setMark("Samsung");
		e.setType("GSM LTE");
		e.setItemId(ItemAutoSequence.getInstance().getNextSequence());
		
		OrderDetail detail = new OrderDetail();
		detail.setItem(e);
		detail.setQuantity(2);
		
		List<OrderDetail> tmp = new LinkedList<OrderDetail>();
		tmp.add(detail);
		
		/* ########### ITEM NUMBER 2 ############# */
		
		e = new Electronic();
		e.setStock(89);
		e.setTax(0.21);
		e.setPrice(3000.00);
		e.setItemDescription("LG G3 LTE");
		e.setMark("LG");
		e.setType("GSM LTE");
		e.setItemId(ItemAutoSequence.getInstance().getNextSequence());
		
		detail = new OrderDetail();
		detail.setItem(e);
		detail.setQuantity(7);
		
		tmp.add(detail);
		
		OrderFacadeSubject.getInstance().updateData(o, tmp);
	}
	
	private static void addOrder2() throws ParseException {
		Order o = new Order();
		o.setClientIdentificationNumber(35441188);
		o.setClientIdentificationType("DNI");
		o.setClientName("Anonim User");
		o.setOrderAddress("Av Colon 778");
		o.setOrderZipAddress("X500GBY");
		// o.setOrderNumber(OrderAutoSequence.getInstance().getNextSequence());
		o.setCreationDate(new Date(System.currentTimeMillis()));
		
		/* ########### ITEM NUMBER 1 ############# */
		
		Books e = new Books();
		e.setStock(4);
		e.setTax(0.21);
		e.setPrice(400.00);
		e.setItemDescription("Book of Pandas");
		e.setEditor("Editor Books");
		e.setPages(4500);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date d = df.parse("12/05/2014");
		e.setPublishDate(d);
		e.setItemId(ItemAutoSequence.getInstance().getNextSequence());
		
		OrderDetail detail = new OrderDetail();
		detail.setItem(e);
		detail.setQuantity(2);
		
		List<OrderDetail> tmp = new LinkedList<OrderDetail>();
		tmp.add(detail);
		
		/* ########### ITEM NUMBER 2 ############# */
		
		Clothes ez = new Clothes();
		ez.setStock(25);
		ez.setTax(0.21);
		ez.setPrice(190);
		ez.setItemDescription("Remera");
		ez.setMark("Mistral");
		ez.setSize("XL");
		ez.setItemId(ItemAutoSequence.getInstance().getNextSequence());
		
		detail = new OrderDetail();
		detail.setItem(ez);
		detail.setQuantity(7);
		
		tmp.add(detail);
		
		OrderFacadeSubject.getInstance().updateData(o, tmp);
	}
	
	public static void main(String[] args)
			throws IOException, ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException, ParseException {
			
		/*
		 * addOrder1(); addOrder2();
		 * 
		 * // OrderFacadeSubject.getInstance().serialize(); //
		 * OrderFacadeSubject.getInstance().deSerialize();
		 * OrderFacadeSubject.getInstance().rawPersistence();
		 * //OrderFacadeSubject.getInstance().getRawPersistence(); addOrder2();
		 * 
		 * List<Order> orderListing = OrderFacadeSubject.getInstance()
		 * .getOrderList();
		 * 
		 * Iterator<Order> iter = orderListing.iterator();
		 * 
		 * while (iter.hasNext()) { Order z = iter.next();
		 * System.out.println(z); }
		 * 
		 * OrderFacadeSubject.getInstance() .setStrategy(new
		 * OrderStateListing(ListingStrategy.DESC));
		 * OrderFacadeSubject.getInstance().strategyOrders();
		 * 
		 * orderListing = OrderFacadeSubject.getInstance().getOrderList();
		 * 
		 * iter = orderListing.iterator();
		 * 
		 * while (iter.hasNext()) { Order aux = iter.next(); System.out.println(
		 * "Order: " + aux.getOrderNumber() + " Date: " + new SimpleDateFormat(
		 * "dd/MM/yyyy HH:mm:ss") .format(aux.getCreationDate()) + " State: " +
		 * aux.getContextState()); }
		 */

		// new CreateOrder().main();
		
		if (Validator.isValidDNI("25337654"))
			System.out.println("DNI valido");

		/*
		 * System.out.println("Despachando ordenes");
		 * ofs.dispathOrders(manager.getOrderList()); iter =
		 * manager.getOrderList().iterator();
		 * 
		 * while (iter.hasNext()) { Order z = iter.next();
		 * System.out.println(z); }
		 */
		
		// System.out.println("Cancelando orden");
		// ofs.cancelOrder(manager.getOrderList());
		/*
		 * System.out.println("Entregando Pedidos realizados");
		 * ofs.deliveryOrders(manager.getOrderList()); iter =
		 * manager.getOrderList().iterator();
		 * 
		 * while (iter.hasNext()) { Order z = iter.next();
		 * System.out.println(z); }
		 */
		
		/*
		 * ItemManager managerItem = ItemManager.getInstance();
		 * 
		 * Electronic rh = new Electronic();
		 * 
		 * rh.setStock(89); rh.setTax(0.21); rh.setPrice(3000.00);
		 * rh.setItemDescription("Samsung Gxy 6 LTE"); rh.setMark("Samsung");
		 * rh.setType("GSM LTE"); rh.setItemId(200568);
		 * 
		 * managerItem.addItem(rh);
		 * 
		 * rh = new Electronic();
		 * 
		 * rh.setStock(99); rh.setTax(0.21); rh.setPrice(1500.00);
		 * rh.setItemDescription("LG G3 LTE"); rh.setMark("LGE"); rh.setType(
		 * "GSM LTE"); rh.setItemId(200569);
		 * 
		 * managerItem.addItem(rh);
		 * 
		 * CsvPersistence csv = new CsvPersistence();
		 * csv.persistObjects(ItemManager.getInstance().getItemsList(),
		 * CsvPersistence.FILE_NAME_ITEM);
		 * csv.persistObjects(OrderManager.getInstance().getOrderList(),
		 * CsvPersistence.FILE_NAME_ORDER);
		 */
		
		/*
		 * Persistable lk = (Persistable) Class.forName("ser.jint.bo." +
		 * "Electronic").newInstance();
		 * 
		 * Method m = lk.getClass().getMethod("setMark", String.class);
		 * 
		 * m.invoke(lk, "Pulenta");
		 * 
		 * System.out.println(lk.toString());
		 */
		/*
		 * System.out.println("##############################"); List<Order>
		 * orderListTest = (List<Order>) csv.recreateObjects(
		 * CsvPersistence.FILE_NAME_ORDER, CsvPersistence.BO_PATH);
		 * 
		 * Iterator<Order> iterator = orderListTest.iterator();
		 * 
		 * while (iterator.hasNext()) {
		 * System.out.println(iterator.next().toString()); }
		 * 
		 * System.out.println("##############################"); List<Items>
		 * itemListTest = (List<Items>) csv.recreateObjects(
		 * CsvPersistence.FILE_NAME_ITEM, CsvPersistence.BO_PATH);
		 * 
		 * Iterator<Items> iterator1 = itemListTest.iterator();
		 * 
		 * while (iterator1.hasNext()) {
		 * System.out.println(iterator1.next().toString()); }
		 * 
		 * System.out.println("##############################");
		 * ObjectSerializer serializer = new ObjectSerializer();
		 * serializer.serializeObjects(OrderManager.getInstance(),
		 * ObjectSerializer.SERIAL_ORDER);
		 * serializer.serializeObjects(ItemManager.getInstance(),
		 * ObjectSerializer.SERIAL_ITEMS);
		 * 
		 * System.out.println("##############################");
		 * ObjectSerializer serializer1 = new ObjectSerializer();
		 * 
		 * OrderManager manager1 = (OrderManager) serializer1
		 * .deserializeObject(ObjectSerializer.SERIAL_ORDER);
		 * 
		 * Iterator<Order> iterator2 = manager1.getOrderList().iterator();
		 * 
		 * while (iterator2.hasNext()) {
		 * System.out.println(iterator2.next().toString()); }
		 * 
		 * System.out.println("##############################");
		 * csv.persistObjects(CsvPersistence.FILE_NAME_SEQUENCER,
		 * OrderAutoSequence.getInstance(), ItemAutoSequence.getInstance());
		 * List<Sequencer> list = (List<Sequencer>) csv.recreateObjects(
		 * CsvPersistence.FILE_NAME_SEQUENCER, CsvPersistence.AUTO_PATH, true);
		 * Iterator<Sequencer> iterator3 = list.iterator();
		 * 
		 * while (iterator3.hasNext()) { Sequencer st = iterator3.next();
		 * 
		 * if (st instanceof OrderAutoSequence) { System.out.println(
		 * "Order Sequence: " +
		 * OrderAutoSequence.getInstance().getNextSequence()); } else if (st
		 * instanceof ItemAutoSequence) { System.out.println("Item Sequence: " +
		 * ItemAutoSequence.getInstance().getNextSequence()); } }
		 * 
		 * System.out.println("Using one: " +
		 * OrderAutoSequence.getInstance().getNextSequence());
		 * System.out.println("Using two: " +
		 * OrderAutoSequence.getInstance().getNextSequence());
		 */
	}
}
