package ser.jint.strategy;

import ser.jint.bo.Items;
import ser.jint.singleton.ItemManager;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Razorback on 18/07/2015.
 */
public class ItemIdListing extends ListingStrategyAdapter {
    private int listingMode;
    private ItemManager manager;

    public ItemIdListing(int listingMode) {
        this.listingMode = listingMode;
        manager = ItemManager.getInstance();
    }

    @Override
    public void listItems() {
        Collections.sort(manager.getListItems(), new IdComaparator());
    }

    private class IdComaparator implements Comparator<Items> {

        public int compare(Items o1, Items o2) {
            return (listingMode == ListingStrategy.ASC) ? o1.getItemId() - o2.getItemId() : o2.getItemId() - o1.getItemId();
        }
    }
}
