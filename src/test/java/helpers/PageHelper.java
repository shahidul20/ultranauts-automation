package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static helpers.CommonHelper.*;
import static org.junit.Assert.fail;

public abstract class PageHelper {

    public WebDriver driver;
    private JavascriptExecutor js;

    public PageHelper(WebDriver webDriver) {
        this.driver = webDriver;
        driver.manage().timeouts().pageLoadTimeout(TIMEOUT_SECONDS_SIXTY, TimeUnit.SECONDS);
        setDriverTimeout(TIMEOUT_SECONDS_DEFAULT);
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

    public void selectByVisibleText(By byElement, String text) {
        new Select(findWebElementBy(TIMEOUT_SECONDS_DEFAULT, byElement)).selectByVisibleText(text);
    }

    public String getText(int timeoutSeconds, By byElement) {
        return findWebElementBy(timeoutSeconds, byElement).getText().replaceAll("\n", "").trim();
    }

    public String getText(By byElement) {
        return getText(TIMEOUT_SECONDS_DEFAULT, byElement);
    }

    public List<String> getStringList(By byElement) {
        return getStringList(TIMEOUT_SECONDS_DEFAULT, byElement);
    }

    public List<String> getStringList(int timeoutSeconds, By byElement) {
        List<String> listStrings = new ArrayList<>();
        List<WebElement> webElements = findWebElementsBy(timeoutSeconds, byElement);
        for (WebElement element : webElements) {
            listStrings.add(element.getText().replaceAll("\\s+", " ").trim());
        }
        return listStrings;
    }

    public Map<String, String> getStringMap(List<String> headerName, List<String> columnValue) {
        Map<String, String> map = new HashMap<>();

        if (headerName.size() == columnValue.size()) {
            for (int i = 0; i < headerName.size(); i++) {
                map.put(headerName.get(i), columnValue.get(i));
            }
        } else {
            fail("Failed to create map, header and column value size not matched.\nHeader Value: " + headerName.size() + "\nRow Value: " + columnValue.size());
        }
        return map;
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
            takeScreenshot();
            fail("Failed to locate element byElement [" + byElement + "]\n" + e.getStackTrace());
        }
        return element;
    }

    public List<WebElement> findWebElementsBy(By byElement) {
        return findWebElementsBy(TIMEOUT_SECONDS_DEFAULT, byElement);
    }

    public List<WebElement> findWebElementsBy(int timeoutSeconds, By byElement) {
        return new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
    }

    /**
     * Set checkbox if not selected
     *
     * @param checkBox
     */
    public void setCheckbox(By checkBox) {
        WebElement webElement = findWebElementBy(checkBox);
        if (!webElement.isSelected()) {
            webElement.click();
        }
    }

    /**
     * Handle mouseover action using Actions moveToElement()
     *
     * @param locator
     * @return
     */
    public WebElement moveToElement(By locator) {
        WebElement element = findWebElementBy(locator);
        new Actions(driver).moveToElement(element).build().perform();
        return element;
    }

    /**
     * Set UI element attribute.
     *
     * @param element
     * @param attrName
     * @param contains
     * @param replace
     */
    public void setAttribute(WebElement element, String attrName, String contains, String replace) {
        if (element.getAttribute(attrName).contains(contains)) {
            element.getAttribute(attrName).replace(contains, replace);
        }
    }

    public void acceptBrowserAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS_DEFAULT);
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        } catch (Exception e) {
            //ignore
        }
    }

    /**
     * Switching to new window by Window index. Zero based window index.
     *
     * @param timeOutSec
     * @param expectedNumOfWin
     * @param switchToWindowIndex
     */
    public void switchToWindowByIndex(int timeOutSec, int expectedNumOfWin, int switchToWindowIndex) {
        try {
            setDriverTimeout(0);
            new WebDriverWait(driver, timeOutSec).until(ExpectedConditions.numberOfWindowsToBe(expectedNumOfWin));
            List<String> windows = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(windows.get(switchToWindowIndex));
        } catch (Exception e) {
            fail("Unable to switch to window by index: [" + switchToWindowIndex + "]\n" + e.getMessage());
        } finally {
            setDriverTimeout(TIMEOUT_SECONDS_DEFAULT);
        }
    }

    /**
     * JavascriptExecutor to interact with the browser elements.
     * Use these instead of sendKeys(), click() if facing challenges to interact on special UI framework.
     *
     * @param elementId
     * @param value
     */
    public void jsExecutorEnterById(String elementId, String value) {
        js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsById('" + elementId + "')[0].value='" + value + "'");
    }

    public void jsExecutorEnterByName(String elementName, String value) {
        js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsByName('" + elementName + "')[0].value='" + value + "'");
    }

    public void jsExecutorClickById(String elementId) {
        if (findWebElementBy(By.id(elementId)).isDisplayed()) {
            js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('" + elementId + "').click();");
        }
    }

    public void jsExecutorClickByName(String elementName) {
        if (findWebElementBy(By.name(elementName)).isDisplayed()) {
            js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementByName('" + elementName + "').click();");
        }
    }
    public void checkImage(By byElement)  {
        WebElement ImageFile = findWebElementBy( byElement);
       js= (JavascriptExecutor)driver;
       js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);

    }

}
