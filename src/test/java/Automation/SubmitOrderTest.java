package Automation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	String ProductName="ZARA COAT 3";

     
    @Test(dataProvider="getData", groups= {"Purchase"})
	public  void SubmitOrder(HashMap<String, String> input) throws Exception {

		String CountryName="india";
		
		ProductCatalog productCatalog=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalog.goToCartPage();
		
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
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
    
    public String getScreenshot(String testCaseName) throws IOException
    {
    	TakesScreenshot ts= (TakesScreenshot) driver;
    	File source=ts.getScreenshotAs(OutputType.FILE);
    	File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
    	FileUtils.copyFile(source, file);  
    	return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }
    
    
    @DataProvider
    public Object[][] getData() throws IOException
    {

    	
    	List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\PurchaseOrder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    	
    	
    }

}
