package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

/**
 * Used with BattleManager.java. Tells the program whether
 * an encounter is over or not
 */
public class EncounterState {
    private boolean isOver;

    /**
     * Creates an encounter state with default
     * value of false.
     */
    public EncounterState() {
        isOver = false;
    }

    /**
     * @return whether or not the encounter is over
     */
    public boolean isOver() {
        return isOver;
    }

    /**
     * Sets the state to a new value.
     *
     * @param over the new state
     */
    public void setOver(boolean over) {
        isOver = over;
    }

    /**
     * Ends the encounter.
     */
    public void conclude() {
        isOver = true;
    }
}
