package pageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	
		@FindBy(css=".mb-3")
		List<WebElement> products;
		
		@FindBy(css=".ng-animating")
		WebElement spinner;
		

		
		By productsBy=By.cssSelector(".mb-3");
		By addToCart=By.cssSelector(".card-body button:last-of-type");
		By toastContainer=By.cssSelector("#toast-container");

		
		
		public List<WebElement> getProductList()
		{
			waitForElementToAppear(productsBy);
		    return products;
		}
		
		public WebElement getProductByName(String ProductName)
		{
			WebElement prod=getProductList().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
			return prod;
		}
		
		public void addProductToCart(String productName)
		{
			WebElement prod=getProductByName(productName);
			prod.findElement(addToCart).click();
			waitForElementToAppear(toastContainer);
			waitForElementToDisAppear(spinner);
		

		}
		

}
