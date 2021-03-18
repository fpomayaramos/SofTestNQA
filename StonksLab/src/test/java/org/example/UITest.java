package org.example;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UITest {

    private Stonk gme = null;

    @BeforeClass
    public static void createUI() {
        System.out.println("Creating UI");
    }

    @Before
    public void setUp() {
        gme = new Stonk("GME", 49.99, 20);
    }

    @Test
    public void test1() {
        gme.setPrice(200);
        System.out.println(gme);
    }

    @Test
    public void test2() {
        System.out.println(gme);
    }

    @AfterClass
    public static void disposeUI() {

    }
}
