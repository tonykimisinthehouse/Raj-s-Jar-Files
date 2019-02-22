package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

import java.util.HashSet;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Coordinate;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Coordinates class.
 */
public class CoordinatesTester {
    private static final int TIMEOUT = 10000;

    /**
     * Tests for hashcode collisions.
     */
    @Test (timeout = TIMEOUT)
    public void testCoordinates() {
        HashSet<Coordinate> hs = new HashSet<>();
        int collisions = 0;
        for (int i = -1000; i < 1000; i++) {
            for (int j = -1000; j < 1000; j++) {
                boolean added = hs.add(new Coordinate(i, j));
                collisions += added ? 0 : 1;
            }
        }
        System.out.println(collisions + " collisions with hashcode.");
    }

    /**
     * Checks that the coordinate distance calculations are correct.
     */
    @Test (timeout = TIMEOUT)
    public void testDistTo() {
        Coordinate c = new Coordinate(0, 0);
        Coordinate otherC = new Coordinate(3, 4);
        assertEquals(5, c.distTo(otherC), 0.001);

        Coordinate c1 = new Coordinate(-2, 2);
        Coordinate c2 = new Coordinate(24, -18);

        assertEquals(
                Math.sqrt(
                        Math.pow(c1.getX() - c2.getX(), 2)
                        + Math.pow(c1.getY() - c2.getY(), 2)
                ), Coordinate.distBetween(c1, c2), 0.001
        );
    }
}
