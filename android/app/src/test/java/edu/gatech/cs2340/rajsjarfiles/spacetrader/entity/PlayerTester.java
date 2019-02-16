package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTester {
    private static final int TIMEOUT = 200;

    /**
     * Creates a valid player with all parameters.
     */
    @Test(timeout = TIMEOUT)
    public void playerBuilderTest() {
        Player player = new PlayerBuilder("Justin").setPoints(new int[]{2, 2, 4, 8})
                .setCredits(1200).setShip(new Ship(ShipType.BEETLE)).build();
        assertEquals("Justin", player.getName());
        assertArrayEquals(new int[] {2, 2, 4, 8}, player.getPoints());
        assertEquals(2, player.getPilot());
        assertEquals(2, player.getEngineer());
        assertEquals(4, player.getTrade());
        assertEquals(8, player.getFight());
        assertEquals(1200, player.getCredits());
        assertEquals(new Ship(ShipType.BEETLE), player.getShip());
    }

    /**
     * Creates a player with no args.
     */
    @Test(timeout = TIMEOUT)
    public void playerBuilderTestNoArgs() {
        Player player = new PlayerBuilder("Justin").build();
        assertEquals("Justin", player.getName());
        assertArrayEquals(new int[] {4, 4, 4, 4}, player.getPoints());
        assertEquals(4, player.getPilot());
        assertEquals(4, player.getEngineer());
        assertEquals(4, player.getTrade());
        assertEquals(4, player.getFight());
        assertEquals(1000, player.getCredits());
        assertEquals(new Ship(ShipType.GNAT), player.getShip());
    }

    /**
     * Set's player name.
     */
    @Test(timeout = TIMEOUT)
    public void playerTestSetName() {
        Player player = new PlayerBuilder("Justin").build();
        player.setName("Raj");
        assertEquals("Raj", player.getName());
    }

    /**
     * Ensures exception is thrown when points sum to less than 16.
     */
    @Test(timeout = TIMEOUT, expected = java.lang.IllegalArgumentException.class)
    public void playerBuilderIllegalPointsSumLess() {
        Player player = new PlayerBuilder("Thomas").setPoints(new int[] {1, 2, 3, 4}).build();
    }

    /**
     * Ensures exception is thrown when points sum to more than 16.
     */
    @Test(timeout = TIMEOUT, expected = java.lang.IllegalArgumentException.class)
    public void playerBuilderIllegalPointsSumMore() {
        Player player = new PlayerBuilder("Thomas").setPoints(new int[] {1, 2, 20, 4}).build();
    }

    /**
     * Ensures exception is thrown when a point is negative.
     */
    @Test(timeout = TIMEOUT, expected = java.lang.IllegalArgumentException.class)
    public void playerBuilderIllegalPointsNegative() {
        Player player = new PlayerBuilder("Thomas").setPoints(new int[] {2, -2, 7, 1}).build();
    }

    /**
     * Ensures exception is thrown player's ship is set to null with setShip().
     */
    @Test(timeout = TIMEOUT, expected = java.lang.IllegalArgumentException.class)
    public void playerSetNullShipWrong() {
        Player player = new PlayerBuilder("Thomas").setPoints(new int[] {2, 2, 4, 8}).build();
        player.setShip(null);
    }

    /**
     * Sets player's ship to a new ship and to null when player has no ship.
     */
    @Test(timeout = TIMEOUT)
    public void playerSetNullShipCorrect() {
        Player player = new PlayerBuilder("Thomas").setPoints(new int[] {2, 2, 4, 8}).build();
        player.setNoShip();

        Player player1 = new PlayerBuilder("Justin").build();
        player1.setShip(new Ship(ShipType.FIREFLY));

        assertEquals(new Ship(ShipType.FIREFLY), player1.getShip());
    }

    /**
     * Sets player's credits to new value.
     */
    @Test(timeout = TIMEOUT)
    public void playerSetCredits() {
        Player player = new PlayerBuilder("Thomas").setPoints(new int[] {2, 2, 4, 8}).build();
        player.setCredits(1222);
        assertEquals(1222, player.getCredits());
    }

    /**
     * Ensures exception is thrown when setting player's credits to a negative number.
     */
    @Test(timeout = TIMEOUT, expected = java.lang.IllegalArgumentException.class)
    public void playerSetCreditsNegative() {
        Player player = new PlayerBuilder("Thomas").setPoints(new int[] {2, 2, 4, 8}).build();
        player.setCredits(-2);
    }
}
