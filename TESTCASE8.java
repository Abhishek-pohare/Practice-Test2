package brwsrInvoke;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TESTCASE8 {
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
	private void TESTSCRIPT8() throws InterruptedException {
		driver.get(baseURL);
		Thread.sleep(2000);
		
		//click on MY Account
		driver.findElement(By.xpath("//div[@class='block-title']/following::a[@title='My Account']")).click();
       Thread.sleep(2000);
       
       //Login Using credentials
       driver.findElement(By.xpath("//input[@name='login[username]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys(vEmail);
		driver.findElement(By.xpath("//input[@name='login[password]']")).clear();
		driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys(vPassword);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(2000);
		
		//click on Reorder 
		System.out.println("Before Reorder***");
		driver.findElement(By.xpath("//tbody//tr[1]/td[6]/span/a[2]")).click();
		System.out.println("After Reorder***");
		
		//get grand total
		String vPrice= driver.findElement(By.xpath("//div[@class='cart-totals']/table/tfoot/tr/td[2]/strong/span")).getText();
		
		//change quantity
		driver.findElement(By.xpath("//input[@title='Qty']")).clear();
		driver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("10");
		Thread.sleep(1000);
		System.out.println("Quantity Set");
		
		//Click Update btn
		driver.findElement(By.xpath("//button[@title='Update']")).click();
		System.out.println("Cart Updated/ Update btn clicked");
		Thread.sleep(1500);
		
		//verify updated Grand Total
		String UPrice= driver.findElement(By.xpath("//tfoot/tr/td[2]//span[@class='price']")).getText();
		//verify before and after grand total 
		if(vPrice==UPrice) {
			System.out.println("GRAND TOTAL price has not Changed");
		}
		else {
			System.out.println("GRAND TOTAL price has Chaged");
		}
		
		//click proceed to checkout
		driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
		Thread.sleep(2000);
		
		//billing Address
		for(String handle:driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		//select billing Address
		try {
			Select billAddr= new Select(driver.findElement(By.xpath("//select[@name='billing_address_id']")));
			int bAddrSize= billAddr.getOptions().size();
			billAddr.selectByIndex(bAddrSize-1);
		}
		catch(Exception e) {
			System.out.println("No dropdown present "+e);
		}
		//check radio btn 
		driver.findElement(By.xpath("//input[@title='Ship to different address']")).click();
		
		//click on continue
		driver.findElement(By.xpath("//div[@id='billing-buttons-container']/button")).click();
		Thread.sleep(2000);
		
		//shipping Addr
		try {
			Select ShipAddr= new Select(driver.findElement(By.xpath("//select[@name='shipping_address_id']")));
			int shipAddSize= ShipAddr.getOptions().size();
			ShipAddr.selectByIndex(shipAddSize-1);
		}catch(Exception e2) {
			System.out.println(e2);
		}
		//click on continue
		driver.findElement(By.xpath("//div[@id='shipping-buttons-container']/button")).click();
		Thread.sleep(2000);
		
		//in Shipping Method click continue
		driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/button")).click();
		Thread.sleep(2000);
		
		//In payment method check money/order
		driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@id='payment-buttons-container']/button")).click();
		Thread.sleep(2000);
		
		//click on place order
		driver.findElement(By.xpath("//button[@title='Place Order']")).click();
		Thread.sleep(2000);
		
		//verify order number;
		String OrderNum= driver.findElement(By.xpath("//div[@class='main']/div/div[1]/following::p[1]/a")).getText();
		System.out.println("Order Number: "+OrderNum);
	}
	
	@AfterTest
	private void Close() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}

}
