package pages;

import helpers.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageHelper {

    private By userNameFieldEle = By.id("user-name");
    private By passwordFieldEle = By.xpath("//input[@id='password']");
    private By loginBtnEle = By.xpath("//input[@id='login-button']");
    private By textEle = By.xpath("//*[@id=\"login_button_container\"]/div/form/h3");


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void logInWith(String username, String password) {
        setField(userNameFieldEle, username);
        setField(passwordFieldEle, password);
        clickOnElement(loginBtnEle);

    }

    public String getErrorText() {
       return getText(textEle);
    }
}
