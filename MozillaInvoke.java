package brwsrInvoke;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

public class MozillaInvoke {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String Hometitle="";
		String Mobtitle="";
		String expTitle="Mobile";
		FirefoxOptions f= new FirefoxOptions();
		f.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver", 
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\geckodriver.exe");
		WebDriver driver= new FirefoxDriver(f);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/index.php/");
		
		Hometitle= driver.getTitle();
		System.out.println(Hometitle);
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		Thread.sleep(1500);
		Mobtitle= driver.getTitle();
		System.out.println(Mobtitle);
		
		if(Mobtitle.contentEquals(expTitle)) {
			System.out.println("Test Passed");
		}
		else {
			System.out.println("Test failed");
		}
		
		Select sel= new Select(driver.findElement(By.xpath("//div[@class='category-description std']"
				+ "//following::select[1]")));
		sel.selectByIndex(1);
		Thread.sleep(2000);
		driver.quit();
		//driver.close();

	}

}
