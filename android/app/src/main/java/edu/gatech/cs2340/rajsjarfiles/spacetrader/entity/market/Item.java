package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Events;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.ResourceClassification;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.TechLevel;

public class Item {

    private static Random rand = new Random();

    private String name;
    private int quantity;
    private int price;
    private boolean toBeBought;

    Item(ItemBuilder builder) {
        name = builder.good.getName();
        quantity = builder.quantity;
        price = builder.price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public static class ItemBuilder{
        private Good good;
        private int quantity;
        private int price;
        private ResourceClassification resourceClass;
        private Events events;
        private TechLevel techLevel;

        ItemBuilder(TradeGoods tg) {
            this.good = tg;
        }

        public ItemBuilder resourceClass(ResourceClassification rc) {
            this.resourceClass = rc;
            return this;
        }

        public ItemBuilder event(Events ev) {
            this.events = ev;
            return this;
        }

        public ItemBuilder techLevel(TechLevel tl) {
            this.techLevel = tl;
            return this;
        }

        public ItemBuilder calculatePrice() {

            int basePrice = good.getBasePrice();
            int dynamicPrice = good.getIPL() * (techLevel.ordinal() - good.getMTLP());
            int variancePrice = (basePrice * rand.nextInt(good.getVar())) / 10;
            int finalPrice = basePrice + dynamicPrice +variancePrice;

            if (resourceClass == good.getCR()) {
                finalPrice = finalPrice / 3 * 2;
            }

            if (resourceClass == good.getER()) {
                finalPrice = finalPrice * 3 / 2;
            }

            if (events == good.getIE()) {
                finalPrice = finalPrice * 3;
            }

            this.price = finalPrice;
            return this;
        }

        public ItemBuilder calculateQuantity() {

            int maxQuantity = 30;
            int minQuantity = 1;

            if (resourceClass == good.getCR()) {
                minQuantity = 15;
            }

            if (resourceClass == good.getER()) {
                maxQuantity = 15;
            }

            if (events == good.getIE()) {
                minQuantity = minQuantity / 2;
                maxQuantity = maxQuantity / 2;
            }

            quantity = rand.nextInt(maxQuantity - minQuantity) + minQuantity;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
