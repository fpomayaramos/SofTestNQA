package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.Port;

public class PortfolioTest {

    private Portfolio portfolio;
    private Stonk gme;
    private Stonk nok;

    @Before
    public void setUp() {
        portfolio = new Portfolio();
        gme = new Stonk("GME", 50, 20);
        nok = new Stonk("NOK", 5, 100);
        portfolio.add(gme);
        portfolio.add(nok);
    }

    @Test
    public void testEmptyPortfolio() {
        Portfolio portfolio = new Portfolio();
        Assert.assertNotNull(portfolio);
    }

    @Test
    public void testAddStonk() {
        Assert.assertEquals(2, portfolio.size());
    }

    @Test
    public void testRemoveStonk() {
        portfolio.remove(new Stonk("GME", 200, 1));
        portfolio.remove(new Stonk("NOK", 10, 2));
        Assert.assertEquals(0, portfolio.size());
    }

    @Test
    public void testUpdatePortfolio() {
        portfolio.update();
        double totalValue = portfolio.getTotalValue();
        System.out.println(totalValue);
        Assert.assertTrue(totalValue > 0);
    }

    @Test
    public void testTotalValue() {
        gme.setPrice(150);
        nok.setPrice(15);
        Stonk tesla = new Stonk("TSLA", 800, 2);
        portfolio.add(tesla);
        double total = (150 * 20) + (5 * 100) + (800 * 2);
        Assert.assertEquals(total, portfolio.getTotalValue(), 0.1);
    }
}
