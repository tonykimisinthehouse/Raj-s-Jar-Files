package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Event;

public enum Item {
    WATER();

    private int minimumTechLevelToProduce;
    private int minimumTechLevelToUse;
    private int techLevelProduceMax;

    private int priceIncreasePerTechLevel;
    private int priceVariancePercentage;
    private int priceBase;

    private int minPriceTradedByTrader;
    private int maxPriceTradedByTrader;

    private Event[] EventRadicalPriceIncrase;
    private Event[] EventLowPrice;
    private Event[] EventHighPrice;

}
