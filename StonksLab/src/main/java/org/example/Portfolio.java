package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the portfolio of Stonks
 */
public class Portfolio {

    private List<Stonk> stonks = new ArrayList<>();

    /**
     * Need default constructor for serialization
     */
    public Portfolio() {

    }

    /**
     * Adds Stonk to the portfolio
     * @param stonk the stonk to add
     */
    public void add(Stonk stonk) {
        stonks.add(stonk);
    }

    /**
     * Returns the size of the portfolio
     */
    public int size() {
        return stonks.size();
    }

    /**
     * Removes a stonk from portfolio
     */
    public void remove(Stonk stonk) {
        stonks.remove(stonk);
    }

    /**
     * Updates the portfolio with the latest price of each stonk
     */
    public void update() {
        for (int i = 0; i < stonks.size(); i++) {
            Stonk current = stonks.get(i);
            current.setPrice(StonkUtility.getPriceOfStonk(current.getTicker()));
        }
    }

    /**
     * Returns the total dollar value of the portfolio (shares * price)
     */
    public double getTotalValue() {
        double total = 0;
        for (int i = 0; i < stonks.size(); i++) {
            total += stonks.get(i).getPrice() * stonks.get(i).getShares();
        }
        return total;
    }

    @Override
    public String toString() {
        String str = "Your portfolio consists of: ";
        for (int i = 0; i < stonks.size(); i++){
            str += "\n" + stonks.get(i).getTicker() + " " + stonks.get(i).getShares();
        }
        return str;
    }
}
