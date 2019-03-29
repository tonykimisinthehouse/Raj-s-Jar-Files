package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Events;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.ResourceClassification;

import static edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.TradeGoods.FIREARMS;
import static edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.TradeGoods.NARCOTICS;

/**
 * Interface for good (tradeGoods, other goods, ship etc)
 */
public interface Good {
    String getName();
    int getMTLP();
    int getMTLU();
    int getTTP();
    int getBasePrice();
    int getIPL();
    int getVar();
    Events getIE();
    ResourceClassification getCR();
    ResourceClassification getER();
    int getMTL();
    int getMTH();
    boolean isIllegal();

    public static final Good[] ILLEGAL_GOODS
            = new Good[] {FIREARMS, NARCOTICS};
}
