package pages;

import helpers.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourChartPage extends PageHelper {

private By totalChartQtyValueEle = By.xpath("//span[text()=\"3\"]");
private By inChartAfterRemoveQtyValueEle = By.xpath("//span[text()=\"2\"]");


private By removeBikeLightBtnEle = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/button");
private By checkoutBtnEle = By.xpath("//a[@class=\"btn_action checkout_button\"]");

    public YourChartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void removeBikeLightItems(){
        clickOnElement(removeBikeLightBtnEle);
    }
    public String getQuantityValue(){
        return getText(totalChartQtyValueEle);
    }
    public String getAfterRemoveQtnValue(){
        return getText(inChartAfterRemoveQtyValueEle);
    }
    public boolean isQtnNumberVisible(){
        return isElementFound(inChartAfterRemoveQtyValueEle, 5);
    }
    public void clickOnCheckout(){
        clickOnElement(checkoutBtnEle);
    }
}
