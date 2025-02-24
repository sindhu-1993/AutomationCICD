package selenium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.AbstaractComponents.OrderPage;
import selenium.TestComponents.BaseTest;
import selenium.pageobjects.CartPage;
import selenium.pageobjects.CheckoutPage;
import selenium.pageobjects.ConfirmationPage;
import selenium.pageobjects.ProductCatalogue;

public class submitorder extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("sindhuchowdary.qa@gmail.com", "Sadhguru1@");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}


	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\selenium\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}

/*
 * @DataProvider 
 * public Object[][] getData() {
 * 
 * HashMap<String,String> map = new HashMap<String,String>(); 
 * map.put("email","sindhuchowdary.qa@gmail.com"); 
 * map.put("password", "Sadhguru1@");
 * map.put("product", "ZARA COAT 3");
 * 
 * HashMap<String,String> map1 = new HashMap<String,String>(); 
 * map1.put("email","bijjam93@gmail.com"); 
 * map1.put("password", "Pradeep1@"); 
 * map1.put("product","ADIDAS ORIGINAL");
 * 
 * return new Object[][] {{map}, {map1}};
 */


