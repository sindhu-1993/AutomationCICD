package selenium.tests;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.TestComponents.Retry;

import selenium.TestComponents.BaseTest;
import selenium.pageobjects.CartPage;
import selenium.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {


	    @Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	    public void LoginErrorValidation() throws IOException, InterruptedException
	    {
		landingpage.loginApplication("sindhuchowdary.qa@gmail.com", "sadhguru1@");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
}
	    
	    @Test
	    public void ProductErrorValidation() throws IOException, InterruptedException
	    {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("bijjam93@gmail.com", "Pradeep1@");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
	    Assert.assertFalse(match);
	   

	}
}

