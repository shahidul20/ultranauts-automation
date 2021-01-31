package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.HomePage;
import pages.ProductsPage;
import pages.YourChartPage;

public class ItemsAddToChart extends TestHelper {


    String username = getEnvironmentProperty("standardUserName");
    String password = getEnvironmentProperty("password");

    @Test
    public void verifyItemsAddToChartTest() {
        HomePage homePage = new HomePage(driver);
        homePage.logInWith(username, password);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemsInChart();
        productsPage.clickToChart();
        YourChartPage chartPage = new YourChartPage(driver);
        String actualValue = chartPage.getQuantityValue();
        Assert.assertEquals("Chart is not added", "3", actualValue);

    }
}
