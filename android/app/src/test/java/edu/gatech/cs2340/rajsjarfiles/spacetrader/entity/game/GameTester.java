package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Universe;

public class GameTester {
    public static final int TIMEOUT = 200;

    @Test
    public void testGameToString() {
        Game game = new Game(GameDifficulty.BEGINNER);
        System.out.println(game);
    }
}
