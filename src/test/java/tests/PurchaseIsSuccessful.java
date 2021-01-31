package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import pages.*;

public class PurchaseIsSuccessful extends TestHelper {
    String username = getEnvironmentProperty("glitchUserName");
    String password = getEnvironmentProperty("password");

    @Test
    public void verifyPurchaseIsSuccessfulTest() {
        HomePage homePage = new HomePage(driver);
        homePage.logInWith(username, password);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemsInChart();
        productsPage.clickToChart();
        YourChartPage chartPage = new YourChartPage(driver);
        String actualValue = chartPage.getQuantityValue();
        Assert.assertEquals("Chart is not added", "3", actualValue);

        chartPage.clickOnCheckout();
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutInformationPage.setUserInformation();
        checkoutInformationPage.clickOnContinue();
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        String totalPrice = checkoutOverviewPage.getTotalPrice();
        Assert.assertEquals("Total amount is not match", "Total: $60.45", totalPrice);
        checkoutOverviewPage.clickOnFinish();
        FinishPage finishPage = new FinishPage(driver);
        if(finishPage.getImage()){
            Assert.assertTrue("Test is fail",true);
        }
    }
}
