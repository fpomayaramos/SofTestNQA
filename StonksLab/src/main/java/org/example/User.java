package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Represents the user of the app
 * The user will be able to
 * 1. View current price of a Stonk
 * 2. View portfolio and get total current value
 * 3. Add to portfolio
 * 4. Remove from portfolio
 * 5. Save portfolio
 */

public class User {
    // design error! multiple users should not share same text file!!!
    private static String PORTFOLIO_FILE = "portfolio.txt";
    private Portfolio portfolio;

    /**
     * When a User is instantiated, the portfolio is loaded from the text file
     */
    public User() {
        if(!isPortfolioEmpty()) loadPortfolio();
    }

    /**
     * Returns true if the "portfolio.txt" file is empty
     * @return true if empty, false otherwise
     */
    public boolean isPortfolioEmpty() {
        String str = "";
        try {
            str = FileUtils.readFileToString(new File(PORTFOLIO_FILE), "UTF-8");
        } catch(IOException e) {
            throw new RuntimeException("Can't read portfolio file");
        }
        return str.equals("") ? true : false;
    }

    /**
     * Loads stonks from the "portfolio.txt" file
     * The file "portfolio.txt" simply displays the ticker the number of shares in
     */
    public void loadPortfolio() {
        portfolio = new Portfolio();
        String line = "";
        try {
            line = FileUtils.readFileToString(new File(PORTFOLIO_FILE), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("unable to read portfolio read");
        }
        String[] array = line.split(",");
        for (int i = 0; i < array.length; i++) {
            String ticker = array[i].split(":")[0];
            int shares = Integer.parseInt(array[i].split(":")[1]);
            double price = StonkUtility.getPriceOfStonk(ticker);
            Stonk stonk = new Stonk(ticker, price, shares);
            portfolio.add(stonk);
        }

    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * THIS METHOD WILL BE TESTED MANUALLY
     * Given a ticker, print out the price of the Stonk
     * @param ticker ticker of the Stonk
     */
    public void printStonkPrice(String ticker) {
        double price = StonkUtility.getPriceOfStonk(ticker);
        System.out.println(price);
    }

    /**
     * print out instructions for the user
     */
    public void showMenu() {
        System.out.println("1. Get price of the individual Stonk");
        System.out.println("2. Get price of portfolio");
        System.out.println("3. Add Stonk to portfolio");
        System.out.println("4. Remove Stonk from portfolio");
    }
}
