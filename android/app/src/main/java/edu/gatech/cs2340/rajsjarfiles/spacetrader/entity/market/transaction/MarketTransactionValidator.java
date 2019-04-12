package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

import java.util.AbstractMap;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Marketplace;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.TradeGoods;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Wallet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.TechLevel;

/**
 * Transaction Validator for transaction with marketplace.
 */
public class MarketTransactionValidator implements TransactionValidator {

    // Market place where this validator is working on.
    private final Marketplace marketplace;

    /**
     * Constructor for this class
     *
     * @param mp marketplace in which this validator validates.
     */
    public MarketTransactionValidator(Marketplace mp) {
        this.marketplace = mp;
    }

    @Override
    public boolean validate(TransactionOrder order) {
        Item item = order.getItem();
        Good goodInOrder = item.getGood();
        int quantityInOrder = item.getQuantity();
        Player playerInOrder = order.getInitiator();


        Item marketItem = marketplace.getItem(goodInOrder);
        if (order.getTransactionType() == TransactionType.BUY) {

            // Check if the market place has good
            if (!hasGoods(order)) {
                return false;
            }

            int marketPrice = marketplace.getMarketPrice(goodInOrder);

            Wallet wallet = playerInOrder.getWallet();

            // Check if the initiator has enough credits
            if (!wallet.checkCreditEnough(
                    marketPrice * quantityInOrder)) {
                return false;
            }

            // Check if the initiator has enough cargo capacity
            if (!playerInOrder.checkCargoCapacityEnough(quantityInOrder)) {
                return false;
            }

            // Set a market price to the good that the player is trying to buy
            item.setPrice(marketPrice);

            // Subtract amount of quantity of good from the market

            marketItem.subQuantity(quantityInOrder);

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
            item.setPrice(marketPrice);


            marketItem.addQuantity(quantityInOrder);
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
        Item item = order.getItem();
        Good good = item.getGood();

        AbstractMap<TradeGoods, Item> tradeGoods = marketplace.getTradeGoodsInMarket();
        Item marketItem = marketplace.getItem(good);
        if (tradeGoods.containsKey(good)) {
            return marketItem.getQuantity()
                    >= item.getQuantity();
        }
        return false;
    }

    /**
     * Check if the good can be sold in the market
     * @param order transaction order
     * @return true if this good can be sold to the marketplace
     */
    private boolean isSellPossible(TransactionOrder order) {
        Item item = order.getItem();
        Good g = item.getGood();
        int mtlu = g.getMTLU();

        TechLevel t = marketplace.getTechLevel();
        return t.ordinal() >= mtlu;
    }

    /**
     * Check if the player has enough goods that they
     * demand on their transaction order.
     * 
     * @param order transaction order
     * @return true if the player has enough goods that can sell.
     */
    private boolean playerHasGoods(TransactionOrder order) {
        Item item = order.getItem();
        int quantity = item.getQuantity();
        Good good = item.getGood();

        Player initiator = order.getInitiator();
        Ship s = initiator.getShip();
        return s.hasGoods(good, quantity);
    }

}
