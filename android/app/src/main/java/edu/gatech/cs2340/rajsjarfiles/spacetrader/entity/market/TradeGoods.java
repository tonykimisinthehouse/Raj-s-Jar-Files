package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.radicalPriceEvents;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.ResourceClassification;

/**
 * Represents all the trade goods in our Space Trader Game.
 */
public enum TradeGoods {
    WATER(new TradeGoodsBuilder("Water")
            .MTLP(0).MTLU(0).TTP(2).BasePrice(30).IPL(3).Var(4)
            .IE(radicalPriceEvents.DROUGHT)
            .CR(ResourceClassification.LOTS_OF_WATER)
            .ER(ResourceClassification.DESERT)
            .MTL(30)
            .MTH(50))
    ,
    FURS(new TradeGoodsBuilder("Furs")
            .MTLP(0).MTLU(0).TTP(0).BasePrice(250).IPL(10).Var(10)
            .IE(radicalPriceEvents.COLD)
            .CR(ResourceClassification.RICH_FAUNA)
            .ER(ResourceClassification.LIFELESS)
            .MTL(230).MTH(280))
    ,
    FOOD(new TradeGoodsBuilder("Food")
            .MTLP(1).MTLU(0).TTP(1).BasePrice(100).IPL(5).Var(5)
            .IE(radicalPriceEvents.CROPFAIL)
            .CR(ResourceClassification.RICH_SOIL)
            .ER(ResourceClassification.POOR_SOIL)
            .MTL(90).MTH(160))
    ,

    ORE(new TradeGoodsBuilder("Ore").
            MTLP(2).MTLU(2).TTP(3).BasePrice(350).IPL(20).Var(10)
            .IE(radicalPriceEvents.WAR)
            .CR(ResourceClassification.MINERAL_RICH)
            .ER(ResourceClassification.MINERAL_POOR)
            .MTL(350).MTH(420))
    ,

    GAMES(new TradeGoodsBuilder("Games")
            .MTLP(3).MTLU(1).TTP(6).BasePrice(250).IPL(-10).Var(5)
            .IE(radicalPriceEvents.BOREDOM)
            .CR(ResourceClassification.ARTISTIC)
            .ER(null)
            .MTL(160).MTH(270))
    ,
    FIREARMS(new TradeGoodsBuilder("Firearms")
            .MTLP(3).MTLU(1).TTP(5).BasePrice(1250).IPL(-75).Var(100)
            .IE(radicalPriceEvents.WAR)
            .CR(ResourceClassification.WARLIKE)
            .ER(null)
            .MTL(600).MTH(1100))
    ,
    MEDICINE(new TradeGoodsBuilder("Medicine")
            .MTLP(4).MTLU(1).TTP(6).BasePrice(650).IPL(-20).Var(10)
            .IE(radicalPriceEvents.PLAGUE)
            .CR(ResourceClassification.LOTS_OF_HERBS)
            .ER(null)
            .MTL(400).MTH(700))
    ,
    MACHINES (new TradeGoodsBuilder("Machines")
            .MTLP(4).MTLU(3).TTP(5).BasePrice(900).IPL(-30).Var(5)
            .IE(radicalPriceEvents.LACKOFWORKERS)
            .CR(null)
            .ER(null)
            .MTL(400).MTH(700))
    ,
    NARCOTICS (new TradeGoodsBuilder("Narcotics")
            .MTLP(5).MTLU(0).TTP(5).BasePrice(3500).IPL(-125).Var(150)
            .IE(radicalPriceEvents.BOREDOM)
            .CR(ResourceClassification.WEIRD_MUSHROOMS)
            .ER(null)
            .MTL(2000).MTH(3000))
    ,
    ROBOTS (new TradeGoodsBuilder("Robots")
            .MTLP(6).MTLU(5).TTP(7).BasePrice(5000).IPL(-150).Var(100)
            .IE(radicalPriceEvents.LACKOFWORKERS)
            .CR(null)
            .ER(null)
            .MTL(3000).MTH(5000));

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
    private final ResourceClassification CR;

    /** When this condition is present, the resource is expensive */
    private final ResourceClassification ER;

    /** Int representation of Min price offered in space trade with random trader
     * (not on a planet)*/
    private final int MTL;

    /** Int representation of Max price offered in space trade with random trader
     * (not on a planet)*/
    private final int MTH;

    TradeGoods(TradeGoodsBuilder builder) {
        this.name = builder.name;
        this.MTLP = builder.MTLP;
        this.MTLU = builder.MTLU;
        this.TTP = builder.TTP;
        this.BasePrice = builder.BasePrice;
        this.IPL = builder.IPL;
        this.Var = builder.Var;
        this.IE = builder.IE;
        this.CR = builder.CR;
        this.ER = builder.ER;
        this.MTL = builder.MTL;
        this.MTH = builder.MTH;
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

    public ResourceClassification getCR() {
        return CR;
    }

    public ResourceClassification getER() {
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

    private static class TradeGoodsBuilder{
        private String name;
        private int MTLP;
        private int MTLU;
        private int TTP;
        private int BasePrice;
        private int IPL;
        private int Var;
        private radicalPriceEvents IE;
        private ResourceClassification CR;
        private ResourceClassification ER;
        private int MTL;
        private int MTH;

        TradeGoodsBuilder(String name) {
            this.name = name;
        }

        private TradeGoodsBuilder MTLP(int MTLP) {
            this.MTLP = MTLP;
            return this;
        }
        private TradeGoodsBuilder MTLU(int MTLU) {
            this.MTLU = MTLU;
            return this;
        }
        private TradeGoodsBuilder TTP(int TTP) {
            this.TTP = TTP;
            return this;
        }
        private TradeGoodsBuilder BasePrice(int BasePrice) {
            this.BasePrice = BasePrice;
            return this;
        }
        private TradeGoodsBuilder IPL(int IPL) {
            this.IPL = IPL;
            return this;
        }
        private TradeGoodsBuilder Var(int Var) {
            this.Var = Var;
            return this;
        }
        private TradeGoodsBuilder IE(radicalPriceEvents IE) {
            this.IE = IE;
            return this;
        }
        private TradeGoodsBuilder CR(ResourceClassification CR) {
            this.CR = CR;
            return this;
        }
        private TradeGoodsBuilder ER(ResourceClassification ER) {
            this.ER = ER;
            return this;
        }
        private TradeGoodsBuilder MTL(int MTL) {
            this.MTL = MTL;
            return this;
        }
        private TradeGoodsBuilder MTH(int MTH) {
            this.MTH = MTH;
            return this;
        }
    }


}