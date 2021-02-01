package pages;

import helpers.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends PageHelper {
    private By firstNameFieldEle = By.xpath("//input[@id=\"first-name\"]");
    private By lasttNameFieldEle = By.xpath("//input[@id=\"last-name\"]");
    private By zipCodeFieldEle = By.xpath("//input[@id=\"postal-code\"]");
    private By continueBtnEle = By.xpath("//input[@class=\"btn_primary cart_button\"]");

    public CheckoutInformationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void setUserInformation() {
        setField(firstNameFieldEle, "abc");
        setField(lasttNameFieldEle, "xyz");
        setField(zipCodeFieldEle, "20878");
    }

    public void clickOnContinue() {
        clickOnElement(continueBtnEle);
    }
}
