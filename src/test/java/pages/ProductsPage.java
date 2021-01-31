package pages;

import helpers.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends PageHelper {
    private By productsTextEle = By.xpath("//div[@class='product_label']");

    private By backPackAddToChartBtnEle = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button");
    private By bikeLightAddToChartBtnEle = By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button");
    private By redTshirtAddToChartBtnEle = By.xpath("//*[@id=\"inventory_container\"]/div/div[6]/div[3]/button");
    private By shopingChartBtnEle = By.xpath("//*[@id=\"shopping_cart_container\"]");
    private By inChartTotalQtyValueEle = By.xpath("//span[text()=\"1\"]");

    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getProductsText() {
        return getText(productsTextEle);
    }

    public void addItemsInChart() {
        clickOnElement(backPackAddToChartBtnEle);
        clickOnElement(bikeLightAddToChartBtnEle);
        clickOnElement(redTshirtAddToChartBtnEle);
    }

    public void clickToChart() {
        clickOnElement(shopingChartBtnEle);
    }

    public void checkRedTshirtIsAvailable() {
        if (isElementFound(redTshirtAddToChartBtnEle, 2)) {
            clickOnElement(redTshirtAddToChartBtnEle);
        }
    }
    public String getChartItemNumber(){
       return getText(inChartTotalQtyValueEle);
    }
}
