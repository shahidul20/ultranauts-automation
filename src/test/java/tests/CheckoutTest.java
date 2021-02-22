package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import pages.*;

public class CheckoutTest extends TestHelper {
    ProductsPage productsPage = new ProductsPage(driver);
    YourCartPage chartPage = new YourCartPage(driver);
    CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);

    @Test
    public void verifyPurchaseIsSuccessfulTest() {
        new LoginPage(driver).submitLogin();
        productsPage.addItemsInCart();
        productsPage.clickToCart();
        String actualValue = chartPage.getQuantityValue();
        Assert.assertEquals("Chart is not added", "3", actualValue);
        chartPage.clickOnCheckout();
        checkoutInformationPage.setUserInformation();
        checkoutInformationPage.clickOnContinue();
        String totalPrice = checkoutOverviewPage.getTotalPrice();
        Assert.assertEquals("Total amount is not match", "Total: $60.45", totalPrice);
        checkoutOverviewPage.clickOnFinish();
        Assert.assertTrue("Test is fail", new FinishPage(driver).getImage());
    }

    @Test
    public void verifyTotalPriceOfTheChartTest() {
        new LoginPage(driver).submitLogin();
        productsPage.addItemsInCart();
        productsPage.clickToCart();
        String actualValue = chartPage.getQuantityValue();
        Assert.assertEquals("Chart is not added", "3", actualValue);
        chartPage.clickOnCheckout();
        checkoutInformationPage.setUserInformation();
        checkoutInformationPage.clickOnContinue();
        String totalPrice = checkoutOverviewPage.getTotalPrice();
        Assert.assertEquals("Total amount is not match", "Total: $60.45", totalPrice);
    }
}
