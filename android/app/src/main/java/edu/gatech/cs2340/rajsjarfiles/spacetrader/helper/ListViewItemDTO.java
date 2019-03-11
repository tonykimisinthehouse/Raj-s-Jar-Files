package edu.gatech.cs2340.rajsjarfiles.spacetrader.helper;

/**
 * @see "https://www.dev2qa.com/android-custom-listview-with-checkbox-example/"
 */
public class ListViewItemDTO {

    private boolean checked = false;

    private String itemText = "";

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

}