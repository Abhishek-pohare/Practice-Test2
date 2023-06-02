package brwsrInvoke;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCASE6 {
	private WebDriver driver;
	private String baseURL="http://live.techpanda.org/";
	public String FirstName="BERRY";
	public String LastName="BERRYEIGHT";
	public String vEmail="Berry@abc8.com.au";
	public String vPassword="Panda@2023";
	
	@BeforeTest
	public void Open() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@Test
	public void TestSCRIPT6() throws InterruptedException {
		driver.get(baseURL);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
		Thread.sleep(1000);
		//1 Enter mail& password to login
		driver.findElement(By.xpath("//input[@name='login[username]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys(vEmail);
		driver.findElement(By.xpath("//input[@name='login[password]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys(vPassword);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(1500);
		
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		//2. click on My wishlist
		driver.findElement(By.xpath("//div[@class='block block-account']/div/following::li[8]/a[text()='My Wishlist']")).click();
		Thread.sleep(1500);
		//3 Add to cart
		driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
		Thread.sleep(1500);
		//4.
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		
		Select oselect= new Select(driver.findElement(By.xpath("//select[@id='country']")));
		oselect.selectByVisibleText("India");
		
		driver.findElement(By.xpath("//input[@name='region']")).sendKeys("New Wales");
		driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("2000");
		driver.findElement(By.xpath("//button[@title='Estimate']")).click();
		
		String sFlatRate="Flat Rate";
		String flatrate= driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt")).getText();
		try {
			System.out.println("sFlatRate= "+sFlatRate);
			System.out.println("flatrate= "+flatrate);
			Assert.assertEquals(flatrate, sFlatRate);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		String sFlatRatePrice = "Fixed - $5.00";
		String flatrateprice= driver.findElement(By.xpath("//form[@id='co-shipping-method-form']/dl/dd/ul/li/label")).getText();
	
		try {
			System.out.println("sFlatRatePrice= "+sFlatRatePrice);
			System.out.println("flatrateprice= "+flatrateprice);
			Assert.assertEquals(flatrateprice, sFlatRatePrice);
		}
		catch(Exception e1) {
			System.out.println(e1);
		}
		
		//
		driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();
		driver.findElement(By.xpath("//button[@title='Update Total']")).click();
		
		String vFlatRatePrice = "$5.00";
		String shippingCostIncluded= driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();
		try {
			System.out.println("vFlatRatePrice= "+vFlatRatePrice);
			System.out.println("shippingCostIncluded= "+shippingCostIncluded);
			Assert.assertEquals(shippingCostIncluded, vFlatRatePrice);
		}
		catch(Exception e2) {
			System.out.println(e2);
		}
		driver.findElement(By.xpath("//div[@class='page-title title-buttons']/ul/li/button")).click();
		Thread.sleep(2000);
		
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@id='billing:street1']")).clear();
		driver.findElement(By.xpath("//input[@id='billing:street1']")).sendKeys("148 Crown Street");
		
		Select oselect2= new Select(driver.findElement(By.xpath("//select[@id='billing:country_id']")));
		oselect2.selectByIndex(14);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='billing:city']")).clear();
		driver.findElement(By.xpath("//input[@id='billing:city']")).sendKeys("Sydney");
		
		driver.findElement(By.xpath("//input[@id='billing:postcode']")).sendKeys("2000");
		driver.findElement(By.xpath("//input[@id='billing:telephone']")).sendKeys("12439849");
		//check ship to different addr.
		driver.findElement(By.xpath("//input[@title='Ship to different address']")).click();
		
		driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();
		
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Thread.sleep(2000);
		try {
			Select sAddr= new Select(driver.findElement(By.name("shipping_address_id")));
			int sAddrSize= sAddr.getOptions().size();
			sAddr.selectByIndex(sAddrSize-1);
		}
		catch(Exception e3) {
			System.out.println("no dropdown element present "+e3);
		}
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(".//*[@id='shipping:firstname']")).sendKeys(FirstName);
		driver.findElement(By.xpath(".//*[@id='shipping:lastname']")).sendKeys(LastName);
		
		new Select(driver.findElement(By.xpath(".//*[@id='shipping:country_id']"))).selectByIndex(14);
		
		driver.findElement(By.xpath(".//*[@id='shipping:street1']")).sendKeys("berry street");
		driver.findElement(By.xpath(".//*[@id='shipping:city']")).sendKeys("sydney");
		driver.findElement(By.xpath(".//*[@id='shipping:region']")).sendKeys("New South Wales");
		driver.findElement(By.xpath(".//*[@id='shipping:telephone']")).sendKeys("000111");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='shipping-buttons-container']/button")).click();
		
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Thread.sleep(1000);
		
		//shipping method click continue
		driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click();
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();
		driver.findElement(By.xpath("//div[@id='payment-buttons-container']/button")).click();
		Thread.sleep(2000);
		//click on place order
		driver.findElement(By.xpath("//div[@id='review-buttons-container']/button")).click();
		
		String OrderNum= driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();
		System.out.println("Your Order number: "+OrderNum);
		
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
}
