package org.example;

/**
 * Class to represent a Stock. Named "Stonk" because that's what the cool kids are calling it
 *
 * by Fran
 * 2/15/2021
 */

public class Stonk {

    /**
     * Construct Stonk with ticker, price and number of shares
     * @param ticker symbol of the stonk
     * @param price shows value of the stonk
     * @param shares number of purchased shares
     */
    private String ticker;
    private double price;
    private int shares;

    public Stonk() {

    }

    public Stonk(String ticker, double price, int shares) {
        this.ticker = ticker;
        this.price = price;
        this.shares = shares;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    /**
     * Return the current value (price * shares) of the stonk
     * @return value of the "investment"
     */
    public double getTotalValue() {
        return shares * price;
    }

    /**
     * Return string representation of stonk which is "price:shares"
     * @return string representation of stonk
     */
    @Override
    public String toString() {
        return ticker + ": " + shares;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
