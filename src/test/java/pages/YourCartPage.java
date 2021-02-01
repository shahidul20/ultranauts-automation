package pages;

import helpers.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourCartPage extends PageHelper {
    private By totalCartQtyValueEle = By.xpath("//span[text()=\"3\"]");
    private By checkoutBtnEle = By.xpath("//a[@class=\"btn_action checkout_button\"]");

    public YourCartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getQuantityValue() {
        return getText(totalCartQtyValueEle);
    }

    public void clickOnCheckout() {
        clickOnElement(checkoutBtnEle);
    }
}
