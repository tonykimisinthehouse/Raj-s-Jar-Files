package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

/**
 * Represents interface of the transactionValidator
 */
public interface TransactionValidator {
    /**
     * Validate a transaction order.
     *
     * @param to the transaction
     * @return whether it was successful or not
     */
    boolean validate(TransactionOrder to);
}
