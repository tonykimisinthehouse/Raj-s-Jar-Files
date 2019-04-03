package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.TechLevel;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

import static org.junit.Assert.*;

public class M10PlayerTester {
    private static final int TIMEOUT = 200;
    @Before
    public void setUp() {
        Model model = new Model("Justin",
                new int[] {4, 4, 4, 4}, GameDifficulty.BEGINNER);
    }

    /**
     * Ensures exception is thrown when points sum to less than 16.
     */
    @Test(timeout = TIMEOUT, expected = java.lang.IllegalArgumentException.class)
    public void playerBuilderIllegalPointsSumLess() {
        Player player = new Player.PlayerBuilder("Thomas")
                .points(new int[] {1, 2, 3, 4}).build();
    }

    /**
     * Ensures exception is thrown when points sum to more than 16.
     */
    @Test(timeout = TIMEOUT, expected = java.lang.IllegalArgumentException.class)
    public void playerBuilderIllegalPointsSumMore() {
        Player player = new Player.PlayerBuilder("Thomas")
                .points(new int[] {1, 2, 20, 4}).build();
    }

    /**
     * Ensures exception is thrown when a point is negative.
     */
    @Test(timeout = TIMEOUT, expected = java.lang.IllegalArgumentException.class)
    public void playerBuilderIllegalPointsNegative() {
        Player player = new Player.PlayerBuilder("Thomas")
                .points(new int[] {2, -2, 7, 1}).build();
    }
}
