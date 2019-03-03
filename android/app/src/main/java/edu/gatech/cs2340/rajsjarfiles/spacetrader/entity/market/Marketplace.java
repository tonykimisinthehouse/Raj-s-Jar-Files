package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import java.util.EnumMap;
import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Events;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Habitats;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.ResourceClassification;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Species;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.TechLevel;


/**
 *  Representation of a Planet's Marketplace based on the planet's tech level and trade good's
 *  attributes
 */
public class Marketplace{
    private static Random rand = new Random();

    private EnumMap<TradeGoods, Item> tradeGoods2Buy;

    private String planetName;
    private TechLevel tl;
    private Habitats ha;
    private ResourceClassification rc;
    private Species sp;
    private Events ev;


    /**
     * Constructor for marketplace
     * @param pn planet name
     * @param tl tech level
     * @param ev events
     * @param ha habitats
     * @param rc resource class
     * @param sp species
     */
    public Marketplace(String pn,
            TechLevel tl, Events ev, Habitats ha, ResourceClassification rc, Species sp) {

        this.planetName = pn;
        this.tl = tl;
        this.ha = ha;
        this.rc = rc;
        this.sp = sp;
        this.ev = ev;

        tradeGoods2Buy = new EnumMap<>(TradeGoods.class);

        // Indexing total goods
        for (TradeGoods goods : TradeGoods.values()) {
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

    public EnumMap<TradeGoods, Item> getTradeGoodsEnumMap() {
        return getTradeGoodsEnumMap();
    }

    public boolean makePurchase(TradeGoods good, int quantity) {
        if (hasGoods(good, quantity)) {
            tradeGoods2Buy.get(good).buyQuantity(quantity);
            return true;
        }
        return false;
    }

    public boolean hasGoods(TradeGoods good, int quantity) {
        if (tradeGoods2Buy.containsKey(good)) {
            return tradeGoods2Buy.get(good).getQuantity() >= quantity;
        }
        return false;
    }

    public int getPriceForPurchase(TradeGoods good, int quantity) {
        if (!tradeGoods2Buy.containsKey(good)) {
            throw new java.lang.IllegalArgumentException("Good not found from the market");
        }
        return tradeGoods2Buy.get(good).getPrice() * quantity;
    }


    public int makeSales(TradeGoods good, int quantity) {
        if (!canSell(good)) {
            throw new java.lang.IllegalArgumentException("Good not found from the market");
        }
        Item item = new Item.ItemBuilder(good)
                .resourceClass(rc)
                .techLevel(tl)
                .event(ev)
                .calculatePrice()
                .calculateQuantity()
                .build();
        return item.getPrice();
    }

    public boolean canSell(TradeGoods good) {
        return tl.ordinal() >= good.getMTLU();
    }

    @Override
    public String toString() {
        String str = "\n["+planetName+"]";
        str += "\n/////////////////// Goods for buying /////////////////////\n";
        for (Item item: tradeGoods2Buy.values()) {
            str += String.format("Name: %-7s\n", item.getName());
            str += String.format("| price: %-5d\n",item.getPrice());
            str += String.format("| quantity: %-3d\n", item.getQuantity());
        }
        return str;
    }
}