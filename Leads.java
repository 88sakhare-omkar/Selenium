package VtigerCRM;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class Leads {

	WebDriverUtil wutil = new WebDriverUtil();
	PropertyFileUtil putil = new PropertyFileUtil();
	ExcelUtil eutil = new ExcelUtil();
	JavaUtil jutil = new JavaUtil();

	@Test
	public void contacttest() throws IOException, InterruptedException {

		WebDriver driver = new ChromeDriver();
		wutil.maximize(driver);
		wutil.implicitwait(driver);

		String URL = putil.getDataFromPropertyFile("Url");
		String USERNAME = putil.getDataFromPropertyFile("username");
		String PASSWORD = putil.getDataFromPropertyFile("Password");

		driver.get(URL);

		// adding username
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);

		// adding password
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

		// to click on login button
		driver.findElement(By.id("submitButton")).click();

		// Click on contacts
		driver.findElement(By.xpath("//a[text()='Leads']")).click();

		// To click on create contacts..(+)
		driver.findElement(By.cssSelector("img[alt='Create Lead...']")).click();

		// To read data from Excel File
		Thread.sleep(2000);
		String LEADSFIRST = eutil.getDataFromExcel("Leads", 0, 1);
		String LEADSLAST = eutil.getDataFromExcel("Leads", 1, 1);
		String LEADDROP = eutil.getDataFromExcel("Leads", 3, 1);
		String LEADSCOMPNEY = eutil.getDataFromExcel("Leads", 2, 1);

		// to enter data from excel
		Thread.sleep(2000);
		driver.findElement(By.name("firstname")).sendKeys(LEADSFIRST + jutil.getRandomNumber());

		Thread.sleep(2000);
		driver.findElement(By.name("lastname")).sendKeys(LEADSLAST);

		Thread.sleep(2000);
		driver.findElement(By.name("company")).sendKeys(LEADSCOMPNEY);

		// In assigned to click on group
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();

		// In the dropdown select Support Group WebElement condrop
		Thread.sleep(2000);
		driver.findElement(By.name("assigned_group_id"));

		// manage dropdown
		Thread.sleep(2000);
		WebElement condrop = driver.findElement(By.name("assigned_group_id"));
		wutil.HandleDropdowncontact(condrop, LEADDROP);

		// cLICK ON SAVE BUTTON
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

		// Mouse hover on image WebElement image
		Thread.sleep(2000);
		WebElement image = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mousehover(driver, image);

		// Click on signout
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}
}