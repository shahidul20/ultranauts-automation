package tests;

import helpers.TestHelper;
import org.junit.Test;
import pages.HomePage;

public class LockedOutUserCanNotLogIn extends TestHelper {

    String username = getEnvironmentProperty("lockedOutUserName");
    String password = getEnvironmentProperty("password");
    String expectedText ="Epic sadface: Sorry, this user has been locked out.";
    @Test
    public void verifyLockedOutUserCanNotLogInTest(){
        HomePage homePage = new HomePage(driver);
        homePage.logInWith(username,password);
        String actualText = homePage.getErrorText();
        verifyEquals("Test is failed because invalid credentials",expectedText,actualText);


    }
}
