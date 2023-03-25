import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogoutFunctionality {
	
	
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
	
	@AfterAll
	public static void afterAll() {
		// block of code to be executed before each test
		//driver.quit();
		driver.close();
	}
	
	public void loginHappyPath() throws InterruptedException {
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
		
		//maximize the screen
		driver.manage().window().maximize();
	}
	
	@Test
	@DisplayName("Check results on clicking on the Log Out button as Manager")
	public void TC009() throws InterruptedException {
		
		//precondition- login function and call this function
		loginHappyPath();
		
		//Test steps:
		
		//click on new customer
		driver.findElement(By.linkText("Log out")).click();
		
		//check the success message
		String expectedResultsLogout = "You Have Succesfully Logged Out!!";
		String actualResultsLogout = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		assertTrue(expectedResultsLogout.equals(actualResultsLogout));

	}
	}
