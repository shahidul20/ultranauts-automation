package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductCartTest extends TestHelper {
    ProductsPage productsPage = new ProductsPage(driver);

    @Test
    public void verifyItemsAddToCart() {
        new LoginPage(driver).submitLogin();
        productsPage.addItemsInCart();
        String actualValue = productsPage.getChartItemNumber();
        Assert.assertEquals("Chart is not added", "3", actualValue);
    }
}
