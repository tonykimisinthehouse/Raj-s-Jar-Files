package edu.gatech.cs2340.rajsjarfiles.spacetrader.helper;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;

/**
 * Modified helper class
 * @see "https://www.dev2qa.com/android-custom-listview-with-checkbox-example/"
 */
public class ListViewItemDTO {

    private boolean checked;

    private Item good;
    private String itemText = "";

    /**
     * Check if checked
     * @return true if checked
     */
    public boolean isChecked() {
        return this.checked;
    }

    /**
     * Set item on the list to check
     * @param checked true if you want to check
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * Get item's text
     * @return item's text
     */
    public String getItemText() {
        return this.itemText;
    }

    /**
     * Get good of the selected item
     * @return good
     */
    public Item getGood() {
        return this.good;
    }

    /**
     * Set good of the item in the list
     * @param item item to be set
     */
    public void setGood(Item item) {
        this.good = item;
        this.itemText = item.toText();
    }

}