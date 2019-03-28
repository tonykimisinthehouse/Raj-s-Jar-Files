package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Marketplace;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;

/**
 * Transaction Validator for transaction with marketplace.
 */
public class MarketTransactionValidator implements TransactionValidator {

    // Market place where this validator is working on.
    private Marketplace marketplace;

    /**
     * Constructor for this class
     *
     * @param mp marketplace in which this validator validates.
     */
    public MarketTransactionValidator(Marketplace mp) {
        this.marketplace = mp;
    }

    /**
     * Validate the transaction order
     *
     * @param order transaction order from player
     * @return true if the transaction can be initiated.
     */
    public boolean validate(TransactionOrder order) {

        Good goodInOrder = order.getItem().getGood();
        int quantityInOrder = order.getItem().getQuantity();
        Player playerInOrder = order.getInitiator();



        if (order.getTransactionType() == TransactionType.BUY) {

            // Check if the market place has good
            if (!hasGoods(order)) {
                return false;
            }

            int marketPrice = marketplace.getMarketPrice(goodInOrder);

            // Check if the initiator has enough credits
            if (!playerInOrder.checkCreditEnough(
                    marketPrice * quantityInOrder)) {
                return false;
            }

            // Check if the initiator has enough cargo capacity
            if (!playerInOrder.checkCargoCapacityEnough(quantityInOrder)) {
                return false;
            }

            // Set a market price to the good that the player is trying to buy
            order.getItem().setPrice(marketPrice);

            // Subract amount of quantity of good from the market
            marketplace.getItem(goodInOrder).subQuantity(quantityInOrder);

            return true;

        } else {

            // If the good can be sold in certain market
            if (!isSellPossible(order)) {
                return false;
            }

            // If the player has goods to sell
            if (!playerHasGoods(order)) {
                return false;
            }

            // Update price for selling goods
            int marketPrice = marketplace.getMarketPrice(goodInOrder);
            order.getItem().setPrice(marketPrice);

            marketplace.getItem(goodInOrder).addQuantity(quantityInOrder);

            return true;
        }
    }

    /**
     * Create transaction result
     * @param order transaction order
     * @return TransactionResult containing the transaction result.
     */
    public TransactionResult validateNTransaction(TransactionOrder order) {
        Boolean isTransSuccess = validate(order);
        TransactionResult result =
                new TransactionResult(isTransSuccess, order.getItem());
        return result;
    }

    /**
     * Check if the marketplace has specific goods
     * @param order transaction order
     * @return true if the marketplace has goods and has enough quantity.
     */
    private boolean hasGoods(TransactionOrder order) {
        Good good = order.getItem().getGood();
        if (marketplace.getTradeGoodsInMarket().containsKey(good)) {
            return marketplace.getItem(good).getQuantity()
                    >= order.getItem().getQuantity();
        }
        return false;
    }

    /**
     * Check if the good can be sold in the market
     * @param order transaction order
     * @return true if this good can be sold to the marketplace
     */
    private boolean isSellPossible(TransactionOrder order) {
        int mtlu = order.getItem().getGood().getMTLU();
        return marketplace.getTechLevel().ordinal() >= mtlu;
    }

    /**
     * Check if the player has enough goods that they
     * demand on their transaction order.
     * 
     * @param order transaction order
     * @return true if the player has enough goods that can sell.
     */
    private boolean playerHasGoods(TransactionOrder order) {
        int quantity = order.getItem().getQuantity();
        Good good = order.getItem().getGood();
        return order.getInitiator().getShip().hasGoods(good, quantity);
    }

}
