package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Events;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Habitats;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.ResourceClassification;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.TechLevel;

import static org.junit.Assert.*;


/**
 * Tester for testing market
 */
public class MarketTester {
    private static final int TIMEOUT = 1000;

//    PRE_AGRICULTURE,
//    AGRICULTURE,
//    MEDIEVAL,
//    RENAISSANCE,
//    EARLY_INDUSTRIAL,
//    INDUSTRIAL,
//    POST_INDUSTRIAL,
//    HI_TECH;

    @Test(timeout = TIMEOUT)
    public void MarketCreationTest() {

        TechLevel tl = TechLevel.PRE_AGRICULTURE;
        Events event = Events.getRandomEvent();
        ResourceClassification rc = ResourceClassification.getRandomResourceClass(
                Habitats.getRandomHabitat()
        );
        Marketplace marketplace = new Marketplace("Bob", tl, event, rc);
        assertEquals(tl, marketplace.getTl());

        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.WATER));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FURS));

        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FOOD));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ORE));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.GAMES));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FIREARMS));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.MEDICINE));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.NARCOTICS));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ROBOTS));

    }

    @Test(timeout = TIMEOUT)
    public void MarketCreationTest2() {

        TechLevel tl = TechLevel.AGRICULTURE;
        Events event = Events.getRandomEvent();
        ResourceClassification rc = ResourceClassification.getRandomResourceClass(
                Habitats.getRandomHabitat()
        );
        Marketplace marketplace = new Marketplace("Bob", tl, event, rc);
        assertEquals(tl, marketplace.getTl());

        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.WATER));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FURS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FOOD));

        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ORE));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.GAMES));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FIREARMS));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.MEDICINE));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.NARCOTICS));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ROBOTS));
    }

    @Test(timeout = TIMEOUT)
    public void MarketCreationTest3() {

        TechLevel tl = TechLevel.MEDIEVAL;
        Events event = Events.getRandomEvent();
        ResourceClassification rc = ResourceClassification.getRandomResourceClass(
                Habitats.getRandomHabitat()
        );
        Marketplace marketplace = new Marketplace("Bob", tl, event, rc);
        assertEquals(tl, marketplace.getTl());

        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.WATER));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FURS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FOOD));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ORE));

        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.GAMES));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FIREARMS));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.MEDICINE));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.NARCOTICS));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ROBOTS));
    }

    @Test(timeout = TIMEOUT)
    public void MarketCreationTest4() {

        TechLevel tl = TechLevel.RENAISSANCE;
        Events event = Events.getRandomEvent();
        ResourceClassification rc = ResourceClassification.getRandomResourceClass(
                Habitats.getRandomHabitat()
        );
        Marketplace marketplace = new Marketplace("Bob", tl, event, rc);
        assertEquals(tl, marketplace.getTl());

        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.WATER));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FURS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FOOD));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ORE));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.GAMES));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FIREARMS));

        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.MEDICINE));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.NARCOTICS));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ROBOTS));
    }

    @Test(timeout = TIMEOUT)
    public void MarketCreationTest5() {

        TechLevel tl = TechLevel.EARLY_INDUSTRIAL;
        Events event = Events.getRandomEvent();
        ResourceClassification rc = ResourceClassification.getRandomResourceClass(
                Habitats.getRandomHabitat()
        );
        Marketplace marketplace = new Marketplace("Bob", tl, event, rc);
        assertEquals(tl, marketplace.getTl());

        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.WATER));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FURS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FOOD));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ORE));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.GAMES));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FIREARMS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.MEDICINE));

        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.NARCOTICS));
        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ROBOTS));
    }

    @Test(timeout = TIMEOUT)
    public void MarketCreationTest6() {

        TechLevel tl = TechLevel.INDUSTRIAL;
        Events event = Events.getRandomEvent();
        ResourceClassification rc = ResourceClassification.getRandomResourceClass(
                Habitats.getRandomHabitat()
        );
        Marketplace marketplace = new Marketplace("Bob", tl, event, rc);
        assertEquals(tl, marketplace.getTl());

        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.WATER));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FURS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FOOD));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ORE));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.GAMES));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FIREARMS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.MEDICINE));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.NARCOTICS));

        assertFalse(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ROBOTS));
    }

    @Test(timeout = TIMEOUT)
    public void MarketCreationTest7() {

        TechLevel tl = TechLevel.POST_INDUSTRIAL;
        Events event = Events.getRandomEvent();
        ResourceClassification rc = ResourceClassification.getRandomResourceClass(
                Habitats.getRandomHabitat()
        );
        Marketplace marketplace = new Marketplace("Bob", tl, event, rc);
        assertEquals(tl, marketplace.getTl());

        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.WATER));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FURS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FOOD));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ORE));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.GAMES));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.FIREARMS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.MEDICINE));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.NARCOTICS));
        assertTrue(marketplace.getTradeGoodsEnumMap().containsKey(TradeGoods.ROBOTS));
    }


}
