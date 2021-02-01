package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        ProductCartTest.class,
        CheckoutTest.class,
        ProductPageTest.class,
        LoginWithInvalidCredentials.class,
        RemoveItemsFromCartTest.class
})
public class TestSuite {

}
