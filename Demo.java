package brwsrInvoke;

//********************************************************************************************
	//*                                                                                          *
	//*    Guru99 eCommerce Live Project                                                         *
	//*    Day 1 - TestCase 1                                                                    *
	//*    Author: Krishna Rungta                                                                *                                                                                      *

	//********************************************************************************************
	/*  

	Test Steps
	Step 1. Goto http://live.techpanda.org/
	Step 2. Verify Title of the page
	Step 3. Click on ‘MOBILE’ menu
	Step 4. Verify Title of the page
	Step 5. In the list of all mobile , select ‘SORT BY’ dropdown as ‘name’
	Step 6. Verify all products are sorted by name
	*/



	//import static org.junit.Assert.fail;

	import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo {

	
		  private WebDriver driver;
		  private String baseUrl;
		  public int scc = 0;
		  
		  private StringBuffer verificationErrors = new StringBuffer();

		  @BeforeMethod
		@BeforeTest
		public void setUp() throws Exception {
		    driver = new FirefoxDriver();
			// Step 1 Goto http://live.techpanda.org/
		    baseUrl = "http://live.techpanda.org/";
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  }
		  @Test
		  public void testDay1TestCase1() throws Exception {
			
		    driver.get(baseUrl); 
			//Step 2. Verify Title of the page
		    String demoSite  = driver.findElement(By.cssSelector("h2")).getText();
		    System.out.println(demoSite);
		    try {
		      AssertJUnit.assertEquals("THIS IS DEMO SITE FOR   ", demoSite);
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }	    
		    

		    // Step 3. Click on ‘MOBILE’ menu
		    driver.findElement(By.linkText("MOBILE")).click();	
	        // Step 5. In the list of all mobile , select ‘SORT BY’ dropdown as ‘name’		
		    new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");
		    
		    // Step 6. Verify all products are sorted by name
			// this will take a screen shot of the manager's page after a successful login
		    scc = (scc+1);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String png = ("C:\\Guru99 eCommerce Live Project\\Day01_TestCase1\\Mobile Products are sorted" + scc + ".png");
		//	FileUtils.copyFile(scrFile, new File(png));
	//**************************************************************************************************************************	
		    }	
		  
		@AfterTest
		public void tearDown() throws Exception {
			driver.quit();
		   
		  }
		
		  
		

}
