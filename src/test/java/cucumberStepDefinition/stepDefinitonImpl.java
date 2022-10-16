package cucumberStepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Automation.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalog;

public class stepDefinitonImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CheckOutPage checkOutPage;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException, Exception
	{
		landingPage=launchApplication();
	}


	@Given("^Logged In with username (.+) and password (.+)$")
	public void Logged_In_with_username_and_password(String username, String password)
	{
		 productCatalog=landingPage.loginApplication(username,password);

	}
	
	@When ("^I add (.+) to the cart$")
	public void I_add_to_the_cart(String productName)
	{
		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	
	@And ("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		
        
		cartPage=productCatalog.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage=checkOutPage.submitOrder();
		
	}
	
    @Then("{string} confirmation message is displayed")
    public void confirmation_message_is_displayed(String string)
    {
    	 String confirmMessage=confirmationPage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }
    
    @Then("^\"([^\"]*)\" message id displayed.$")
    public void something_message_id_displayed(String strArg1) throws Throwable {
    	
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
    }



}
