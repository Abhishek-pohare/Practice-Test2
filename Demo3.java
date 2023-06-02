package brwsrInvoke;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Demo3 {
	private WebDriver driver;
	private String URL;
	
	private void Initialize() throws Exception {
		FirefoxOptions fo= new FirefoxOptions();
		fo.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\geckodriver.exe");
		driver= new FirefoxDriver(fo);
		URL="http://live.techpanda.org/";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(URL);
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/following::span[text()='Add to Cart']")).click();
		WebElement QTY=driver.findElement(By.xpath("//button[@name='update_cart_action']/preceding::input[@type='text']"));
		QTY.click();
		QTY.clear();
		
		Actions act= new Actions(driver);
		Action series= act.moveToElement(QTY).click().sendKeys("1000").build();
		series.perform();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[@class='button btn-update']")).click();
		String Errmsg=driver.findElement(By.xpath("//p[contains(text(),'The')]")).getText();
		System.out.println(Errmsg);
		
		driver.findElement(By.xpath("//span[text()='Empty Cart']")).click();
		String cart= driver.findElement(By.xpath("//p[contains(text(),'cart')]")).getText();
		System.out.println(cart);
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Demo3 d= new Demo3();
		d.Initialize();
	}

}
