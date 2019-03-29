package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

/**
 * Represents an item that can be sold on a planet.
 */
public class Item {

    private Good good; //Types of good
    private int quantity; //Quantity of good
    private int price; //Price of good (price when traded)

    /**
     * Creates an item using a builder.
     *
     * @param builder the item builder object
     */
    Item(ItemBuilder builder) {
        good = builder.good;
        quantity = builder.quantity;
        price = builder.price;
    }

    /**
     * @return the good's name
     */
    public String getGoodName() {
        return good.getName();
    }

    /**
     * @return returns the good
     */
    public Good getGood() {
        return this.good;
    }

    /**
     * Sets the price of this good.
     *
     * @param price the new price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the price of the good
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return the number of items available to be sold on the planet
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of items available to be sold on the planet.
     *
     * @param quantity the number of items
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Add to the number of items available.
     *
     * @param quantity the number to add
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * Subtracts from the number of items available.
     *
     * @param quantity the number to subtract by
     */
    public void subQuantity(int quantity) {
        this.quantity -= quantity;
    }

    /**
     * Class for creating an Item using the builder pattern.
     */
    public static class ItemBuilder {
        private Good good;
        private int quantity = 1;
        private int price = 0;

        /**
         * Creates an item builder object for a specific good.
         *
         * @param tg the trade good
         */
        public ItemBuilder(Good tg) {
            this.good = tg;
        }

        /**
         * Sets the quantity of the item.
         *
         * @param quantity the new quantity
         * @return the item builder object
         */
        public ItemBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Sets the price of the item.
         *
         * @param price the new price
         * @return the item builder object
         */
        public ItemBuilder price(int price) {
            this.price = price;
            return this;
        }

        /**
         * Builds the Item and returns the object.
         *
         * @return the new Item object
         */
        public Item build() {
            return new Item(this);
        }
    }
}
