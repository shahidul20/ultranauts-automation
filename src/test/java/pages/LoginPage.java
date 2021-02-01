package pages;

import helpers.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.CommonHelper.getEnvironmentProperty;

public class LoginPage extends PageHelper {

    private By userNameFieldEle = By.id("user-name");
    private By passwordFieldEle = By.xpath("//input[@id='password']");
    private By loginBtnEle = By.xpath("//input[@id='login-button']");
    private By textEle = By.xpath("//*[@id=\"login_button_container\"]/div/form/h3");


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void submitLogin() {
        String username = getEnvironmentProperty("username");
        String password = getEnvironmentProperty("password");
        submitLogin(username, password);
    }

    public void submitLogin(String username, String password) {
        setField(userNameFieldEle, username);
        setField(passwordFieldEle, password);
        clickOnElement(loginBtnEle);
    }

    public String getErrorText() {
        return getText(textEle);
    }
}
