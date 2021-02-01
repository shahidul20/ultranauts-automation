package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;

public class CommonHelper {
    private static final int TIMEOUT_SECONDS_DEFAULT = 5;
    public static WebDriver driver;

    public static WebDriver loadWebDriver(String driverType) {
        if ("chrome".equalsIgnoreCase(driverType)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(driverType)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(driverType)) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        setDriverDefaultTimeout();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static void setDriverDefaultTimeout() {
        driver.manage().timeouts().implicitlyWait(TIMEOUT_SECONDS_DEFAULT, TimeUnit.SECONDS);
    }

    public static String getSeleniumDownloadDirectoryPath() {
        String defaultDownloadPath = SystemUtils.IS_OS_WINDOWS ? "C:\\selenium-tests-downloads\\" : System.getProperty("user.home") + "/selenium-tests-downloads/";
        File directory = new File(defaultDownloadPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        System.out.println("++++++++++ All Selenium Test downloads located: " + defaultDownloadPath + " ++++++++++");
        return defaultDownloadPath;
    }

    public static String getEnvironmentProperty(String propertyName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(getEnvironment());
        return resourceBundle.getString(propertyName);
    }

    private static String getEnvironment() {
        String env = null;
        try {
            if (StringUtils.isEmpty(System.getenv("ENVRIONMENT"))) {
                env = "test";
            } else {
                env = System.getenv("ENVRIONMENT");
            }
        } catch (Exception e) {
            fail("Please enter a valid environment, please use one of the valid environment name: test, preprod, prod");
        }
        return env;
    }

    public static void captureScreenshot() {
        String fileName = "selenium-screen-capture-" + new SimpleDateFormat("MMddyyyymmsss").format(new Date()) + ".png";
        String filePathWithFileName = getSeleniumDownloadDirectoryPath() + fileName;
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePathWithFileName));
            System.out.println("++++++++++ Screenshot captured and saved: " + filePathWithFileName + " ++++++++++");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}