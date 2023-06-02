package brwsrInvoke;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase5 {

	private WebDriver driver;
	private String URL="http://live.techpanda.org/";
	public String firstName="BERRY";
	public String lastName="BERRYEIGHT";
	//
	@BeforeTest
	public void Invoke() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		
	}
	
	@Test
	public void TestScript5() throws InterruptedException {
		driver.get(URL);
		
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[text()='My Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@id='firstname']")).clear();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).clear();
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).clear();
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("Berry@abc8.com.au");
		driver.findElement(By.xpath("//input[@id='password']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Panda@2023");
		driver.findElement(By.xpath("//input[@id='confirmation']")).clear();
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("Panda@2023");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Thread.sleep(2000);
		
		String vWelcome=("WELCOME, "+firstName+" "+lastName+"!");
		String check=driver.findElement(By.xpath("//div[@class='store-language-container']/following::p[@class='welcome-msg']")).getText();
		System.out.println("check= "+check);
		//
		try {
			Assert.assertEquals(vWelcome, check);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		driver.findElement(By.xpath("//a[text()='TV']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[@id='old-price-4']/following::a[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//textarea[@id='email_address']")).clear();
		driver.findElement(By.xpath("//textarea[@id='email_address']")).sendKeys("support@guru99.com");
		driver.findElement(By.xpath("//textarea[@id='message']")).clear();
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Just Check");
		driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
		Thread.sleep(2000);
		
		String Vwishlist ="Your Wishlist has been shared.";
		String Awishlist= driver.findElement(By.xpath("//div[@class='my-wishlist']/div[1]/following::span[1]")).getText();
		try {
			Assert.assertEquals(Vwishlist, Awishlist);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		Thread.sleep(1500);
	}
	
	@AfterTest
	public void Quit() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	//	driver.close();
	}
}
