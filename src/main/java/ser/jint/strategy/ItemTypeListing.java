package ser.jint.strategy;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ser.jint.bo.Items;

/**
 * Created by Razorback on 02/08/2015.
 */
public class ItemTypeListing extends ListingStrategyAdapter {
	
	private int listingMode;
	
	public ItemTypeListing(int listingMode) {
		this.listingMode = listingMode;
	}
	
	@Override
	public void listItems(List<Items> list) {
		Collections.sort(list, new TypeComparator());
	}
	
	private class TypeComparator implements Comparator<Items> {
		
		private Collator textComparator = Collator.getInstance();
		
		@Override
		public int compare(Items o1, Items o2) {
			return (ListingStrategy.ASC == listingMode)
					? textComparator.compare(o1.getItemType(), o2.getItemType())
					: textComparator.compare(o2.getItemType(),
							o1.getItemType());
		}
	}
}
