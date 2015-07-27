package ser.jint.criteria;

import java.text.Collator;
import java.util.LinkedList;
import java.util.List;

import ser.jint.bo.Items;

/**
 * Created by Razorback on 27/07/2015.
 */
public class TypeItemCriteria implements ItemCriteria {
	
	private String itemType;
	
	public TypeItemCriteria(String itemType) {
		this.itemType = itemType;
	}
	
	public List<Items> matchCriteria(List<Items> entry) {
		List<Items> resultSet = new LinkedList<Items>();
		Collator text = Collator.getInstance();
		
		for (Items e : entry) {
			if (text.compare(e.getItemType(), itemType) == 0) {
				resultSet.add(e);
			}
		}
		
		return resultSet;
	}
}
