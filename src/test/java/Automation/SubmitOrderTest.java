package Automation;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	String ProductName="ZARA COAT 3";

     
    @Test
	public  void SubmitOrder() throws Exception {

		String CountryName="india";
		
		ProductCatalog productCatalog=landingPage.loginApplication("shivam1@gmail.com", "Grimreaper1@");
		
		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(ProductName);
		CartPage cartPage=productCatalog.goToCartPage();
		
		Boolean match=cartPage.verifyProductDisplay(ProductName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		
		checkOutPage.selectCountry(CountryName);
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		
		 String confirmMessage=confirmationPage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
    
    
    @Test(dependsOnMethods = {"SubmitOrder"})
    public void OrderHistory()
    {
		ProductCatalog productCatalog=landingPage.loginApplication("shivam1@gmail.com", "Grimreaper1@");
		OrderPage orderPage=productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(ProductName));

    }

}
