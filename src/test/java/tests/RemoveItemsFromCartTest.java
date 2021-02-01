package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class RemoveItemsFromCartTest extends TestHelper {
    ProductsPage productsPage = new ProductsPage(driver);

    @Test
    public void verifyItemsRemoveFromChart() {
        new LoginPage(driver).submitLogin();
        productsPage.addItemsInCart();
        productsPage.removeItem();
        String actualValue = productsPage.getCartItemsValueAfterRemoveItem();
        Assert.assertEquals("Test is fail,Items not removed", "2", actualValue);
    }
}
