package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

/**
 * Represents a ship that a character or enemy can use.
 */
public class Ship {
    private ShipType shipType;

    /**
     * Default constructor that sets ShipType to GNAT.
     */
    public Ship() {
        this(ShipType.GNAT);
    }

    /**
     * One arg constructor for creating a ship.
     *
     * @param shipType the ship type
     */
    public Ship(ShipType shipType) {
        this.shipType = shipType;
    }

    /**
     * @return the ship type
     */
    public ShipType getShipType() {
        return shipType;
    }

    /**
     * Sets the ship type to a new ship type.
     *
     * @param shipType the new ship type
     */
    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof Ship)) {
            return false;
        }

        Ship s = (Ship) that;

        return this.shipType == s.shipType;
    }

    @Override
    public String toString() {
        return shipType.toString();
    }
}
