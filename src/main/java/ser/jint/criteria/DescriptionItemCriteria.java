package ser.jint.criteria;

import java.text.Collator;
import java.util.LinkedList;
import java.util.List;

import ser.jint.bo.Items;

/**
 * Created by Razorback on 02/08/2015.
 */
public class DescriptionItemCriteria implements ItemCriteria {
	
	private String itemDescription;
	
	public DescriptionItemCriteria(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	@Override
	public List<Items> matchCriteria(List<Items> entry) {
		List<Items> resultSet = new LinkedList<>();
		Collator text = Collator.getInstance();
		
		for (Items e : entry) {
			if (text.compare(e.getItemDescription(), itemDescription) == 0) {
				resultSet.add(e);
			}
		}
		
		return resultSet;
	}
}
