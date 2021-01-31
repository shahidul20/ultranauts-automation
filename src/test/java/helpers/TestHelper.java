package helpers;

import org.apache.commons.lang3.StringUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import static org.junit.Assert.assertEquals;

public class TestHelper extends CommonHelper{
    public static WebDriver driver;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @BeforeClass
    public static void setDriver() {
        DriverType driverType = DriverType.valueOf(StringUtils.isEmpty(System.getenv("DRIVER_TYPE")) ? "CHROME" : System.getenv("DRIVER_TYPE"));
        driver = loadWebDriver(driverType);
        driver.get(getEnvironmentProperty("app.url"));
    }

//    @Before
//    public void setupTest() {
//        String username = getEnvironmentProperty("standardUserName");
//        String password = getEnvironmentProperty("password");
//        HomePage homePage = new HomePage(driver);
//        homePage.lotinWith(username,password);
////        LoginPage logInPage = new LoginPage(driver);
////        logInPage.loginWith(username, password);
////        logInPage.closeWindow();
//    }

    @AfterClass
    public static void tearDownDriver() {
        driver.quit();
    }

    public void verifyEquals(String message, String expected, String actual) {
        try {
            assertEquals(message, expected, actual);
        } catch (AssertionError e) {
            errorCollector.addError(e);
            takeScreenshot();
        }
    }
}
