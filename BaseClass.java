package CommonUtils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	public WebDriver driver;
	
	 WebDriverUtil wutil = new  WebDriverUtil();
	 PropertyFileUtil putil = new PropertyFileUtil();
	
	@BeforeSuite
	public void BS() {
		
		System.out.println("Connect To Data Base");
		
	}
	
	@BeforeClass
	public void BC() throws IOException {
		
	//	@BeforeClass is used to 
		String URL = putil.getDataFromPropertyFile("Url");
		WebDriver driver = new ChromeDriver();
		wutil.maximize(driver);
		wutil.implicitwait(driver);
		
		driver.get(URL);
		
	}
	
	@BeforeMethod
	public void BM() throws IOException {
	//	@BeforeMethod is used to Login to the application
		
        String USERNAME = putil.getDataFromPropertyFile("Username");
		
		String PASSWORD = putil.getDataFromPropertyFile("Password");
		
           // Login To Application
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);					
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);					
			driver.findElement(By.id("submitButton")).click();				
		
	}
	
	@AfterMethod
	public void AM() {
	//	@AfterMethod is used to signout from the application
		
	//	Mouse Hover on image
		WebElement image = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));		
		wutil.mousehover(driver, image);	
		
	//	Click on Sign Out
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();         
		
	}
	
	@AfterClass
	public void AC() {
	//	@AfterClass is used to close the browser
		
		driver.quit();
		
	}
	
	@AfterSuite
	public void AS() {
		System.out.println("Discconect From Data Base");
		
	}

}
