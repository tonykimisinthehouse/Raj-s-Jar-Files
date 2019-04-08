package edu.gatech.cs2340.rajsjarfiles.spacetrader;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Marketplace;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.TradeGoods;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.ShipType;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

import static org.junit.Assert.*;


/*
 For M-10 Junit by Son
 */
public class M10MarketTransaction {
    private static final int TIMEOUT = 1000;

    Model model = new Model("son", new int[]{4,4,4,4}, GameDifficulty.BEGINNER);
    Player player = model.getPlayer();
    Marketplace market = player.getLocation().getPlanet().getMarketplace();

    @Test(timeout = TIMEOUT)
    public void playerEnoughCredits() {

        int credit = 100000000;
        int cargosize = player.getShip().getCargoCapacity();
        int marketnum = market.getMarketQuantity(TradeGoods.WATER);

        player.getWallet().setCredits(credit);
        assertTrue(player.getWallet().getCredits() == credit);
        assertTrue(player.getShip().getAvailableCargoCapacity() >= 5);
        assertTrue(market.getMarketQuantity(TradeGoods.WATER)
        >= 5);
        assertTrue(player.getWallet().makePurchase(TradeGoods.WATER, 5));
        assertTrue(player.getWallet().getCredits() == credit - market.getMarketPrice(TradeGoods.WATER) * 5);
        assertTrue(player.getShip().getAvailableCargoCapacity() == cargosize - 5);
        assertTrue(market.getMarketQuantity(TradeGoods.WATER) == marketnum - 5);
    }

    @Test(timeout = TIMEOUT)
    public void playerNotEnoughCredits() {

        player.getWallet().setCredits(0);

        assertTrue(player.getWallet().getCredits() == 0);
        assertTrue(player.getShip().getAvailableCargoCapacity() >= 5);
        assertTrue(market.getMarketQuantity(TradeGoods.WATER)
                >= 5);
        assertFalse(player.getWallet().makePurchase(TradeGoods.WATER, 5));
    }

    @Test(timeout = TIMEOUT)
    public void playerNotEnoughCargo() {

        player.getWallet().setCredits(100000000);

        model.getPlayer().setShip(new Ship(0, ShipType.BEETLE));
        assertTrue(player.getWallet().getCredits() == 100000000);
        assertTrue(player.getShip().getAvailableCargoCapacity() == 0);
        assertTrue(market.getMarketQuantity(TradeGoods.WATER)
                >= 5);
        assertFalse(player.getWallet().makePurchase(TradeGoods.WATER, 5));
    }

    @Test(timeout = TIMEOUT)
    public void marketNotEnoughGoods() {

        player.getWallet().setCredits(100000000);

        assertTrue(player.getWallet().getCredits() == 100000000);
        assertTrue(player.getShip().getAvailableCargoCapacity() >= 5);
        assertTrue(market.getMarketQuantity(TradeGoods.WATER)
                <= 999999);
        assertFalse(player.getWallet().makePurchase(TradeGoods.WATER, 999999));
    }
}
