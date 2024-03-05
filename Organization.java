package VtigerCRM;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.ListenerImplimentation;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

@Listeners(ListenerImplimentation.class)

public class Organization {
	
	PropertyFileUtil putil = new PropertyFileUtil();
	 WebDriverUtil wutil = new  WebDriverUtil();
	 ExcelUtil eutil = new ExcelUtil();
	 JavaUtil jutil = new JavaUtil();
	
	@Test
	public void OrganizationTest() throws IOException, InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		wutil.maximize(driver);
		wutil.implicitwait(driver);
		
	//	TO READ DATA FROM PROPERTY FILE
		String URL = putil.getDataFromPropertyFile("Url");
		
		String USERNAME = putil.getDataFromPropertyFile("Username");
		
		String PASSWORD = putil.getDataFromPropertyFile("Password");
		
	//	To read data from Excel File
		String ORGNAME = eutil.getDataFromExcel("Organizations", 0, 1);
		String GROUP = eutil.getDataFromExcel("Organizations", 1, 1);
		
	//	 to launching the application
		driver.get(URL);
		
	//	adding username
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		
	//	 adding password
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		
	//	 to click on login button
		driver.findElement(By.id("submitButton")).click();		
		
	//	 to click on orgination
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
	//	to click on Create orgination
		driver.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
		
	//	 to enter the Organizations Name
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+jutil.getRandomNumber());
		
	//	In assigned to click on group
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
	//	In the dropdown select Support Group
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		
		wutil.HandleDropdown(dropdown,GROUP);
		
	//	CLICK ON SAVE BUTTON
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
		
		Thread.sleep(2000);
	//	Mouse hover on image
		WebElement image = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));		
		wutil.mousehover(driver, image);
		
		Thread.sleep(2000);
	//	Click on signout
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();           
		
		
	}

}
