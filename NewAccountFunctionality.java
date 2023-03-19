import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class NewAccountFunctionality {

		//Declaration of the object webdriver
		public static WebDriver driver = null;
		
		
		@BeforeAll
		public static void beforeALL() throws InterruptedException {
			//---------------------------
			//Setup Environment  --------
			//---------------------------
			//Set environment variable
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			
			//WebDriverManager will setup the chrome browser 
			WebDriverManager.chromedriver().setup();
			
			//Initialize our virtual Browser
			driver = new ChromeDriver();
			
			//loginHappyPath();
		}
		
		@AfterAll
		public static void afterAll() {
			// block of code to be executed before each test
			//driver.quit();
			driver.close();
		}
		
		public static void loginHappyPath() throws InterruptedException {
			
			//Enter UserID
			driver.findElement(By.name("uid")).sendKeys("mngr483896");
			
			//Enter Password
			driver.findElement(By.name("password")).sendKeys("azyjYry");
			
			//Click on Submit
			driver.findElement(By.name("btnLogin")).click();
			
			//maximize the screen
			driver.manage().window().maximize();
		}
		
		@Test
		@Order(7)
		@DisplayName("Check results on entering a valid information for all fields  (Account type -Savings ) and submit the form")
		public void TC0034() throws InterruptedException {
			
			//Open the website
			driver.get("https://demo.guru99.com/v4/index.php");
			Thread.sleep(2000);
			
			//Close the iframe - Privacy Police
			driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
			Thread.sleep(2000);
			
			//Pre condition = Login Happy Path
			loginHappyPath();
			
			//Click on New Account
			driver.findElement(By.linkText("New Account"));
			driver.get("https://demo.guru99.com/v4/manager/addAccount.php");
			
			//Enter Customer ID
			driver.findElement(By.name("cusid")).sendKeys("36981");
			
			//Select Savings Account
			//Find the list
			WebElement accountTypeList = driver.findElement(By.name("selaccount"));
			//Create Select object with a list we found
			Select select = new Select(accountTypeList);
			//select the option you want
			String AccountType = "Savings";
			select.selectByVisibleText(AccountType);
			
			//fill the deposit
			String deposit = "600";
			driver.findElement(By.name("inideposit")).sendKeys(deposit);
			
			//Click submit
			driver.findElement(By.name("button2")).click();
			
			/////////Results///////
			
			//check the success message
			String expectedResultsAccountRegistered = "Account Generated Successfully!!!";
			String actualResultsAccountRegistered = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(1) > td > p")).getText();
			assertTrue(expectedResultsAccountRegistered.equals(actualResultsAccountRegistered));
			
			//check the customer ID
			String expectedResultsCustomerID = "36981";
			String actualResultsCustomerID= driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(5) > td:nth-child(2)")).getText();
			assertTrue(expectedResultsCustomerID.equals(actualResultsCustomerID));
			
			//check the customer name
			String expectedResultsCustomerName = "zuzka";
			String actualResultsCustomerName = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(6) > td:nth-child(2)")).getText();
			assertTrue(expectedResultsCustomerName.equals(actualResultsCustomerName));		

			//check the customer email
			String expectedResultsCustomerEmail = "zuzka@zuzka.cz";
			String actualResultsCustomerEmail = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(7) > td:nth-child(2)")).getText();
			assertTrue(expectedResultsCustomerEmail.equals(actualResultsCustomerEmail));
			
			//check the account type
			String actualResultsAccountType = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(8) > td:nth-child(2)")).getText();
			assertTrue(AccountType.equals(actualResultsAccountType));				

			//check the date of opening
			String TimeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String actualResultsDateOpening = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(9) > td:nth-child(2)")).getText();
			assertTrue(TimeStamp.equals(actualResultsDateOpening));
			
			//check the current amount
			String actualResultsDeposit = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(10) > td:nth-child(2)")).getText();
			assertTrue(deposit.equals(actualResultsDeposit));	
			
			//check if account ID created and write to console
			String actualResultsAccountID = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
			if(actualResultsAccountID != null) {
				System.out.println(actualResultsAccountID);}
		}
		
		@Test
		@Order(8)
		@DisplayName("Check results on entering a valid information for all fields  (Account type -Current ) and submit the form")
		public void TC0035() throws InterruptedException {
			
			//Open the website
			driver.get("https://demo.guru99.com/v4/index.php");
			Thread.sleep(2000);
			
			//Pre condition = Login Happy Path
			loginHappyPath();
			
			//Click on New Account
			driver.findElement(By.linkText("New Account"));
			driver.get("https://demo.guru99.com/v4/manager/addAccount.php");
			
			//Enter Customer ID
			driver.findElement(By.name("cusid")).sendKeys("36981");
			//Select Current Account
			//Find the list
			WebElement accountTypeList = driver.findElement(By.name("selaccount"));
			//Create Select object with a list we found
			Select select = new Select(accountTypeList);
			//select the option you want
			String AccountType = "Current";
			select.selectByVisibleText(AccountType);
			
			//fill the deposit
			String deposit = "600";
			driver.findElement(By.name("inideposit")).sendKeys(deposit);
			
			//Click submit
			driver.findElement(By.name("button2")).click();
			Thread.sleep(2000);
			
			/////////Results///////
			
			//check the success message
			String expectedResultsAccountRegistered = "Account Generated Successfully!!!";
			String actualResultsAccountRegistered = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(1) > td > p")).getText();
			assertTrue(expectedResultsAccountRegistered.equals(actualResultsAccountRegistered));
			
			//check the customer ID
			String expectedResultsCustomerID = "36981";
			String actualResultsCustomerID= driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(5) > td:nth-child(2)")).getText();
			assertTrue(expectedResultsCustomerID.equals(actualResultsCustomerID));
			
			//check the customer name
			String expectedResultsCustomerName = "zuzka";
			String actualResultsCustomerName = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(6) > td:nth-child(2)")).getText();
			assertTrue(expectedResultsCustomerName.equals(actualResultsCustomerName));		

			//check the customer email
			String expectedResultsCustomerEmail = "zuzka@zuzka.cz";
			String actualResultsCustomerEmail = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(7) > td:nth-child(2)")).getText();
			assertTrue(expectedResultsCustomerEmail.equals(actualResultsCustomerEmail));
			
			//check the account type
			String actualResultsAccountType = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(8) > td:nth-child(2)")).getText();
			assertTrue(AccountType.equals(actualResultsAccountType));				

			//check the date of opening
			String TimeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String actualResultsDateOpening = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(9) > td:nth-child(2)")).getText();
			assertTrue(TimeStamp.equals(actualResultsDateOpening));
			
			//check the current amount
			String actualResultsDeposit = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(10) > td:nth-child(2)")).getText();
			assertTrue(deposit.equals(actualResultsDeposit));	
			
			//check if account ID created and write to console
			String actualResultsAccountID = driver.findElement(By.cssSelector("#account > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
			if(actualResultsAccountID != null) {
				System.out.println(actualResultsAccountID);}
		}	
		
	}
