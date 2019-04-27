package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import org.junit.Test;

import java.util.List;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.ShipType;

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
     * Tests ship's overridden equals method.
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

    /**
     * Test adding weapons to a ship that has weapon slots.
     */
    @Test(timeout = TIMEOUT)
    public void testAddWeapons() {
        Ship ship = new Ship();
        assertEquals(ShipType.GNAT, ship.getShipType());

        assertEquals(0, ship.getWeapons().size());

        ship.addWeapon(Weapon.BEAM_LASER);
        assertEquals(1, ship.getWeapons().size());

        ship.addWeapon(Weapon.MILITARY_LASER);
        assertEquals(1, ship.getWeapons().size());

        Ship ship1 = new Ship(ShipType.WASP);
        assertEquals(ShipType.WASP, ship1.getShipType());

        ship1.addWeapon(Weapon.PULSE_LASER);
        assertEquals(1, ship1.getWeapons().size());
        ship1.addWeapon(Weapon.BEAM_LASER);
        assertEquals(2, ship1.getWeapons().size());
        ship1.addWeapon(Weapon.MILITARY_LASER);
        assertEquals(3, ship1.getWeapons().size());
        ship1.addWeapon(Weapon.PULSE_LASER);
        assertEquals(3, ship1.getWeapons().size());
    }

    @Test(timeout = TIMEOUT)
    public void testGetRandomShipWithWeapons() {
        for (int j = 0; j < 200; j++) {
            Ship ship = Ship.getRandomShipWithWeapons();

            assertTrue(!ship.getWeapons().isEmpty());
            assertTrue("Enemy ships should not start with 0 health.",
                    ship.getHealth() > 0);
            assertTrue("Enemy ships should not have more health than max.",
                    ship.getHealth() <= ship.getShipType().getMaxHealth());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testGetShipsWithWeapons() {
        List<ShipType> ships = ShipType.getShipsWithWeapons();
        for (ShipType s : ships) {
            assertTrue(s.getMaxWeaponSlots() > 0);
        }
    }
}
