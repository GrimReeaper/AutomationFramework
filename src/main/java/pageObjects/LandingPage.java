package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement userPassword;
		
		@FindBy(id="login")
		WebElement submit;
		
		
		public ProductCatalog loginApplication(String email, String password)
		{
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			submit.click();
			ProductCatalog productCatalog=new ProductCatalog(driver);
            return productCatalog;
		}
		
		public void goTo() throws AWTException
		{
			driver.get("https://rahulshettyacademy.com/client");
			driver.manage().window().maximize();
			
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);

			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
		}

}
