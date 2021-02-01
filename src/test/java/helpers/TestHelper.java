package helpers;

import org.apache.commons.lang3.StringUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class TestHelper extends CommonHelper {
    public static WebDriver driver;
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @BeforeClass
    public static void setDriver() {
        String driverType = StringUtils.isEmpty(System.getenv("DRIVER_TYPE")) ? "chrome" : System.getenv("DRIVER_TYPE");
        driver = loadWebDriver(driverType);

    }

    @Before
    public void openBrowser() {
        driver.get(getEnvironmentProperty("app.url"));
    }

    @After
    public void closeBrowser() {

    }

    @AfterClass
    public static void tearDownDriver() {
        driver.quit();
    }

    public void verifyEquals(String message, String expected, String actual) {
        try {
            assertEquals(message, expected, actual);
        } catch (AssertionError e) {
            errorCollector.addError(e);
            captureScreenshot();
        }
    }
}
