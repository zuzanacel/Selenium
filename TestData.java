import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestData {
	
	// declaration of the object webdriver
	public static WebDriver driver = null; 
	
	public static void loginHappyPath() throws InterruptedException {
		
		//enter user id mngr478324
		driver.findElement(By.name("uid")).sendKeys("mngr483896");
				
		//enter password yjAbaga
		driver.findElement(By.name("password")).sendKeys("azyjYry");
				
		//click on submit
		driver.findElement(By.name("btnLogin")).click();
	}
	
	/// Test data/////
	
	public static String customerName = "Zuzka";
	public static String customerGender = "male";
	public static String customerBD = "01-01-1991";
	public static String customerBD2 = "1991-01-01";
	public static String customerAddress = "47 Testing Road";
	public static String customerCity = "Dublin";
	public static String customerState = "Dublin";
	public static String customerPin = "123456";
	public static String customerMobile = "1234567";
	
	//Generating a random number
	static Random random = new Random();
	static int randomNumber = random.nextInt(10000);  //12345
	static String email = "adam."+randomNumber+"@guru.ie";
	

}
