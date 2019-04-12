package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;

/**
 * Represent the transaction order
 */
public class TransactionOrder {


    private final Item item; // Good + quantity to be transacted.
    private final Player initiator; // The initiator of this transaction.
    private final TransactionType transactionType; // The type of the transaction (buy or sell)

    /**
     * Constructor for the transaction order
     *
     * @param good good to transact
     * @param quantity quantity of good to transact
     * @param initiator initiator of this transaction
     * @param transactionType the type of this transaction
     */
    public TransactionOrder(
            Good good, int quantity, Player initiator, TransactionType transactionType) {

        // Combine good and its quantity
        this.item = new Item.ItemBuilder(good)
                .quantity(quantity)
                .build();
        this.initiator = initiator;
        this.transactionType = transactionType;
    }

    /**
     * Get Item of this transaction
     *
     * @return item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Get initiator of this transaction
     *
     * @return the initiator
     */
    public Player getInitiator() {
        return initiator;
    }

    /**
     * Get Transaction type of this transaction
     *
     * @return the type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }
}
