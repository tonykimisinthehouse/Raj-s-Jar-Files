package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;

public class TransactionResult {

    private boolean isTransactionSuccess;
    private Item item;

    TransactionResult(boolean isTranS, Item item) {
        this.isTransactionSuccess = isTranS;
        this.item = item;
    }

    public boolean getisTransactionSuccess() {
        return this.isTransactionSuccess;
    }

    public Item getItem() {
        return this.getItem();
    }
}
