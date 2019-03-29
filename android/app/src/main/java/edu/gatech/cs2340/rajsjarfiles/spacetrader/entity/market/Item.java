package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

public class Item {

    private Good good; //Types of good
    private int quantity; //Quantity of good
    private int price; //Price of good (price when traded)

    Item(ItemBuilder builder) {
        good = builder.good;
        quantity = builder.quantity;
        price = builder.price;
    }

    public String getGoodName() {
        return good.getName();
    }

    public Good getGood() {
        return this.good;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
    public void subQuantity(int quantity) {this.quantity -= quantity; }

    @Override
    public String toString() {
        return quantity + " " + good.getName() + " at " + price + " units each.";
    }

    public static class ItemBuilder{
        private Good good;
        private int quantity = 1;
        private int price = 0;

       public ItemBuilder(Good tg) {
            this.good = tg;
       }

       public ItemBuilder quantity(int quantity) {
           this.quantity = quantity;
           return this;
       }

       public ItemBuilder price(int price) {
           this.price = price;
           return this;
       }

       public Item build() {
            return new Item(this);
        }
    }
}
