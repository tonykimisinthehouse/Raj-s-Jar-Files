package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import java.util.Collection;
import java.util.EnumMap;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.MarketTransactionValidator;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionOrder;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionResult;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Events;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.ResourceClassification;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.TechLevel;


/**
 *  Representation of a Planet's Marketplace based on the planet's tech level and trade good's
 *  attributes
 */
public class Marketplace{

    // EnumMap that store trade goods and its quantity
    private EnumMap<TradeGoods, Item> tradeGoods2Buy;

    private String planetName;
    private TechLevel tl;
    private ResourceClassification rc;
    private Events ev;

    /**
     * Constructor for marketplace
     *
     * @param pn planet name
     * @param tl tech level
     * @param ev events
     * @param rc resource class
     */
    public Marketplace(String pn,
            TechLevel tl, Events ev, ResourceClassification rc) {

        this.planetName = pn;
        this.tl = tl;
        this.rc = rc;
        this.ev = ev;

        tradeGoods2Buy = new EnumMap<>(TradeGoods.class);

        // Build item list for this marketplace based on its tech level.
        for (TradeGoods goods : TradeGoods.values()) {
            // Check minimum tech level to produce certain goods
            if (tl.ordinal() >= goods.getMTLP()) {
                Item item = new Item.ItemBuilder(goods)
                        .resourceClass(rc)
                        .techLevel(tl)
                        .event(ev)
                        .calculatePrice()
                        .calculateQuantity()
                        .build();
                tradeGoods2Buy.put(goods, item);
            }
        }
    }

    // Validate transaction.
    public TransactionResult validateTransaction(TransactionOrder to) {
        MarketTransactionValidator tv = new MarketTransactionValidator(this);
        return tv.validateNTransaction(to);
    }

    /**
     * Getter for getting tech level of the planet which the marketplace is located.
     *
     * @return techlevel of the planet which the marketplace is located
     */
    public TechLevel getTl() {
        return tl;
    }

    /**
     * Getter for whole index of goods that this marketplace has.
     *
     * @return enumMap containing index of goods that this marketplace has.
     */
    public EnumMap<TradeGoods, Item> getTradeGoodsEnumMap() {
        return tradeGoods2Buy;
    }


    public Collection<Item> getGoods() {
        return tradeGoods2Buy.values();
    }

    @Override
    public String toString() {
        String str = "\n["+planetName+"]";
        str += "\n/////////////////// Goods for buying /////////////////////\n";
        for (Item item: this.getGoods()) {
            str += String.format("Name: %-7s\n", item.getName());
            str += String.format("| price: %-5d",item.getPrice());
            str += String.format("| quantity: %-3d", item.getQuantity());
            str += "\n";
        }
        return str;
    }
}