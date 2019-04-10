package edu.gatech.cs2340.rajsjarfiles.spacetrader.model;

import android.util.Log;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Weapon;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.utility.LogCustom;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.Game;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;

/**
 * Creates a player and model with the valid inputs.
 */
public class Model {
    private static Model current;
    private Player player;
    private Game game;

    /**
     * Constructor for creating a player and model with the valid inputs.
     *
     * @param name The player name the user has chosen.
     * @param points The allocation of skill points the user has chosen
     * @param difficulty The Game Difficulty the user has chosen
     */
    public Model(String name, int[] points, GameDifficulty difficulty) {

        // Set singleton reference
        Model.setCurrent(this);

        game = new Game(difficulty);
        player = new Player.PlayerBuilder(name)
                .points(points)
                .build();

        //give player default weapon
        player.getShip().addWeapon(Weapon.PULSE_LASER);

        Log.i("Model", player.toString());
        LogCustom.largeLog("Model", game.toString());
    }

    /**
     * Singleton reference to the main object.
     *
     * @return the model
     */
    public static Model getCurrent() {
        return current;
    }

    /**
     * Sets the model to a new model
     *
     * @param current the new model
     */
    public static void setCurrent(Model current) {
        Model.current = current;
    }

    /**
     * @return the player object
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return the game object
     */
    public Game getGame() {
        return this.game;
    }

}
