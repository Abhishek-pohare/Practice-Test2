package brwsrInvoke;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo6 {
	private WebDriver driver;
	private String baseURL="http://live.techpanda.org/";
	public String FirstName="BERRY";
	public String LastName="BERRYEIGHT";
	public String vEmail="Berry@abc8.com.au";
	public String vPassword="Panda@2023";
	
	@BeforeTest
	private void OPEN() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	private void TC6() throws InterruptedException {
		driver.get(baseURL);
		Thread.sleep(3000);
		// Click on MY Account
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
		Thread.sleep(1000);
		//1. Login
		driver.findElement(By.xpath("//input[@name='login[username]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys(vEmail);
		driver.findElement(By.xpath("//input[@name='login[password]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys(vPassword);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(1500);
		//2. click on TV
		driver.findElement(By.xpath("//div[@id='header-nav']/nav/ol/li[2]/a")).click();
		//3. click on Add to Cart
	//	driver.findElement(By.xpath("//div[@class='category-products']/ul/li/div[1]/div[3]/ul/li/a")).click();
		//4. Add to wishlist
		driver.findElement(By.xpath("//span[@id='old-price-4'] /following::a[2]")).click();
		Thread.sleep(2000);
		//
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a/span[1]")).click();
		Thread.sleep(1500);
		//
		driver.findElement(By.xpath("//div[@id='header-account']/div/ul/li[5]/a")).click();
	}
	
	@AfterTest
	private void Close() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
