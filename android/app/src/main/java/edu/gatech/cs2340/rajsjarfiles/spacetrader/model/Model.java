package edu.gatech.cs2340.rajsjarfiles.spacetrader.model;

import android.util.Log;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Location;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Weapon;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.SolarSystem;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.utility.LogCustom;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.Game;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;

/**
 * Creates a player and model with the valid inputs.
 */
public class Model {
    private static Model current;

    private final Player player;
    private final Game game;

    /**
     * Constructor for creating a player and model with the valid inputs.
     *
     * @param name The player name the user has chosen.
     * @param points The allocation of skill points the user has chosen
     * @param difficulty The Game Difficulty the user has chosen
     */
    public Model(String name, int[] points, GameDifficulty difficulty) {

        // Set singleton reference
        Model.setModel(this);

        game = new Game(difficulty);
        player = new Player.PlayerBuilder(name, game.getRandomSolarSystem())
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
    public static Model getModel() {
        return current;
    }

    /**
     * Set the model to a new model.
     *
     * @param current the new model
     */
    private static void setModel(Model current) {
        Model.current = current;
    }

    ///////////////////////////// Game /////////////////////////////

    /**
     * @return the game object
     */
    public Game getGame() {
        return this.game;
    }

    ///////////////////////////// Universe  /////////////////////////////

    /**
     * Get list of planets inside the solar system where the player is located
     * @return array of planets
     */
    public Planet[] getPlanets() {
        Location l = player.getLocation();
        return l.getPlanets();
    }

    /**
     * Get planet where the player is located
     * @return planet
     */
    public Planet getPlanet() {
//        Player player = current.getPlayer();
//        return player.getPlanet();
        return current.getPlanet();
    }

    public String getPlanetName() {
        return player.getPlanetName();
    }

    public int getPlanetOrbitRadius() {
        return player.getPlanetOrbitRadius();
    }

    public String getResourceClassString() {
        return player.getResourceClassString();
    }

    public String getPlanetTechLevel(Planet p) {
        return p.getTechLevelString();
    }

    public String getPlanetEvent(Planet p) {
        return p.getEventString();
    }

    public String getPlanetSpecies(Planet p) {
        return p.getSpeciesString();
    }

    public String getPlanetColorHex(Planet p) {
        return p.getColorHex();
    }

    ///////////////////////////// Player /////////////////////////////

    /**
     * @return the player object
     */
    public Player getPlayer() {
        return this.player;
    }

    ///////////////////////////// Ship /////////////////////////////

    public int getFuelRemaining(Player player) {
        return player.getFuelRemaining();
    }

    public int getCargoCapacity(Player player) {
        return player.getCargoCapacity();
    }

    public int getAvailableCargoCapacity(Player player) {
        return player.getAvailableCargoCapacity();
    }
}
