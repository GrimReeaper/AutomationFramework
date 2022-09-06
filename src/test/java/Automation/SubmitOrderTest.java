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
import pageObjects.LandingPage;
import pageObjects.ProductCatalog;

public class SubmitOrderTest {

	public static void main(String[] args) throws AWTException {

		String ProductName="ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("shivam1@gmail.com", "Grimreaper1@");
		
		ProductCatalog productcatalog=new ProductCatalog(driver);
		List<WebElement> products=productcatalog.getProductList();
		productcatalog.addProductToCart(ProductName);
		productcatalog.clickOnCartBtn();
				
		
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cart h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a =new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();	
		driver.findElement(By.cssSelector(".action__submit")).click();

		
		String confirmationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		

		



		
		
	}

}
