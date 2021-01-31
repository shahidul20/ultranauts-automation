package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;

public class UnauthorizedUserCanNotLogIn extends TestHelper {
    String username = getEnvironmentProperty("unAuthorizedUserName");
    String password = getEnvironmentProperty("password");
    String expectedText ="Epic sadface: Username and password do not match any user in this service";
    @Test
    public void verifyUnauthorizedUserCanNotlogInTest(){
        HomePage homePage = new HomePage(driver);
        homePage.logInWith(username,password);
        String actualText = homePage.getErrorText();
        verifyEquals("Test is failed because invalid credentials",expectedText,actualText);


    }
}
