package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

/**
 * Represents a model of the game.
 */
public class Game {
    private GameDifficulty gameDiff;

    private Player player;

    /**
     * Two arg constructor for creating a game.
     *
     * @param gameDiff the game difficulty
     * @param player the player
     */
    public Game(GameDifficulty gameDiff, Player player) {
        this.gameDiff = gameDiff;
        this.player = player;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
}
