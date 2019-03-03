package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Events;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.ResourceClassification;

public interface Good {

    public String getName();
    public int getMTLP();
    public int getMTLU();
    public int getTTP();
    public int getBasePrice();
    public int getIPL();
    public int getVar();
    public Events getIE();
    public ResourceClassification getCR();
    public ResourceClassification getER();
    public int getMTL();
    public int getMTH();
}
