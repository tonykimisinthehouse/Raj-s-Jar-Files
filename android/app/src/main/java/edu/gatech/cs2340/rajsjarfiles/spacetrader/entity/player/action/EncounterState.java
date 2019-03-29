package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

public class EncounterState {
    private boolean isOver;

    public EncounterState() {
        isOver = false;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}
