import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class NewCustomerFunctionality {
	
	@BeforeAll
	public static void beforeALL() {
		//-------Setup enviroment-----------
		
		//set enviroment variable
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		
		//webdrivermanager will setup chrome browser
		WebDriverManager.chromedriver().setup();
		
		//initalize virtual browser
		TestData.driver = new ChromeDriver();
			}
	
	@AfterAll
	public static void afterAll() {
		// block of code to be executed before each test
		TestData.driver.quit();
		//driver.close();
	}
	
	@Test
	@DisplayName("Check results on entering a valid information for all fields")
	public void TC0011() throws InterruptedException {
		
		//open the website
		TestData.driver.get("https://demo.guru99.com/v4/index.php");
				
		// wait between steps
		Thread.sleep(2000);
				
		// close the iframe privacy pollicy
		TestData.driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
		Thread.sleep(2000);
		
		//precondition- login function and call this function
		TestData.loginHappyPath();
		
		//maximize the screen
		TestData.driver.manage().window().maximize();
		
		//Test steps:
		
		//click on new customer
		TestData.driver.findElement(By.linkText("New Customer")).click();
		TestData.driver.get("https://demo.guru99.com/V4/manager/addcustomerpage.php");
		
		//enter customer name, gender, date of birth, address... plus click on submit
		
		TestData.driver.findElement(By.name("name")).sendKeys(TestData.customerName);
		TestData.driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]")).click();
		TestData.driver.findElement(By.id("dob")).sendKeys(TestData.customerBD);
		TestData.driver.findElement(By.name("addr")).sendKeys(TestData.customerAddress);
		TestData.driver.findElement(By.name("city")).sendKeys(TestData.customerCity);
		TestData.driver.findElement(By.name("state")).sendKeys(TestData.customerState);
		TestData.driver.findElement(By.name("pinno")).sendKeys(TestData.customerPin);
		TestData.driver.findElement(By.name("telephoneno")).sendKeys(TestData.customerMobile);
		TestData.driver.findElement(By.name("emailid")).sendKeys(TestData.email);
		TestData.driver.findElement(By.name("password")).sendKeys("1234567");
		
		
		TestData.driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]")).click();
		
		//check the success message
		String expectedResultsCustomerRegistered = "Customer Registered Successfully!!!";
		String actualResultsCustomerRegistered = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(1) > td > p")).getText();
		assertTrue(expectedResultsCustomerRegistered.equals(actualResultsCustomerRegistered));
		
		//check the name
		String actualResultsCustomerName = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(5) > td:nth-child(2)")).getText();
		assertTrue(TestData.customerName.equals(actualResultsCustomerName));
		
		//check the gender
		String actualResultsCustomerGender = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(6) > td:nth-child(2)")).getText();
		assertTrue(TestData.customerGender.equals(actualResultsCustomerGender));
		
		//check the birthdate
		String actualResultsCustomerBD = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(7) > td:nth-child(2)")).getText();
		assertTrue(TestData.customerBD2.equals(actualResultsCustomerBD));

		//check the address
		String actualResultsCustomerAddress = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(8) > td:nth-child(2)")).getText();
		assertTrue(TestData.customerAddress.equals(actualResultsCustomerAddress));
		
		//check the city
		String actualResultsCustomerCity = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(9) > td:nth-child(2)")).getText();
		assertTrue(TestData.customerCity.equals(actualResultsCustomerCity));

		//check the state
		String actualResultsCustomerState = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(10) > td:nth-child(2)")).getText();
		assertTrue(TestData.customerState.equals(actualResultsCustomerState));
		
		//check the pin
		String actualResultsCustomerPin = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(11) > td:nth-child(2)")).getText();
		assertTrue(TestData.customerPin.equals(actualResultsCustomerPin));

		//check the mobile
		String actualResultsCustomerMobile = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(12) > td:nth-child(2)")).getText();
		assertTrue(TestData.customerMobile.equals(actualResultsCustomerMobile));
		
		//check the email
		String actualResultsCustomerEmail = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(13) > td:nth-child(2)")).getText();
		assertEquals(TestData.email, actualResultsCustomerEmail);
		
		//check if customer ID created and write to console
		String actualResultsCustomerID = TestData.driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
		if(actualResultsCustomerID != null) {
			System.out.println(actualResultsCustomerID);}
	}
	
	@Test
	@DisplayName("Check results on leaving blank all fields and try to submit the form. ")
	public void TC0012() throws InterruptedException {
		
		//open the website
		TestData.driver.get("https://demo.guru99.com/v4/index.php");
				
		// wait between steps
		Thread.sleep(2000);
		
		//precondition- login function and call this function
		TestData.loginHappyPath();
		
		//maximize the screen
		TestData.driver.manage().window().maximize();
		
		//Test steps:
		
		//click on new customer
		TestData.driver.findElement(By.linkText("New Customer")).click();
		TestData.driver.get("https://demo.guru99.com/V4/manager/addcustomerpage.php");
		
		//Fields we leave blank
		
		//Click on Submit button
		TestData.driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]")).click();
		
		// popup will be visible "please fill all fields", compare results
		String expectedResultsCustomerNoData = "please fill all fields";
		String actualResultsCustomerNoData = TestData.driver.switchTo().alert().getText();
		
		assertTrue(expectedResultsCustomerNoData.equals(actualResultsCustomerNoData));
		
	}	
}
