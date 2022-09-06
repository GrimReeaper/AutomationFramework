package Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalog;

public class SubmitOrderTest {

	public static void main(String[] args) throws AWTException {

		String ProductName="ZARA COAT 3";
		String CountryName="india";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
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
