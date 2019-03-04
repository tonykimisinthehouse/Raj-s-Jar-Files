package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Marketplace;

public class MarketTransactionValidator implements TransactionValidator {

    private Marketplace marketpPlace;

    public MarketTransactionValidator(Marketplace mp) {
        this.marketpPlace = mp;
    }

    public boolean validate(TransactionOrder to) {

        if (to.getTransactionType() == TransactionType.BUY) {

            // Check if the market place has good
            if (!hasGoods(to)) return false;

            // Add price to the good
            to.getItem().setPrice(marketpPlace.getTradeGoodsEnumMap().get(to.getItem()).getPrice());

            // Check if the initiator has enough credits
            if (!to.getInitiator().checkCreditEnough(to.getItem().getPrice())) return false;

            // Check if the initiator has enough cargo capacity
            if (!to.getInitiator().checkCargoCapacityEnough(to.getItem().getQuantity())) return false;

            return true;

        } else{

            // If the good can be sold in certain market
            if (!isSellPossible(to)) return false;

            // If the player has goods to sell
            if (!playerHasGoods(to)) return false;

            // Update price for selling goods
            to.getItem().setPrice(marketpPlace.getTradeGoodsEnumMap().get(to.getItem()).getPrice());

            // Should the market now have sold good??????????????????????

            return true;
        }
    }

    public TransactionResult validateNTransaction(TransactionOrder to) {
        Boolean isTransSuccess = validate(to);
        TransactionResult tr = new TransactionResult(isTransSuccess, to.getItem());
        return tr;
    }

    private boolean hasGoods(TransactionOrder to) {
        Good good = to.getItem().getGood();
        if (marketpPlace.getTradeGoodsEnumMap().containsKey(good)) {
            return marketpPlace.getTradeGoodsEnumMap().get(good).getQuantity()
                    >= to.getItem().getQuantity();
        }
        return false;
    }

    private boolean isSellPossible(TransactionOrder to) {
        return marketpPlace.getTl().ordinal() >= to.getItem().getGood().getMTLU();
    }

    private boolean playerHasGoods(TransactionOrder to) {
        return to.getInitiator().getShip().hasGoods(to.getItem().getGood(), to.getItem().getQuantity());
    }

}
