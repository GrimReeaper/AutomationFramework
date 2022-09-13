package pageObjects;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;

	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
		@FindBy(css=".cart h3")
		private List<WebElement> productTitles;
		
		@FindBy(css=".totalRow button")
		WebElement checkOutEle;
		
		public Boolean verifyProductDisplay(String ProductName)
		{
			Boolean match=productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(ProductName));
			return match;

		}
		
		public CheckOutPage goToCheckOut()
		{
			checkOutEle.click();
			CheckOutPage checkOutPage=new CheckOutPage(driver);
			return checkOutPage;
		}
		

		
		

}
