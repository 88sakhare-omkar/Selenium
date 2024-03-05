package VtigerCRM;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class ContactsTestNG {

	WebDriverUtil wutil = new WebDriverUtil();
	PropertyFileUtil putil = new PropertyFileUtil();
	ExcelUtil eutil = new ExcelUtil();

	@Test
	public void ContactsTest() throws IOException, InterruptedException {

		// To launch empty browser
		WebDriver driver = new ChromeDriver();

		// To maximize the browser window
		wutil.maximize(driver);

		// To apply implicit wait
		wutil.implicitwait(driver);

		// To read data from property file
		String URL = putil.getDataFromPropertyFile("Url");
		String USERNAME = putil.getDataFromPropertyFile("Username");
		String PASSWORD = putil.getDataFromPropertyFile("Password");

		// To read data from Excel file
		String FIRSTNAME = eutil.getDataFromExcel("Contacts", 0, 1);
		String LASTNAME = eutil.getDataFromExcel("Contacts", 1, 1);
		String GROUP = eutil.getDataFromExcel("Contacts", 2, 1);
		String ORGNAME = eutil.getDataFromExcel("Contacts", 3, 1);

		// To Launch application
		driver.get(URL);

		// To login application

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);

		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

		driver.findElement(By.id("submitButton")).click();

		// To Click on contacts
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// To click on create contacts..(+)

		driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();

		// To enter first name
		driver.findElement(By.name("firstname")).sendKeys(FIRSTNAME);

		// To click on last name

		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// To fail the TestScript
		
		// WebElement checkbox = driver.findElement(By.name("notify_owner"));
		// Assert.assertTrue(checkbox.isSelected());
		
		String actualurl = driver.getCurrentUrl();
		String expectedurl = "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing";
		Assert.assertEquals(actualurl, expectedurl);

		// In assigned to click on group or To click on Group radio button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();

		// In dropdown select Team Selling
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		wutil.HandleDropdown(dropdown, GROUP);

		// Click on Select(+) in Organization Name text field
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();

		// Transfer the driver control from Parent window to Child window
		wutil.switchwindow(driver,
				"http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");

		// To enter Organization Name in search tf
		driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);

		// Click on Search Now button
		driver.findElement(By.name("search")).click();

		// To click on Organization Name
		driver.findElement(By.xpath("//a[text()='Qspider']")).click();

		// To Transfer the control from Child window to Parent window
		wutil.switchwindow(driver,
				"http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");

		// CLICK ON SAVE BUTTON
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

		// To take Screenshot of Contact
		Thread.sleep(2000);
		// wutil.screenshot(driver, "Contact");

		Thread.sleep(2000);
		// Mouse hover on image
		WebElement image = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mousehover(driver, image);

		Thread.sleep(2000);
//	    Click on signout
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}

}
