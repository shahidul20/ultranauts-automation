package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.ProductsPage;

public class TestAllTheThingsItemsIsAvailable extends TestHelper {

    String username = getEnvironmentProperty("standardUserName");
    String password = getEnvironmentProperty("password");
    @Test
    public void verifyTestallTheItemsIsAvailableTest(){
        HomePage homePage = new HomePage(driver);
        homePage.logInWith(username, password);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.checkRedTshirtIsAvailable();
        String  actualMessage = productsPage.getChartItemNumber();
        Assert.assertEquals("Test fail because value not add","1",actualMessage);
    }
}
