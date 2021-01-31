package tests;

import helpers.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;

public class BrowserTitle extends TestHelper {
    @Test
    public void getTitle(){
        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
       verifyEquals("Test is fail",expectedTitle,actualTitle);

    }
}
