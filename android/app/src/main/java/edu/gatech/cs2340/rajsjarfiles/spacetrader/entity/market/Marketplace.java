package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.MarketTransactionValidator;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionOrder;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionResult;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Events;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.ResourceClassification;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.TechLevel;


/**
 *  Representation of a Planet's Marketplace based on the planet's
 *  tech level and trade good's attributes
 */
public class Marketplace {
    private static final int START_MAX_QUANTITY = 30;
    private static final int START_MIN_QUANTITY = 1;

    private static final int CR_MIN_QUANTITY = 15;
    private static final int ER_MAX_QUANTITY = 15;

    private static Random rand = new Random();

    // EnumMap that store trade goods and its quantity
    private EnumMap<TradeGoods, Item> tradeGoodsInMarketMap;

    private String planetName;
    private TechLevel techLevel;
    private ResourceClassification resource;
    private Events event;

    /**
     * Constructor for marketplace
     *
     * @param pn planet name
     * @param techLevel tech level
     * @param event events
     * @param resource resource class
     */
    public Marketplace(String pn, TechLevel techLevel,
                       Events event, ResourceClassification resource) {

        this.planetName = pn;
        this.techLevel = techLevel;
        this.resource = resource;
        this.event = event;

        tradeGoodsInMarketMap = new EnumMap<>(TradeGoods.class);

        // Build item list for this marketplace based on its tech level.
        for (TradeGoods goods : TradeGoods.values()) {
            // Check minimum tech level to produce certain goods
            if (techLevel.ordinal() >= goods.getMTLP()) {
                Item item = new Item.ItemBuilder(goods)
                        .quantity(calculateQuantity(goods))
                        .price(calculatePrice(goods))
                        .build();
                tradeGoodsInMarketMap.put(goods, item);
            }
        }
    }

    /**
     * Calculate the price of a good based on all the factors.
     *
     * @param good the good
     * @return the price of the good
     */
    private int calculatePrice(Good good) {
        int basePrice = good.getBasePrice();
        int dynamicPrice = good.getIPL()
                * (techLevel.ordinal() - good.getMTLP());
        int variancePrice = (basePrice * rand.nextInt(good.getVar())) / 10;
        int finalPrice = basePrice + dynamicPrice + variancePrice;

        if (resource == good.getCR()) {
            finalPrice = finalPrice / 3 * 2;
        }

        if (resource == good.getER()) {
            finalPrice = finalPrice * 3 / 2;
        }

        if (event == good.getIE()) {
            finalPrice = finalPrice * 3;
        }

        return finalPrice;
    }

    /**
     * Calculate the quantity of the good available on a planet
     * based on the factors.
     *
     * @param good the good
     * @return the quantity
     */
    private int calculateQuantity(Good good) {
        int maxQuantity = START_MAX_QUANTITY;
        int minQuantity = START_MIN_QUANTITY;

        if (resource == good.getCR()) {
            minQuantity = CR_MIN_QUANTITY;
        }

        if (resource == good.getER()) {
            maxQuantity = ER_MAX_QUANTITY;
        }

        if (event == good.getIE()) {
            minQuantity = minQuantity / 2;
            maxQuantity = maxQuantity / 2;
        }

        return rand.nextInt(maxQuantity - minQuantity) + minQuantity;
    }

    // Validate transaction.
    public TransactionResult validateTransaction(TransactionOrder to) {
        MarketTransactionValidator validator
                = new MarketTransactionValidator(this);
        return validator.validateNTransaction(to);
    }

    /**
     * Getter for getting tech level of the planet which the
     * marketplace is located.
     *
     * @return techLevel of the planet which the marketplace is located
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    public Events getEvent() {
        return event;
    }

    public String getEventString() {
        return event.toString();
    }

    /**
     * Getter for whole index of goods that this marketplace has.
     *
     * @return enumMap containing index of goods that this marketplace has.
     */
    public AbstractMap<TradeGoods, Item> getTradeGoodsInMarket() {
        return tradeGoodsInMarketMap;
    }

    /**
     * @return the items available in this market
     */
    public Collection<Item> getItems() {
        return tradeGoodsInMarketMap.values();
    }

    /**
     * Returns the item the good is associated with.
     *
     * @param good the good
     * @return the item associated with the good
     */
    public Item getItem(Good good) {
        return tradeGoodsInMarketMap.get(good);
    }

    //getMarketPrice and getMarketQuantity could probably be static...

    /**
     * Returns the market price of a good.
     *
     * @param good the good
     * @return the market price
     */
    public int getMarketPrice(Good good) {
        return tradeGoodsInMarketMap.get(good).getPrice();
    }

    /**
     * Returns the market quantity of a good.
     *
     * @param good the good
     * @return the market quantity
     */
    public int getMarketQuantity(Good good) {
        return tradeGoodsInMarketMap.get(good).getPrice();
    }

    @Override
    public String toString() {
        String str = "\n[" + planetName + "]";
        str += "\n/////////////////// Goods for buying /////////////////////\n";
        for (Item item: this.getItems()) {
            str += String.format("Name: %-7s\n", item.getGoodName());
            str += String.format("| price: %-5d", item.getPrice());
            str += String.format("| quantity: %-3d", item.getQuantity());
            str += "\n";
        }
        return str;
    }
}