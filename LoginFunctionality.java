import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Test scenario Login")
public class LoginFunctionality {
	
	// declaration of the object webdriver
	public static WebDriver driver = null; 
	
	@BeforeAll
	public static void beforeALL() {
		//-------Setup enviroment-----------
		
		//set enviroment variable
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		
		//webdrivermanager will setup chrome browser
		WebDriverManager.chromedriver().setup();
		
		//initalize virtual browser
		driver = new ChromeDriver();
			}
	
//	@BeforeEach
//	public static void beforeEach() {
//		// block of code to be executed before each test
//	}
	
	@AfterAll
	public static void afterAll() {
		// block of code to be executed before each test
		driver.quit();
		//driver.close();
	}
	
//	@AfterEach
//	public static void afterEach() {
//		// block of code to be executed after each test
//	}
	
	
	@Test
	@Order(1)
	@DisplayName("Check results on entering valid User Id & Password for manager account")
	public void TC001() throws InterruptedException {
			
		//------------------ Test Steps-------
		
		//open the website
		driver.get("https://demo.guru99.com/v4/index.php");
		
		// wait between steps
		Thread.sleep(2000);
		
		// close the iframe privacy pollicy
		driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
		Thread.sleep(2000);
		
		//enter user id mngr478324
		driver.findElement(By.name("uid")).sendKeys("mngr483896");
		
		//enter password yjAbaga
		driver.findElement(By.name("password")).sendKeys("azyjYry");
		
		//click on submit
		driver.findElement(By.name("btnLogin")).click();
		Thread.sleep(2000);
		
		
		//-------CHeck the result------------------
		
		//expected: Check the welcome message Welcome To Manager's Page of Guru99 Bank
		String expectedResultsLoginManager = "Welcome To Manager's Page of Guru99 Bank";
		String actualResultsLoginManager = driver.findElement(By.cssSelector("body > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > marquee")).getText();
		
		assertEquals(expectedResultsLoginManager,actualResultsLoginManager);
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Check results on entering valid User Id & Password for customer account")
	public void TC002() throws InterruptedException {
		//------------------ Test Steps-------
		
		//open the website
		driver.get("https://demo.guru99.com/v4/index.php");
		
		//enter user id mngr478324
		driver.findElement(By.name("uid")).sendKeys("36981");
		
		//enter password yjAbaga
		driver.findElement(By.name("password")).sendKeys("123456");
		
		//click on submit
		driver.findElement(By.name("btnLogin")).click();
		Thread.sleep(2000);
		
		
		//-------CHeck the result------------------
		
		//expected: Check the welcome message Welcome To Customer's Page of Guru99 Bank
		String expectedResultsLoginCustomer = "Welcome To Customer's Page of Guru99 Bank";
		String actualResultsLoginCustomer = driver.findElement(By.cssSelector("body > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > marquee")).getText();
		
		assertEquals(expectedResultsLoginCustomer,actualResultsLoginCustomer);
		
		
		
	}
	
	@Test
	@Order(3)
	@DisplayName("Check response when a User ID and Password are Empty & Login Button is pressed")
	public void TC003() throws InterruptedException {
			
		//------------------ Test Steps-------
		
		//open the website
		driver.get("https://demo.guru99.com/v4/index.php");
		
//		// close the iframe privacy pollicy
//		Thread.sleep(2000);
//		driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
//		Thread.sleep(2000);
		
		//leave userid and password empty//
		
		//click on submit
		driver.findElement(By.name("btnLogin")).click();
		
		// popup will be visible "User or Password is not valid"
		String expectedResultsNoLogin = "User or Password is not valid";
		String actualResultsNoLogin = driver.switchTo().alert().getText();
		
		//write result to the console
		System.out.println(actualResultsNoLogin);
		
		//compare results
		assertEquals(expectedResultsNoLogin,actualResultsNoLogin); 
		//assertTrue(expectedResultsNoLogin.equals(actualResultsNoLogin));
				
	}
}
