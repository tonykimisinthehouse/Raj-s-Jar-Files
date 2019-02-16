package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the Ship class.
 */
public class ShipTester {
    private static final int TIMEOUT = 200;

    /**
     * Tests default constructor.
     */
    @Test(timeout = TIMEOUT)
    public void shipDefaultConstructor() {
        Ship ship = new Ship();
        assertEquals(ShipType.GNAT, ship.getShipType());
    }

    /**
     * Tests one arg constructor.
     */
    @Test(timeout = TIMEOUT)
    public void shipConstructor() {
        Ship ship = new Ship(ShipType.BUMBLEBEE);
        assertEquals(ShipType.BUMBLEBEE, ship.getShipType());

        ship.setShipType(ShipType.FLEA);
        assertEquals(ShipType.FLEA, ship.getShipType());
    }

    /**
     * Tests ship's overriden equals method.
     */
    @Test(timeout = TIMEOUT)
    public void shipEquals() {
        Ship ship = new Ship();
        assertEquals(ship, ship);

        Ship ship2 = new Ship(ShipType.FLEA);
        assertNotEquals(ship, ship2);

        Object notShip = new Object();
        assertNotEquals(ship, notShip);

        Exception e = new Exception();
        assertNotEquals(ship, e);
    }
}
