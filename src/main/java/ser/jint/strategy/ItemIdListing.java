package ser.jint.strategy;

import ser.jint.bo.Items;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Razorback on 18/07/2015.
 */
public class ItemIdListing extends ListingStrategyAdapter {
    private int listingMode;

    public ItemIdListing(int listingMode) {
        this.listingMode = listingMode;
    }

    @Override
    public void listItems(List<Items> list) {
        Collections.sort(list, new IdComaparator());
    }

    private class IdComaparator implements Comparator<Items> {

        public int compare(Items o1, Items o2) {
            return (listingMode == ListingStrategy.ASC) ? o1.getItemId() - o2.getItemId() : o2.getItemId() - o1.getItemId();
        }
    }
}
