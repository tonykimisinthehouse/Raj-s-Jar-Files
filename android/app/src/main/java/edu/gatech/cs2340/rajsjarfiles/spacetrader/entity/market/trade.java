package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market;

public class trade {

    /**
     * Makes sure that the user can make the purchase that he/she has requested.
     * @param marketPrice the price the market is selling at
     * @param quantity the number of items the user wants to buy
     * @param currentCredit the amount of credit the user has
     * @param quantity the number of items the user wants to buy
     * @param currentStock the number of items the user has in his/her cargo
     * @param cargoCapacity the cargo capacity of the ship
     * @return boolean of whether the user made the purchase or not
     */
    public Boolean makePurchase (int marketPrice,
                                        int quantity,
                                        int currentCredit,
                                        int currentStock,
                                        int cargoCapacity) {
        if (checkCreditEnough(marketPrice, quantity, currentCredit)
                && checkCargoCapacityEnough(quantity, currentStock, cargoCapacity)) {
            // Do something with ship cargo and user credit
            return true;
        } else {
            return false;
        }


    }
    /**
     * Makes sure that the user cannot buy more goods than you have money
     * @param marketPrice the price the market is selling at
     * @param quantity the number of items the user wants to buy
     * @param currentCredit the amount of credit the user has
     * @return boolean of whether the user has enough credit
     */
    public Boolean checkCreditEnough(int marketPrice, int quantity, int currentCredit) {
        if (marketPrice * quantity > currentCredit ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Makes sure the user cannot buy more goods than the cargo capacity
     * @param quantity the number of items the user wants to buy
     * @param currentStock the number of items the user has in his/her cargo
     * @param cargoCapacity the cargo capacity of the ship
     * @return boolean of whether the user has enough cargo capcity
     */
    public Boolean checkCargoCapacityEnough(int quantity, int currentStock, int cargoCapacity) {
        if (quantity + currentStock > cargoCapacity) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Makes sure the user cannot sell more goods than he/she has.
     * @param quantitySelling
     * @param currentStock
     * @return boolean of whether the user made the sell or not.
     */
    public Boolean makeSale(int quantitySelling, int currentStock) {
        if (quantitySelling > currentStock) {
            return false;
        }
        // do something.
        return true;
    }

}

