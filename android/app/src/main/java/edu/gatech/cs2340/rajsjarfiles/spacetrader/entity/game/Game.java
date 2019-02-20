package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game;

/**
 * Represents a model of the game.
 */
public class Game {
    private GameDifficulty gameDiff;

    /**
     * Two arg constructor for creating a game.
     *
     * @param gameDiff the game difficulty
     */
    public Game(GameDifficulty gameDiff) {
        this.gameDiff = gameDiff;
    }

    public GameDifficulty getGameDiff() { return gameDiff;}

    public String toString() {
        return "The game difficulty is set to " + getGameDiff().toString();
    }
}
