package tests;

import helpers.TestHelper;
import org.junit.Test;
import pages.HomePage;
import pages.ProductsPage;

public class AuthorizedUserCanLogIn extends TestHelper {
    String username = getEnvironmentProperty("standardUserName");
    String password = getEnvironmentProperty("password");
    private String expectedText="Products";

    @Test
    public void logInTest(){
        HomePage homePage = new HomePage(driver);
        homePage.logInWith(username,password);
        String actualText = new ProductsPage(driver).getProductsText();
        verifyEquals("Test is fail",expectedText,actualText);

    }
}
