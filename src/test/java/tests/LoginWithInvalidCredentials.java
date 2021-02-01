package tests;

import helpers.TestHelper;
import org.junit.Test;
import pages.LoginPage;

public class LoginWithInvalidCredentials extends TestHelper {
    private String password = getEnvironmentProperty("password");
    private LoginPage loginPage = new LoginPage(driver);

    @Test
    public void verifyUnauthorizedUserCanNotLogInTest() {
        String expectedText = "Epic sadface: Username and password do not match any user in this service";
        String userNameInvalid = getEnvironmentProperty("unAuthorizedUserName");
        loginPage.submitLogin(userNameInvalid, password);
        String actualText = loginPage.getErrorText();
        verifyEquals("Test is failed because invalid credentials", expectedText, actualText);
    }
}
