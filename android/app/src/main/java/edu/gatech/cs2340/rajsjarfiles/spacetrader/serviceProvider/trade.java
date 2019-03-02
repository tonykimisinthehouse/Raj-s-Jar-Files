package edu.gatech.cs2340.rajsjarfiles.spacetrader.serviceProvider;

public class trade {


    public Boolean checkUserCanPurchase (int marketPrice,
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
     * @param credit the amount of credit the user has
     * @return
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
     * @return
     */
    public Boolean checkCargoCapacityEnough(int quantity, int currentStock, int cargoCapacity) {
        if (quantity + currentStock > cargoCapacity) {
            return false;
        } else {
            return true;
        }
    }
}

