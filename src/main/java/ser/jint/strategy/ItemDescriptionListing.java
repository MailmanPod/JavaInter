package ser.jint.strategy;

import ser.jint.bo.Items;
import ser.jint.singleton.ItemManager;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Razor15 on 15/07/2015.
 */
public class ItemDescriptionListing extends ListingStrategyAdapter {

    private int listingMode;
    private ItemManager manager;

    public ItemDescriptionListing(int listingMode) {
        this.listingMode = listingMode;
        this.manager = ItemManager.getInstance();
    }

    @Override
    public void getItemListing() {
        Collections.sort(this.manager.getListItems(), new ItemDescriptionComparator());
    }

    private class ItemDescriptionComparator implements Comparator<Items> {

        private Collator textComparator = Collator.getInstance();

        public int compare(Items o1, Items o2) {
            return (ItemDescriptionListing.this.listingMode == ListingStrategy.ASC) ? textComparator.compare(o1.getItemDescription(), o2.getItemDescription()) :
                    textComparator.compare(o2.getItemDescription(), o1.getItemDescription());
        }
    }
}
