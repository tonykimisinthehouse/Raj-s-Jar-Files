package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

/**
 * Represents interface of the transactionValidator
 */
public interface TransactionValidator {
    boolean validate(TransactionOrder to);
}
