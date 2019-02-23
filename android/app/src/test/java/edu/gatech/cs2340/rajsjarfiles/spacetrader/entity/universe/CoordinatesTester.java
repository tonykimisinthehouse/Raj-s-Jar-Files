package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Coordinate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the Coordinates class.
 */
public class CoordinatesTester {
    private static final int TIMEOUT = 10000;
    private static final double DOUBLE_EPSILON = 0.0001;

    @Test (timeout = TIMEOUT)
    public void testTwoArgConstructor() {
        Coordinate c = new Coordinate();

        Coordinate c1 = new Coordinate(55, 152);
        assertEquals(55, c1.getX());
        assertEquals(152, c1.getY());
    }

    /**
     * Tests for hashcode collisions.
     */
    @Test (timeout = TIMEOUT)
    public void testCoordinates() {
        HashSet<Coordinate> hs = new HashSet<>();
        int collisions = 0;
        for (int i = -100; i < 100; i++) {
            for (int j = -100; j < 100; j++) {
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
        assertEquals(5, c.distTo(otherC), DOUBLE_EPSILON);

        Coordinate c1 = new Coordinate(-2, 2);
        Coordinate c2 = new Coordinate(24, -18);

        assertEquals(
                Math.sqrt(
                        Math.pow(c1.getX() - c2.getX(), 2)
                        + Math.pow(c1.getY() - c2.getY(), 2)
                ), Coordinate.distBetween(c1, c2), DOUBLE_EPSILON
        );
    }

    /**
     * Checks that the coordinate distance calculations are correct with
     * random coordinates.
     */
    @Test (timeout = TIMEOUT)
    public void testDistToRandom() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int x1 = rand.nextInt(200) - 100;
            int y1 = rand.nextInt(200) - 100;
            int x2 = rand.nextInt(200) - 100;
            int y2 = rand.nextInt(200) - 100;

            int dx = Math.abs(x1 - x2);
            int dy = Math.abs(y1 - y2);

            double expectedDist = Math.sqrt(dx * dx + dy * dy);

            Coordinate c1 = new Coordinate(x1, y1);
            Coordinate c2 = new Coordinate(x2, y2);

            assertEquals(expectedDist, c1.distTo(c2), DOUBLE_EPSILON);
            assertEquals(expectedDist, Coordinate.distBetween(c1, c2), DOUBLE_EPSILON);
        }
    }

    /**
     * Test equals method.
     */
    @Test(timeout = TIMEOUT)
    public void testEquals() {
        Coordinate c1 = new Coordinate(3, 4);
        Coordinate c2 = new Coordinate(3, 4);
        Exception e = new Exception();
        Object o = new Object();

        assertEquals(c1, c1);
        assertEquals(c1, c2);
        assertNotEquals(c1, e);
        assertNotEquals(o, c1);
    }
}
