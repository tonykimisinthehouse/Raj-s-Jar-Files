package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.planet.planetCondition;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.planet.radicalPriceEvents;

/**
 * Represents all the trade goods in our Space Trader Game.
 */
public enum TradeGoods {
    WATER ("Water", 0, 0, 2, 30, 3, 4,
            radicalPriceEvents.DROUGHT, planetCondition.LOTSOFWATER, planetCondition.DESERT,
            30, 50),
    FURS ("Furs", 0, 0, 0, 250, 10, 10,
            radicalPriceEvents.COLD, planetCondition.RICHFAUNA, planetCondition.LIFELESS,
            230, 280),
    FOOD ("Food", 1, 0, 1, 100, 5, 5,
            radicalPriceEvents.CROPFAIL, planetCondition.RICHSOIL, planetCondition.POORSOIL,
            90, 160),
    ORE ("Ore", 2, 2, 3, 350, 20, 10,
            radicalPriceEvents.WAR, planetCondition.MINERALRICH, planetCondition.MINERALPOOR,
            350, 420),
    GAMES ("Games", 3, 1, 6, 250, -10, 5,
            radicalPriceEvents.BOREDOM, planetCondition.ARTISTIC, null,
            160, 270),
    FIREARMS ("Firearms", 3, 1, 5, 1250, -75, 100,
            radicalPriceEvents.WAR, planetCondition.WARLIKE, null,
            600, 1100),
    MEDICINE ("Medicine", 4, 1, 6, 650, -20, 10,
            radicalPriceEvents.PLAGUE, planetCondition.LOTSOFHERBS, null,
            400, 700),
    MACHINES ("Machines", 4, 3, 5, 900, -30, 5,
            radicalPriceEvents.LACKOFWORKERS, null, null,
            400, 700),
    NARCOTICS ("Narcotics", 5, 0, 5, 3500, -125, 150,
            radicalPriceEvents.BOREDOM, planetCondition.WEIRDMUSHROOMS, null,
            2000, 3000),
    ROBOTS ("Robots", 6, 5, 7, 5000, -150, 100,
            radicalPriceEvents.LACKOFWORKERS, null, null,
            3000, 5000);

    /** the full string representation of the trade good name */
    private final String name;

    /** Int representation of Minimum Tech Level to Produce this resource (You can't buy on planets
     * below this level) */
    private final int MTLP;

    /** Int representation of Minimum Tech Level to Use this resource (You can't sell on planets
     * below this level) */
    private final int MTLU;

    /** Int representation of Tech Level which produces the most of this item */
    private final int TTP;

    /** Int representation of Base Price */
    private final int BasePrice;

    /** Int representation of Price increase per tech level*/
    private final int IPL;

    /** Int representation of variance, which is the maximum percentage that the price can vary
     * above or below the base */
    private final int Var;

    /** Int representation of Radical price increase event, when this even happens on a planet,
     * the price may increase astronomically */
    private final radicalPriceEvents IE;

    /**  When this condition is present, the price of this resource is unusually low */
    private final planetCondition CR;

    /** When this condition is present, the resource is expensive */
    private final planetCondition ER;

    /** Int representation of Min price offered in space trade with random trader
     * (not on a planet)*/
    private final int MTL;

    /** Int representation of Max price offered in space trade with random trader
     * (not on a planet)*/
    private final int MTH;


    /**
     * Constructor for TradeGoods
     * @param name name of the trade good
     * @param MTLP Minimum Tech Level to Produce this resource
     * @param MTLU Minimum Tech Level to Use this resource
     * @param TTP Tech Level which produces the most of this item
     * @param BasePrice Base Price of the good
     * @param IPL Price increase per tech level
     * @param Var Variance is the maximum percentage that the price can vary above or below the base
     * @param IE Radical price increase event, when this even happens on a planet, the price may
     *           increase astronomically
     * @param CR When this condition is present, the price of this resource is unusually low
     * @param ER When this condition is present, the resource is expensive
     * @param MTL Min price offered in space trade with random trader (not on a planet)
     * @param MTH Max price offered in space trade with random trader (not on a planet)
     */
    TradeGoods(String name,
               int MTLP,
               int MTLU,
               int TTP,
               int BasePrice,
               int IPL,
               int Var,
               radicalPriceEvents IE,
               planetCondition CR,
               planetCondition ER,
               int MTL,
               int MTH) {
        this.name = name;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.BasePrice = BasePrice;
        this.IPL = IPL;
        this.Var = Var;
        this.IE = IE;
        this.CR = CR;
        this.ER = ER;
        this.MTL = MTL;
        this.MTH = MTH;
    }

    public String getName() {
        return name;
    }

    public int getMTLP() {
        return MTLP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public int getTTP() {
        return TTP;
    }

    public int getBasePrice() { return BasePrice; }

    public int getIPL() {
        return IPL;
    }

    public int getVar() {
        return Var;
    }

    public radicalPriceEvents getIE() {
        return IE;
    }

    public planetCondition getCR() {
        return CR;
    }

    public planetCondition getER() {
        return ER;
    }

    public int getMTL() {
        return MTL;
    }

    public int getMTH() {
        return MTH;
    }

    @Override
    public String toString() {
        return name;
    }
}