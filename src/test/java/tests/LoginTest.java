package tests;

import helpers.TestHelper;
import org.junit.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends TestHelper {
    private String password = getEnvironmentProperty("password");
    private LoginPage loginPage = new LoginPage(driver);

    @Test
    public void getTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        verifyEquals("Test is fail", expectedTitle, actualTitle);
    }

    @Test
    public void loginWithValidCredentialLoginSuccessful() {
        String expectedText = "Products";
        loginPage.submitLogin();
        String actualText = new ProductsPage(driver).getProductsText();
        verifyEquals("Login fail with valid credential", expectedText, actualText);
    }

    @Test
    public void verifyLockedOutUserCanNotLogInTest() {
        String expectedText = "Epic sadface: Sorry, this user has been locked out.";
        String userNameLockedOut = getEnvironmentProperty("lockedOutUserName");
        loginPage.submitLogin(userNameLockedOut, password);
        String actualText = loginPage.getErrorText();
        verifyEquals("Test is failed because invalid credentials", expectedText, actualText);
    }


}
