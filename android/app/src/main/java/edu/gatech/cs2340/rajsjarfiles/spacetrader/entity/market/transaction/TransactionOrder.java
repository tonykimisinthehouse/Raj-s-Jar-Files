package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;

public class TransactionOrder {

    private Item item;
    private Player initiator;
    private TransactionType transactionType;

    public TransactionOrder(Good good, int quantity, Player initiator, TransactionType transactionType) {
        this.item = new Item.ItemBuilder(good)
                .quantity(quantity)
                .build();
        this.initiator = initiator;
        this.transactionType = transactionType;
    }
    public Item getItem() {
        return item;
    }
    public Player getInitiator() {
        return initiator;
    }
    public int getInitiaorCredits() {
        return initiator.getCredits();
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
}
