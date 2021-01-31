package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.YourChartPage;

public class ItemsRemoveFromChart extends TestHelper {

    String username = getEnvironmentProperty("standardUserName");
    String password = getEnvironmentProperty("password");

    @Test
    public void verifyItemsRemoveFromChart(){
        HomePage homePage = new HomePage(driver);
        homePage.logInWith(username,password);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemsInChart();
        productsPage.clickToChart();
        YourChartPage chartPage = new YourChartPage(driver);
        chartPage.removeBikeLightItems();

        if(chartPage.isQtnNumberVisible()){
            Assert.assertTrue("items remove",true);
        }
        String actualValue = chartPage.getAfterRemoveQtnValue();
        Assert.assertEquals("Items nor removed","2",actualValue);
    }
}
