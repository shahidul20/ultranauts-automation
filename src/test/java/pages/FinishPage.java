package pages;

import helpers.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinishPage extends PageHelper {

    private By ponyExpressImgEle = By.xpath("//img[@class=\"pony_express\"]");

    public FinishPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean getImage() {
        checkImage(ponyExpressImgEle);
        return true;
    }
}
