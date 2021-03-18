package org.example;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class StonkTest {

    @Test
    public void testStonk() {

        Stonk gamespot = new Stonk();

        Assert.assertNotNull(gamespot);

        Stonk gme = new Stonk("GME", 49.99, 20);

        Assert.assertEquals("GME", gme.getTicker());
        Assert.assertEquals(49.99, gme.getPrice(), 0);
        Assert.assertEquals(20, gme.getShares());
    }

    @Test
    public void testStonkPrice() {
        String ticker = "GME";
        double price = StonkUtility.getPriceOfStonk(ticker);
        Assert.assertTrue( price > 0);
        System.out.println(price);
    }

    @Test
    public void testStonkValue() {
        Stonk gme = new Stonk("GME", 800, 20); // arrange

        double total = gme.getTotalValue();                     // act

        Assert.assertEquals(16000, total, 0.1);   // assert   }
    }

    @Test
    public void testStonkWriting() throws IOException {
        Stonk gme = new Stonk("GME", 49.99, 20);
        String str = gme.toString(); // should be "GME:20"
        FileUtils.writeStringToFile(new File("gme.txt"), str, "UTF-8");
        String str2 = FileUtils.readFileToString(new File("gme.txt"), "UTF-8");
    }
}

