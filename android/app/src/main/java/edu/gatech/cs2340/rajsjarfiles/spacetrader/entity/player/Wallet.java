package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionOrder;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionResult;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionType;

/**
 * Represents a player's wallet.
 */
public class Wallet {

    private int credits;
    private Player owner;

    ///////////////////////////// CONSTRUCTOR /////////////////////////////
    Wallet(int credits) {
        this.credits = credits;
    }

    ///////////////////////////// BASE OPERATION /////////////////////////////
    /**
     * Method that allows player to buy certain good.
     *
     * @param good good to buy
     * @param quantity quantity of good to buy
     * @return true if the transaction was successful.
     */
    public boolean makePurchase(Good good, int quantity) {
        // Create new Transaction Order
        TransactionOrder newTransactionOrder = new TransactionOrder(
                good,
                quantity,
                owner,
                TransactionType.BUY);

        // Get Transaction order
        TransactionResult newTransactionResult = owner.getLocation().getPlanet().getMarketplace()
                .validateTransaction(newTransactionOrder);

        // Use credit, Get Good to cargo based on the transaction
        // result (success, fail)
        if (newTransactionResult.getIsTransactionSuccess()) {
            Item item = newTransactionResult.getItem();
            // Add good to the cargo
            owner.getShip().addGood(item);
            // Use credits
            useCredits(item.getPrice() * item.getQuantity());
        }

        // Return if the transaction is success or not.
        return newTransactionResult.getIsTransactionSuccess();
    }

    /**
     *  Method that allows player to sell certain good.
     *
     * @param good good to sell
     * @param quantity quantity of good to sell
     * @return true if the transaction was successful
     */
    public boolean makeSales(Good good, int quantity) {
        // Create new transaction.
        TransactionOrder newTransactionOrder = new TransactionOrder(
                good,
                quantity,
                owner,
                TransactionType.SELL);

        // Get transaction result.
        TransactionResult newTransactionResult = owner.getLocation().getPlanet().getMarketplace()
                .validateTransaction(newTransactionOrder);

        // Remove cargo, and earn credit based on the transaction result
        if (newTransactionResult.getIsTransactionSuccess()) {
            Item item = newTransactionResult.getItem();
            // Sell goods from cargo.
            owner.getShip().sellGood(item);
            // Earn credits.
            earnCredits(item.getPrice() * item.getQuantity());
        }
        return newTransactionResult.getIsTransactionSuccess();
    }

    ///////////////////////////// OWNER OPERATION /////////////////////////////

    /**
     * Sets the wallet's owner to the respective player.
     *
     * @param owner the player
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * @return the wallet's owner
     */
    public Player getOwner() {
        return owner;
    }

    ///////////////////////////// CREDIT OPERATION /////////////////////////////
    /**
     * @return the player's credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets the player's credits.
     *
     * @param credits the new credits
     * @throws java.lang.IllegalArgumentException if credits are negative
     */
    public void setCredits(int credits) {
        if (credits < 0) {
            throw new IllegalArgumentException(
                    "Cannot set credits to a negative number.");
        }
        this.credits = credits;
    }

    /**
     * Subtracts a certain ratio from the player's credits.
     *
     * @param takenRatio the ratio to remove (0 to 1)
     * @return the player's remaining credits
     */
    public int setCredits(float takenRatio) {
        if (takenRatio > 1) {
            throw new IllegalArgumentException("Ratio cannot be larger than one.");
        }

        credits -= (int) (credits * takenRatio);
        return credits;
    }

    /**
     * Earn credit
     *
     * @param amount of credit earned.
     */
    private void earnCredits(int amount) {
        credits += amount;
    }

    /**
     * Use credit
     *
     * @param amount of credit earned.
     */
    public void useCredits(int amount) {
        credits -= amount;
    }

    /**
     * Makes sure that the user cannot buy more goods than you have money
     *
     * @param marketPrice the price the market is selling at
     * @return boolean of whether the user has enough credit
     */
    public Boolean checkCreditEnough(int marketPrice) {
        return marketPrice <= credits;
    }
}
