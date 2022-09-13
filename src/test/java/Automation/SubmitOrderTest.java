package Automation;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
     
    @Test
	public  void SubmitOrder() throws Exception {

		String ProductName="ZARA COAT 3";
		String CountryName="india";
		
		LandingPage landingPage=launchApplication();
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
		
		
		driver.close();
		

		



		
		
	}

}
