package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

public enum Events {
    DROUGHT,
    COLD,
    CROP_FAIL,
    WAR,
    BOREDOM,
    PLAGUE,
    LACK_OF_WORKERS;

    public static Events getRandomEvent() {
        Random rand = new Random();
        Events[] re = Events.values();
        return re[rand.nextInt(re.length)];
    }
}