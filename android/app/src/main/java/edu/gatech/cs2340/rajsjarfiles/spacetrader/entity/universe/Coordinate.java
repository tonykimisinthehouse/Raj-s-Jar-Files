package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distTo(Coordinate c) {
        int dx = c.x - x;
        int dy = c.y - y;
        return Math.abs(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
    }

    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof Coordinate)) {
            return false;
        }
        Coordinate c = (Coordinate) that;
        return this.x == c.x && this.y == c.y;
    }

    @Override
    public int hashCode() {
        int h = 0;
        h = (h * 397) ^ x;
        h = (h * 397) ^ y;
        return h;
    }

    public static double distBetween(Coordinate c1, Coordinate c2) {
        int dx = c1.x - c2.x;
        int dy = c1.y - c2.y;
        return Math.abs(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
    }
}
