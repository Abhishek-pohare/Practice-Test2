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

public class TESTCASE9 {
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
	private void TESTSCRIPT9() throws InterruptedException {
		driver.get(baseURL);
		Thread.sleep(2000);
		
		//click on Mobile
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		Thread.sleep(2000);
		//click on add to cart
		driver.findElement(By.xpath("//div[@class='category-products']/div[1]/following::li[1]/div/div[3]/button")).click();
		Thread.sleep(2000);
		
		//apply coupon
		driver.findElement(By.xpath("//input[@name='coupon_code']")).clear();
		driver.findElement(By.xpath("//input[@name='coupon_code']")).sendKeys("GURU50");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@title='Apply']")).click();
		
		//
		String vSubTOT= driver.findElement(By.xpath("//table[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText();
		String vDisc= driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();
		String vDiscountAmt= driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText();
		
		// Remove the $ sign 
	    String sSubTot = vSubTOT.replaceAll("\\$", " ");                // e.g. $500.00	        
        String sDiscDisp = vDisc.replaceAll("\\$", " ");            // e.g. $25.00	        
        String sDiscountedAmtDisplayed = vDiscountAmt.replaceAll("\\$", " "); 
        
        String sNegDisc= sDiscDisp.replaceAll("\\-", "");
        
        // trim the String variables to get it ready for calculation
        String fSubTot = sSubTot.trim();                                 // e.g. $500.00	        
        String fDiscDisp = sNegDisc.trim();                              // e.g. $25.00	        
        String fDiscountedAmtDisplayed = sDiscountedAmtDisplayed.trim(); // e.g. $475.00
        
        double dSubTot = Double.parseDouble(fSubTot);                                 // e.g.        $500.00
	    double dDiscDisp = Double.parseDouble(fDiscDisp);                             // e.g.         $25.00
	    double dDiscountedAmtDisplayed = Double.parseDouble(fDiscountedAmtDisplayed); // e.g.        $475.00
	    
	 // multiply the dSubTot by the GURU50 discount rate (GURU50 = 5% = 0.05) 
	    double GURU50=0.05;
	    double discountedAmt = (dSubTot * GURU50);     // discountedAmt is the calculated discounted amount (e.g. $25.00)	
	    double dDiscPrice = (dSubTot - discountedAmt); // e.g. Discounted Price (e.g. $475) = Sub Total ($500.00) less the 5% discount amount ($25.00)
	    
	    if (discountedAmt == dDiscDisp){
    		System.out.println("*** Derived discount amount   = " + discountedAmt);
    		System.out.println("*** Displayed discount amount = " + dDiscDisp);
            }
	    else
        { 
    	System.out.println("*** Derived discount amount not equal displayed discount amount ***");
        }	
	 
	    String string_discountedAmt = Double.toString(discountedAmt);
	    String string_dDiscDisp = Double.toString(dDiscDisp);
	    
	    try {
	        Assert.assertEquals(string_discountedAmt, string_dDiscDisp);
	      } catch (Error e) {
	    	  System.out.println("*** Derived discount amount not equal displayed discount amount ***");
	    	  e.printStackTrace();	
	      }
	    
	    if (dDiscPrice == dDiscountedAmtDisplayed){
    		System.out.println("*** Derived discounted price   = " + dDiscPrice);
    		System.out.println("*** Displayed discounted price = " + dDiscountedAmtDisplayed);
	    }else
	    	{ System.out.println("*** Derived discounted price not equal displayed discounted price ***");
	    }	
	    
	    String string_dDiscPrice = Double.toString(dDiscPrice);
	    String string_discountedAmtDisplayed = Double.toString(dDiscountedAmtDisplayed);
	    try {
	        Assert.assertEquals(string_dDiscPrice, string_discountedAmtDisplayed);
	      } catch (Error e) {
	    	  System.out.println("*** Derived discount amount not equal displayed discount amount ***");
	    	  e.printStackTrace();	
	      }
	    
	}
	
	@AfterTest
	private void Close() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
