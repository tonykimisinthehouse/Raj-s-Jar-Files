package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

public interface TransactionValidator {
    boolean validate(TransactionOrder to);
}
