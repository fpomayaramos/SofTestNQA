package org.example;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

/**
 * Utility class for Stonk information
 *
 * 2/15/2021 monday
 */

public class StonkUtility {

    /**
     * Grab the current (latest) price of a stonk given the ticker
     * @param ticker the stonk ticker
     */
    public static double getPriceOfStonk(String ticker) {
        double price = 0;
        try {
            Stock stock = YahooFinance.get(ticker);
            price = stock.getQuote().getPrice().doubleValue();
        } catch(IOException e) {
            throw new RuntimeException("Stonk data could not be retrieved!");
        }
        return price;
    }
}
