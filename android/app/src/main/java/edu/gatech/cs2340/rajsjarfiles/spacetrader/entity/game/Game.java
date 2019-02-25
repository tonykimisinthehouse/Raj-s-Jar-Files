package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Universe;

/**
 * Represents a model of the game.
 */
public class Game {
    private GameDifficulty gameDiff;

    private Universe universe;

    /**
     * One arg constructor for creating a game.
     *
     * @param gameDiff the game difficulty
     */
    public Game(GameDifficulty gameDiff) {
        this.gameDiff = gameDiff;

        universe = new Universe();
    }

    /**
     * @return the game difficulty
     */
    public GameDifficulty getGameDiff() {
        return gameDiff;
    }

    /**
     * @return the universe
     */
    public Universe getUniverse() {
        return universe;
    }

    @Override
    public String toString() {
        return "The game difficulty is set to " + getGameDiff().toString()
                + ".\n" + universe.toString();
    }
}
