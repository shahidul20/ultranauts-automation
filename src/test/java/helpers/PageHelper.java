package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class PageHelper {

    private static final int TIMEOUT_SECONDS_DEFAULT = 30;
    public WebDriver driver;
    private JavascriptExecutor js;

    public PageHelper(WebDriver webDriver) {
        this.driver = webDriver;
        driver.manage().timeouts().pageLoadTimeout(TIMEOUT_SECONDS_DEFAULT, TimeUnit.SECONDS);
        setDriverTimeout(TIMEOUT_SECONDS_DEFAULT);
    }

    public void setDriverTimeout(int timeoutSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
    }

    public boolean isElementFound(By byElement, int timeoutSeconds) {
        boolean elementFound = false;
        try {
            setDriverTimeout(0);
            new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(byElement));
            elementFound = true;
        } catch (Exception ignore) {

        } finally {
            setDriverTimeout(TIMEOUT_SECONDS_DEFAULT);
        }
        return elementFound;
    }

    public void setField(By byElement, String string) {

        setField(TIMEOUT_SECONDS_DEFAULT, byElement, string);
    }

    public void setField(int timeoutSeconds, By byElement, String string) {
        findWebElementBy(timeoutSeconds, byElement).clear();
        findWebElementBy(timeoutSeconds, byElement).sendKeys(string);
    }

    public void clickOnElement(By byElement) {
        clickOnElement(TIMEOUT_SECONDS_DEFAULT, byElement);
    }

    public void clickOnElement(int timeoutSeconds, By byElement) {
        try {
            setDriverTimeout(0);
            new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.elementToBeClickable(byElement));
            driver.findElement(byElement).click();
        } catch (Exception e) {
            fail("!!!!!!!!!!!!!!! Failed to locate element byElement [" + byElement + "].\n" + e.getStackTrace());
        } finally {
            setDriverTimeout(TIMEOUT_SECONDS_DEFAULT);
        }
    }

    public String getText(int timeoutSeconds, By byElement) {
        return findWebElementBy(timeoutSeconds, byElement).getText().replaceAll("\n", "").trim();
    }

    public String getText(By byElement) {
        return getText(TIMEOUT_SECONDS_DEFAULT, byElement);
    }

    public WebElement findWebElementBy(By byElement) {
        return findWebElementBy(TIMEOUT_SECONDS_DEFAULT, byElement);
    }

    public WebElement findWebElementBy(int timeoutSeconds, By byElement) {
        WebElement element = null;
        try {
            setDriverTimeout(0);
            new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(byElement));
            element = driver.findElement(byElement);
        } catch (Exception e) {

            fail("Failed to locate element byElement [" + byElement + "]\n" + e.getStackTrace());
        }
        return element;
    }

    public void checkImage(By byElement) {
        WebElement ImageFile = findWebElementBy(byElement);
        js = (JavascriptExecutor) driver;
        js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
    }

}
