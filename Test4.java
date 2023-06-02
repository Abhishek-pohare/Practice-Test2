package brwsrInvoke;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test4 {
	private WebDriver driver;
	private String URL="http://live.techpanda.org/index.php/";
	@BeforeTest
	public void setUP() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@Test
	public void TestCase4() throws InterruptedException {
		driver.get(URL);
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[@title='Xperia']/following::a[4]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@title='IPhone']/following::a[4]")).click();
		Thread.sleep(1000);
		String mainmob1= driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();
		String mainmob2= driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();
		System.out.println(mainmob1+" "+mainmob2);
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		Thread.sleep(1000);
		
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			System.out.println("Switched to "+handle);
		}
		String head= "COMPARE PRODUCTS";
		String cmpHead=driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div[1]/h1")).getText();
		System.out.println(cmpHead);
		
		String popmob1= driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();
		String popmob2= driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();
		System.out.println(popmob1+" "+popmob2);
		//check pop up heading
		try {
			Assert.assertEquals(head, cmpHead);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//check mobiles
		try {
			Assert.assertEquals(mainmob1, popmob1);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		// check mobiles
		try {
			Assert.assertEquals(mainmob2, popmob2);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		driver.findElement(By.xpath("//span[text()='Close Window']")).click();
		System.out.println("PopUP Closed");
		
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			System.out.println("Switched to "+handle);
		}
	}

	@AfterTest
	public void close() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
