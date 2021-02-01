package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductPageTest extends TestHelper {
    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = new ProductsPage(driver);

    @Test
    public void verifyTestAllTheItemsIsAvailableTest() {
        loginPage.submitLogin();
        productsPage.checkRedTshirtIsAvailable();
        String actualMessage = productsPage.getQtyValue();
        Assert.assertEquals("Test fail because value not add", "1", actualMessage);
    }
}
