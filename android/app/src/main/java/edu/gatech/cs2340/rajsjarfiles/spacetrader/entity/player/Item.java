package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

public class Item {
    private String name;
    private String description;
    private int quantity;
    private Boolean illegal;

    public Item(String name, String description, Boolean illegal) {
        this.name = name;
        this.description = description;
        this.quantity = 0;
        this.illegal = illegal;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Boolean isIllegal() {
        return this.illegal;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}
