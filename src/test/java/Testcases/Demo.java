package Testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Pages.TVOPage;
import Utility.Utility;
import testbase.ExtentReportsGeneration;
import testbase.TestBase;

import java.io.IOException;
import java.time.Duration;

public class Demo {
	
	private static Logger log = Logger.getLogger(Demo.class);
	private WebDriver dr;
	TestBase bb = new TestBase();
    private WebDriver driver;
    TVOPage tp;
    
    @BeforeClass
    public void setup() {
    	
    	//1. Set Chrome as driver
    	//dr = bb.getDriver();
    	
    	
        // Set safari as a browser
    	 driver = new SafariDriver();
    	 log.info("Driver"+driver);
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	
    	// Open TVOLearn website
         driver.get("https://tvolearn.com/");
         
         //initialization of TVOPage object
         tp = new TVOPage(driver);
    }

    @Test
    public void verifyWebsiteTitle_001() throws IOException, InterruptedException {
        
    	
    	//verify the website link
    	ExtentReportsGeneration.addTest("Verify the website title Functionality - 001");
        String title = driver.getTitle();
        System.out.println("Title"+title);
        //assertion
        SoftAssert sft = new SoftAssert();
        sft.assertEquals("Grade 5 | TVO Learn", title);  
       
    }
    
    @Test
    public void testElementryDropDown_002() throws IOException, InterruptedException {
        
    	ExtentReportsGeneration.addTest("Test Elementry Drop Down Functionality - 002");
        tp.openDropDown();//click on dropdown
        tp.clickOnGrade();//click on grade 5
    }
    
    
    @Test
    public void scrollToLeranAndCurriculum_003() throws IOException, InterruptedException {
    	
    	ExtentReportsGeneration.addTest("Verify scroll to Learn Forward in the Curriculum - 003");
    	// Scroll down to "Learn Forward in the Curriculum" section
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	je.executeScript("window.scrollTo(0,200)", "");
    	Thread.sleep(2000);
    	je.executeScript("window.scrollTo(200,600)", "");
    			
    }
    
    @Test
    public void verifyClickOnCard_004() throws IOException, InterruptedException {
    	
    	ExtentReportsGeneration.addTest("Verify Click on Card Science and Technology - 004");
    	tp.clickCard();//click on card science and technology
         
    }
    
    @Test
    public void validatePageTitle_005() throws IOException, InterruptedException {
    	
    	
    	ExtentReportsGeneration.addTest("Validate Page Title - 005");
    	 // Validate the functionality on the page
    	try {
    	 WebElement pageTitle = driver.findElement(By.xpath("//li[@class='current']"));
    	 String aa = pageTitle.getText();
    	 log.info("Page Title:"+aa);
    	 //System.out.print(aa);
         Assert.assertEquals("Grade 5", aa);
    	}
    	catch(WebDriverException e){ // exception handling
    		System.out.println(e);
    	}
    	
    }
    
    @AfterClass
    public void tearDown() {
    	
        // Quit the driver
        driver.quit();
    }
    
    @BeforeSuite(alwaysRun=true)
	public void beforeSuite()
	{
		ExtentReportsGeneration.initializeReport("Sprint 1");
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite() throws IOException
	{
		ExtentReportsGeneration.completeReport();
	}
	

}
