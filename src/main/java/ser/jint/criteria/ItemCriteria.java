package ser.jint.criteria;

import java.util.List;

import ser.jint.bo.Items;

/**
 * Created by Razorback on 26/07/2015.
 */
public interface ItemCriteria {
	
	public List<Items> matchCriteria(List<Items> entry);
}
