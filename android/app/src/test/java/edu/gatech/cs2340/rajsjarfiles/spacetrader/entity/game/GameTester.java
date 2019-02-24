package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Universe;

/**
 * Tests the Game class.
 */
public class GameTester {
    public static final int TIMEOUT = 200;

    /**
     * Outputs the game to the console.
     */
    @Test
    public void testGameToString() {
        Game game = new Game(GameDifficulty.BEGINNER);
        System.out.println(game);
    }
}
