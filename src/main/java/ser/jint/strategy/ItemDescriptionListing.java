package ser.jint.strategy;

import ser.jint.bo.Items;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Razor15 on 15/07/2015.
 */
public class ItemDescriptionListing extends ListingStrategyAdapter {

    private int listingMode;

    public ItemDescriptionListing(int listingMode) {
        this.listingMode = listingMode;
    }

    @Override
    public void listItems(List<Items> list) {
        Collections.sort(list, new ItemDescriptionComparator());
    }

    private class ItemDescriptionComparator implements Comparator<Items> {

        private Collator textComparator = Collator.getInstance();

        public int compare(Items o1, Items o2) {
            return (ItemDescriptionListing.this.listingMode == ListingStrategy.ASC) ? textComparator.compare(o1.getItemDescription(), o2.getItemDescription()) :
                    textComparator.compare(o2.getItemDescription(), o1.getItemDescription());
        }
    }
}
