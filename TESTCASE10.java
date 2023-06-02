package brwsrInvoke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import test10.EMAIL_UTILS;


public class TESTCASE10 {
	private WebDriver driver;
	private String URL="http://live.techpanda.org/index.php/backendlogin/";
	private String UserID="user01";
	private String Password="guru99com";
	
	@BeforeTest
	private void Open() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	private void TESTSCRIPT10() throws InterruptedException {
		driver.get(URL);
		Thread.sleep(2000);
		// Login 
		driver.findElement(By.xpath("//input[@id='username']")).clear();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(UserID);
		driver.findElement(By.xpath("//input[@id='login']")).clear();
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@title='Login']")).click();
		try {Thread.sleep(5000);}catch(Exception e){};
		
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		//click on sales
		driver.findElement(By.xpath("//span[text()='Sales']")).click();
		Thread.sleep(2000);
		//go to sales-> order menu
		driver.findElement(By.xpath("//span[text()='Orders']")).click();
		try {Thread.sleep(5000);}catch(Exception e){};
		
		//click on CSV Export
		driver.findElement(By.xpath("//button[@title='Export']")).click();
		Thread.sleep(3000);
		
		String filePath= System.getProperty("user.home")+"Downloads/orders.csv";
		try {
		//	EMAIL_UTILS.emailUtil(filePath);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		File f= new File(filePath);
		try {
			FileReader fr= new FileReader(f);
			BufferedReader br= new BufferedReader(fr);
			String line= br.readLine();
			while(line!=null) {
				System.out.println(line);
				line=br.readLine();
			}
			fr.close();
			br.close();
		}
		catch(FileNotFoundException e1) {
			System.out.println(e1);
		}catch(IOException e2) {
			System.out.println(e2);
		}
	}

	@AfterTest
	private void CLOSE() {
		driver.quit();
	}
}
