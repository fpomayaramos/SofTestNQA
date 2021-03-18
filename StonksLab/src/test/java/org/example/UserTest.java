package org.example;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void testUserPortfolio() {
        User user = new User();
        Assert.assertNotNull(user);
        boolean isEmpty = user.isPortfolioEmpty();
        Assert.assertEquals(true, isEmpty);
        user.loadPortfolio();
        Assert.assertEquals(3, user.getPortfolio().size());
    }

}
