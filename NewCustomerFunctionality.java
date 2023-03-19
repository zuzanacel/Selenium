import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

public class NewCustomerFunctionality {

	
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
		driver.quit();
		//driver.close();
	}
	
	public void loginHappyPath() throws InterruptedException {
		
		//enter user id mngr478324
		driver.findElement(By.name("uid")).sendKeys("mngr483896");
				
		//enter password yjAbaga
		driver.findElement(By.name("password")).sendKeys("azyjYry");
				
		//click on submit
		driver.findElement(By.name("btnLogin")).click();
	}
	
	@Test
	@Order(5)
	@DisplayName("Check results on entering a valid information for all fields")
	public void TC0010() throws InterruptedException {
		
		//open the website
		driver.get("https://demo.guru99.com/v4/index.php");
				
		// wait between steps
		Thread.sleep(2000);
				
		// close the iframe privacy pollicy
		driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
		Thread.sleep(2000);
		
		//precondition- login function and call this function
		loginHappyPath();
		
		//maximize the screen
		driver.manage().window().maximize();
		
		//Test steps:
		
		//click on new customer
		driver.findElement(By.linkText("New Customer")).click();
		driver.get("https://demo.guru99.com/V4/manager/addcustomerpage.php");
		
		//enter customer name, gender, date of birth, address... plus click on submit
		//Generating a random number
		Random random = new Random();
		int randomNumber = random.nextInt(10000);  //12345
		String email = "adam."+randomNumber+"@guru.ie";
		System.out.println(email);
		
		driver.findElement(By.name("name")).sendKeys("Adam");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]")).click();
		driver.findElement(By.id("dob")).sendKeys("01/01/1991");
		driver.findElement(By.name("addr")).sendKeys("47 Testing Road");
		driver.findElement(By.name("city")).sendKeys("Dublin");
		driver.findElement(By.name("state")).sendKeys("Dublin");
		driver.findElement(By.name("pinno")).sendKeys("123456");
		driver.findElement(By.name("telephoneno")).sendKeys("1234567");
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys("1234567");
		
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]")).click();
		
		//check the success message
		String expectedResultsCustomerRegistered = "Customer Registered Successfully!!!";
		String actualResultsCustomerRegistered = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(1) > td > p")).getText();
		assertTrue(expectedResultsCustomerRegistered.equals(actualResultsCustomerRegistered));
		
		//check the name
		String expectedResultsCustomerName = "Adam";
		String actualResultsCustomerName = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(5) > td:nth-child(2)")).getText();
		assertTrue(expectedResultsCustomerName.equals(actualResultsCustomerName));
		
		//check the gender
		String expectedResultsCustomerGender = "male";
		String actualResultsCustomerGender = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(6) > td:nth-child(2)")).getText();
		assertTrue(expectedResultsCustomerGender.equals(actualResultsCustomerGender));
		
		//check the birthdate
		String expectedResultsCustomerBD = "1991-01-01";
		String actualResultsCustomerBD = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(7) > td:nth-child(2)")).getText();
		assertTrue(expectedResultsCustomerBD.equals(actualResultsCustomerBD));

		//check the address
		String expectedResultsCustomerAddress = "47 Testing Road";
		String actualResultsCustomerAddress = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(8) > td:nth-child(2)")).getText();
		assertTrue(expectedResultsCustomerAddress.equals(actualResultsCustomerAddress));
		
		//check the city
		String expectedResultsCustomerCity = "Dublin";
		String actualResultsCustomerCity = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(9) > td:nth-child(2)")).getText();
		assertTrue(expectedResultsCustomerCity.equals(actualResultsCustomerCity));

		//check the state
		String expectedResultsCustomerState = "Dublin";
		String actualResultsCustomerState = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(10) > td:nth-child(2)")).getText();
		assertTrue(expectedResultsCustomerState.equals(actualResultsCustomerState));
		
		//check the pin
		String expectedResultsCustomerPin = "123456";
		String actualResultsCustomerPin = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(11) > td:nth-child(2)")).getText();
		assertTrue(expectedResultsCustomerPin.equals(actualResultsCustomerPin));

		//check the mobile
		String expectedResultsCustomerMobile = "1234567";
		String actualResultsCustomerMobile = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(12) > td:nth-child(2)")).getText();
		assertTrue(expectedResultsCustomerMobile.equals(actualResultsCustomerMobile));
		
		//check the email
		String actualResultsCustomerEmail = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(13) > td:nth-child(2)")).getText();
		assertEquals(email, actualResultsCustomerEmail);
		
		//check if customer ID created and write to console
		String actualResultsCustomerID = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
		if(actualResultsCustomerID != null) {
			System.out.println(actualResultsCustomerID);}
	}
	
	@Test
	@Order(6)
	@DisplayName("Check results on leaving blank all fields and try to submit the form. ")
	public void TC0011() throws InterruptedException {
		
		//open the website
		driver.get("https://demo.guru99.com/v4/index.php");
				
		// wait between steps
		Thread.sleep(2000);
		
		//precondition- login function and call this function
		loginHappyPath();
		
		//maximize the screen
		driver.manage().window().maximize();
		
		//Test steps:
		
		//click on new customer
		driver.findElement(By.linkText("New Customer")).click();
		driver.get("https://demo.guru99.com/V4/manager/addcustomerpage.php");
		
		//Fields we leave blank
		
		//Click on Submit button
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]")).click();
		
		// popup will be visible "please fill all fields", compare results
		String expectedResultsCustomerNoData = "please fill all fields";
		String actualResultsCustomerNoData = driver.switchTo().alert().getText();
		
		assertTrue(expectedResultsCustomerNoData.equals(actualResultsCustomerNoData));
		
	}	
}
