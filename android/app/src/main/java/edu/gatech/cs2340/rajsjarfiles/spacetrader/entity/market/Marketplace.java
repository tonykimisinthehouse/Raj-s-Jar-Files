package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.TradeGoods;
import java.util.EnumMap;
import java.util.Random;


/**
 *  Representation of a Planet's Marketplace based on the planet's tech level and trade good's
 *  attributes
 */
public class Marketplace {
    private static Random rand = new Random();

    private EnumMap<TradeGoods, Integer> tradeGoodsEnumMap;

    /**
     * Constructor for MarketPlace
     * @param planetTechLevel the tech level of the planet that the market place is located in.
     */
    public Marketplace(int planetTechLevel) {

        tradeGoodsEnumMap = new EnumMap<>(TradeGoods.class);

        for (TradeGoods goods: TradeGoods.values()) {
            if (planetTechLevel >= goods.getMTLP()) {
                int variance = rand.nextInt() * (goods.getVar() + 1);
                int finalPrice = goods.getBasePrice()
                        + goods.getIPL() * (planetTechLevel - goods.getMTLP()) + variance;
                tradeGoodsEnumMap.put(goods, finalPrice);
            }
        }
    }


    public EnumMap<TradeGoods, Integer> getTradeGoodsEnumMap() {
        return getTradeGoodsEnumMap();
    }
}