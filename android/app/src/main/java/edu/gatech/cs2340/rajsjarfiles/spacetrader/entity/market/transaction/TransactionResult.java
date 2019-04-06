package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;

/**
 * Represents transaction result
 */
public class TransactionResult {

    private boolean isTransactionSuccess;
    private Item item;

    /**
     * Constructor for the transaction result
     *
     * @param isTranS is this transaction successful
     * @param item item that is being transacted
     */
    TransactionResult(boolean isTranS, Item item) {
        this.isTransactionSuccess = isTranS;
        this.item = item;
    }

    /**
     * Get transaction result
     *
     * @return true if the transaction was successful.
     */
    public boolean getIsTransactionSuccess() {
        return this.isTransactionSuccess;
    }

    /**
     * Get Item that are being transacted
     *
     * @return  Item that are being transacted
     */
    public Item getItem() {
        return this.item;
    }
}
