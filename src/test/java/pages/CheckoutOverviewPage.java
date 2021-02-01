package pages;

import helpers.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends PageHelper {

    private By itemTotalAmountEle = By.xpath("//*[@class=\"summary_info\"]/div[5]");
    private By taxAmountEle = By.xpath("//*[@class=\"summary_info\"]/div[6]");
    private By totalAmountEle = By.xpath("//*[@class=\"summary_info\"]/div[7]");
    private By finishBtn = By.xpath("//*[@class=\"summary_info\"]/div[8]/a[2]");

    public CheckoutOverviewPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTotalPrice() {
        return getText(totalAmountEle);
    }

    public void clickOnFinish() {
        clickOnElement(finishBtn);
    }
}
