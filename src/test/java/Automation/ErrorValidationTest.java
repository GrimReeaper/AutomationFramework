package Automation;



import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import pageObjects.CartPage;
import pageObjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest {
     
    @Test(groups= {"ErrorHandling"})
	public  void LogInErrorValidation() throws Exception {
		
		landingPage.loginApplication("shivam1@gmail.com", "Grimreaper1!");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		
    }	

    
    
    @Test
   	public  void ProductErrorValidation() throws Exception {

   		String ProductName="ZARA COAT 3";
   		
   		ProductCatalog productCatalog=landingPage.loginApplication("shivam2@gmail.com", "Grimreaper1@");
   		
   		List<WebElement> products=productCatalog.getProductList();
   		productCatalog.addProductToCart(ProductName);
   		CartPage cartPage=productCatalog.goToCartPage();
   		
   		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
   		Assert.assertFalse(match);
   		
   		
   		
   	}

    
    
}
