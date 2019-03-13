package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import org.junit.Test;

import static org.junit.Assert.*;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.ShipType;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.TechLevel;


/**
 * Tester for testing transaction
 */
public class TransactionTester {
    private static final int TIMEOUT = 1000;

    @Test(timeout = TIMEOUT)
    public void transactionPurchaseTest() {

        TechLevel tl = TechLevel.HI_TECH;

        Planet planet = new Planet.PlanetBuilder("Bob planet", 1)
                .techLevel(tl)
                .build();

        Player player = new Player.PlayerBuilder("Bob")
                .planet(planet)
                .credits(0)
                .ship(new Ship(ShipType.BEETLE))
                .build();

        assertEquals(planet.getMarketplace().getTechLevel(), tl);
        assertNotNull(planet.getMarketplace());
        // No credit
        assertFalse(player.makePurchase(TradeGoods.FOOD, 1));
        assertFalse(player.getShip().hasGoods(TradeGoods.FOOD,1));
    }

    @Test(timeout = TIMEOUT)
    public void transactionPurchaseTest2() {

        TechLevel tl = TechLevel.HI_TECH;

        Planet planet = new Planet.PlanetBuilder("Bob planet",1)
                .techLevel(tl)
                .build();

        Player player = new Player.PlayerBuilder("Bob")
                .planet(planet)
                .credits(1000)
                .ship(new Ship(ShipType.BEETLE))
                .build();

        assertEquals(planet.getMarketplace().getTechLevel(), tl);
        assertNotNull(planet.getMarketplace());

        // Must be true
        assertTrue(player.makePurchase(TradeGoods.FOOD, 1));
        assertTrue(player.getShip().hasGoods(TradeGoods.FOOD,1));
    }

    @Test(timeout = TIMEOUT)
    public void transactionPurchaseTest3() {

        TechLevel tl = TechLevel.HI_TECH;

        Planet planet = new Planet.PlanetBuilder("Bob planet",1)
                .techLevel(tl)
                .build();

        Player player = new Player.PlayerBuilder("Bob")
                .planet(planet)
                .credits(1000)
                .ship(new Ship(ShipType.BEETLE))
                .build();

        assertEquals(planet.getMarketplace().getTechLevel(), tl);
        assertNotNull(planet.getMarketplace());

        // False since it go overs the cargo capacity
        assertFalse(player.makePurchase(TradeGoods.FOOD, 1000));
        assertFalse(player.getShip().hasGoods(TradeGoods.FOOD,1000));
    }


    @Test(timeout = TIMEOUT)
    public void transactionPurchaseTest4() {

        TechLevel tl = TechLevel.PRE_AGRICULTURE;

        Planet planet = new Planet.PlanetBuilder("Bob planet",1)
                .techLevel(tl)
                .build();

        Player player = new Player.PlayerBuilder("Bob")
                .planet(planet)
                .credits(1000)
                .ship(new Ship(ShipType.BEETLE))
                .build();

        // False since the market does not have the items (minimum tech level)
        assertFalse(player.makePurchase(TradeGoods.ROBOTS, 1));
        assertFalse(player.getShip().hasGoods(TradeGoods.ROBOTS,1));
    }

    @Test(timeout = TIMEOUT)
    public void transactionSalesTest() {

        TechLevel tl = TechLevel.HI_TECH;

        Planet planet = new Planet.PlanetBuilder("Bob planet",1)
                .techLevel(tl)
                .build();

        Player player = new Player.PlayerBuilder("Bob")
                .planet(planet)
                .credits(1000)
                .ship(new Ship(ShipType.BEETLE))
                .build();

        player.getShip().addGood(TradeGoods.ROBOTS, 10);
        assertTrue(player.getShip().hasGoods(TradeGoods.ROBOTS,10));

        // False since the player does not have much goods.
        assertFalse(player.makeSales(TradeGoods.ROBOTS, 11));
        assertTrue(player.getShip().hasGoods(TradeGoods.ROBOTS,10));
    }

    @Test(timeout = TIMEOUT)
    public void transactionSalesTest2() {

        TechLevel tl = TechLevel.HI_TECH;

        Planet planet = new Planet.PlanetBuilder("Bob planet",1)
                .techLevel(tl)
                .build();

        Player player = new Player.PlayerBuilder("Bob")
                .planet(planet)
                .credits(1000)
                .ship(new Ship(ShipType.BEETLE))
                .build();

        player.getShip().addGood(TradeGoods.ROBOTS, 10);
        assertTrue(player.getShip().hasGoods(TradeGoods.ROBOTS,10));

        // False since the player does not have that goods.
        assertFalse(player.makeSales(TradeGoods.FOOD, 11));
        assertTrue(player.getShip().hasGoods(TradeGoods.ROBOTS,10));
    }

    @Test(timeout = TIMEOUT)
    public void transactionSalesTest3() {

        TechLevel tl = TechLevel.HI_TECH;

        Planet planet = new Planet.PlanetBuilder("Bob planet",1)
                .techLevel(tl)
                .build();

        Player player = new Player.PlayerBuilder("Bob")
                .planet(planet)
                .credits(1000)
                .ship(new Ship(ShipType.BEETLE))
                .build();

        player.getShip().addGood(TradeGoods.ROBOTS, 10);
        assertTrue(player.getShip().hasGoods(TradeGoods.ROBOTS,10));

        // True since the player has goods.
        assertTrue(player.makeSales(TradeGoods.ROBOTS, 10));
        assertFalse(player.getShip().hasGoods(TradeGoods.ROBOTS,10));

    }

    @Test(timeout = TIMEOUT)
    public void transactionSalesTest4() {

        TechLevel tl = TechLevel.HI_TECH;

        Planet planet = new Planet.PlanetBuilder("Bob planet",1)
                .techLevel(tl)
                .build();

        Player player = new Player.PlayerBuilder("Bob")
                .planet(planet)
                .credits(1000)
                .ship(new Ship(ShipType.BEETLE))
                .build();

        assertFalse(player.getShip().hasGoods(TradeGoods.ROBOTS,10));

        // False since the player does not have goods.
        assertFalse(player.makeSales(TradeGoods.ROBOTS, 10));
        assertFalse(player.getShip().hasGoods(TradeGoods.ROBOTS,10));
    }

}
