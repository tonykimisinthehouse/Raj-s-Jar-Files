package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Marketplace;

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
     * @param to transaction order from player
     * @return true if the transaction can be initiated.
     */
    public boolean validate(TransactionOrder to) {

        if (to.getTransactionType() == TransactionType.BUY) {

            // Check if the market place has good
            if (!hasGoods(to)) return false;

            // Add marketplace price to the good.
            to.getItem().setPrice(marketplace.getTradeGoodsEnumMap().get(to.getItem()).getPrice());

            int price = to.getItem().getPrice();
            int quantity = to.getItem().getQuantity();

            // Check if the initiator has enough credits
            if (!to.getInitiator().checkCreditEnough(price)) return false;

            // Check if the initiator has enough cargo capacity
            if (!to.getInitiator().checkCargoCapacityEnough(quantity)) return false;

            return true;

        } else{

            // If the good can be sold in certain market
            if (!isSellPossible(to)) return false;

            // If the player has goods to sell
            if (!playerHasGoods(to)) return false;

            // Update price for selling goods
            to.getItem().setPrice(marketplace.getTradeGoodsEnumMap().get(to.getItem()).getPrice());

            // Should the market now have sold good??????????????????????

            return true;
        }
    }

    /**
     * Create transaction result
     * @param to transaction order
     * @return TransactionResult containing the transaction result.
     */
    public TransactionResult validateNTransaction(TransactionOrder to) {
        Boolean isTransSuccess = validate(to);
        TransactionResult tr = new TransactionResult(isTransSuccess, to.getItem());
        return tr;
    }

    /**
     * Check if the marketplace has specific goods
     * @param to transaction order
     * @return true if the marketplace has goods and has enough quantity.
     */
    private boolean hasGoods(TransactionOrder to) {
        Good good = to.getItem().getGood();
        if (marketplace.getTradeGoodsEnumMap().containsKey(good)) {
            return marketplace.getTradeGoodsEnumMap().get(good).getQuantity()
                    >= to.getItem().getQuantity();
        }
        return false;
    }

    /**
     * Check if the good can be sold in the market
     * @param to transaction order
     * @return true if this good can be sold to the marketplace
     */
    private boolean isSellPossible(TransactionOrder to) {
        int mtlu = to.getItem().getGood().getMTLU();
        return marketplace.getTl().ordinal() >= mtlu;
    }

    /**
     * Check if the player has enough goods that they demand on their transaction order.
     * @param to transaction order
     * @return true if the player has enough goods that can sell.
     */
    private boolean playerHasGoods(TransactionOrder to) {
        int quantity = to.getItem().getQuantity();
        Good good = to.getItem().getGood();
        return to.getInitiator().getShip().hasGoods(good, quantity);
    }

}
