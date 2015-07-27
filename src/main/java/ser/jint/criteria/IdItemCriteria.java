package ser.jint.criteria;

import java.util.LinkedList;
import java.util.List;

import ser.jint.bo.Items;

/**
 * Created by Razor15 on 27/07/2015.
 */
public class IdItemCriteria implements ItemCriteria {
	
	private int itemId;
	
	public IdItemCriteria(int itemId) {
		this.itemId = itemId;
	}
	
	public List<Items> matchCriteria(List<Items> entry) {
		List<Items> resultSet = new LinkedList<Items>();
		
		for (Items e : entry) {
			if (e.getItemId() - this.itemId == 0) {
				resultSet.add(e);
			}
		}
		
		return resultSet;
	}
}
