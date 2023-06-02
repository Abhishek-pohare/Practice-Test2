package brwsrInvoke;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TESTCASE7_B {
	private WebDriver driver;
	private String baseURL="http://live.techpanda.org/";
	public String FirstName="BERRY";
	public String LastName="BERRYEIGHT";
	public String vEmail="Berry@abc8.com.au";
	public String vPassword="Panda@2023";
	
	@BeforeTest
	private void Open() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@Test
	private void TESTscript7_B() throws InterruptedException {
		driver.get(baseURL);
		Thread.sleep(2000);
		//click on MY Account
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
        Thread.sleep(2000);
        //switch window
        for(String handle: driver.getWindowHandles()) {
	      	driver.switchTo().window(handle);
	      }
        //Login Using credentials
        driver.findElement(By.xpath("//input[@name='login[username]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys(vEmail);
		driver.findElement(By.xpath("//input[@name='login[password]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys(vPassword);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(2000);
		//click on My Orders.
	//	driver.findElement(By.xpath("//div[@class='block block-account']/div[2]/ul/li[4]/a")).click();
	//	Thread.sleep(2000);		
		//Click on View Order
	//	driver.findElement(By.xpath("//table/colgroup/following::td[6]/span/a[1]")).click();
	//	Thread.sleep(1500);
		
		try {
			Assert.assertEquals("RECENT ORDERS",driver.findElement(By.xpath("//div[@class='box-account box-recent']/div/h2")).getText());
			System.out.println("RECENT ORDER IS confirmed: ");
		}
		catch(Exception e){
			System.out.println("RECENT ORDER is failed to display on Page: "+e);
		}
		
		try {
			Assert.assertEquals("Pending", driver.findElement(By.xpath("//table/colgroup/following::tbody/tr/td[5]/em")).getText());
			System.out.println("Status of Pending is correctly displayed");
		}
		catch(Exception e1) {
			System.out.println("Status of Pending is failed "+e1);
		}
		
		//click on Print
		driver.findElement(By.xpath("//table/colgroup/following::td[6]/span/a[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='page-title title-buttons']/a[2]")).click();
		Thread.sleep(2000);
		
	}
	
	@AfterTest
	private void Close() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
