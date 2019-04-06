package edu.gatech.cs2340.rajsjarfiles.spacetrader.helper;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;

/**
 * Modified helper class
 * @see "https://www.dev2qa.com/android-custom-listview-with-checkbox-example/"
 */
public class ListViewItemDTO {

    private boolean checked = false;

    private Item good;
    private String itemText = "";

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getItemText() {
        return this.itemText;
    }

    public Item getGood() {
        return this.good;
    }

    public void setGood(Item item) {
        this.good = item;
        this.itemText = item.toText();
    }

}