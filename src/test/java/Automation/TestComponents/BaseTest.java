package Automation.TestComponents;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException 
	{
		
		Properties prop =new Properties();
		FileInputStream fis= new FileInputStream( System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();	
	    }
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
		
	}
		
	    @BeforeMethod
		public LandingPage launchApplication() throws IOException, Exception
		{
			driver=initializeDriver();
			landingPage=new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		}


}

