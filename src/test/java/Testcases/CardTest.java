package Testcases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

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

import Pages.CardPage;
import Pages.TVOPage;
import testbase.ExtentReportsGeneration;
import testbase.TestBase;

public class CardTest {

	
	TestBase bb = new TestBase();
    private WebDriver driver;
    CardPage cp;
    TVOPage tp;
    String url = "";
    HttpURLConnection con = null;
    int respCode = 200;
    String homePage = "https://tvolearn.com/";
    
    @BeforeClass
    public void setup() throws InterruptedException, IOException {
    	
    	//1. Set Chrome as driver
    	//dr = bb.getDriver();
    	
    	
        // Set safari as a browser
    	 driver = new SafariDriver();
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	
    	// Open TVOLearn website
         driver.get(homePage);
         
        //initialization of TVOPage object
         tp = new TVOPage(driver);
         
         //initialization of CardPage object
         cp = new CardPage(driver);
         tp.openDropDown();
         tp.clickOnGrade();
         JavascriptExecutor je = (JavascriptExecutor) driver;
     	 je.executeScript("window.scrollTo(0,200)", "");
     	 Thread.sleep(2000);
     	 je.executeScript("window.scrollTo(200,600)", "");
         tp.clickCard();
    }

    
    @Test
    public void clickOnpowerPathway_006() throws IOException, InterruptedException {
    	
    	
    	ExtentReportsGeneration.addTest("Click on Power Pathway - 006");
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	je.executeScript("window.scrollTo(0,1000)", "");
    	Thread.sleep(2000);
    	je.executeScript("window.scrollTo(1000,2800)", "");
    	cp.clickOnPowerPathway();
    	driver.navigate().back();
    }
    
    @Test
    public void getAllLinks_007() throws IOException, InterruptedException {
    	
    	
    	ExtentReportsGeneration.addTest("Get all Links - 007");
    	//Get list of web-elements with tagName  - a
    	 List<WebElement> links = driver.findElements(By.tagName("a"));
    	 
//    	 //Traversing through the list and printing its text along with link address
//    	 for(WebElement link:allLinks){
//    	 System.out.println(link.getText() + " - " + link.getAttribute("href"));
//    	 }
    	 //check links are valida or broken
    	Iterator<WebElement> it = links.iterator();
 		while (it.hasNext()) {

 			url = it.next().getAttribute("href");

 			try {
 				con = (HttpURLConnection) (new URL(url).openConnection());
 				con.setRequestMethod("HEAD");
 				con.connect();
 				respCode = con.getResponseCode();

 				if (respCode >= 400) {
 					System.out.println(url + " is a broken link");
 				} else {
 					System.out.println(url + " is a valid link");
 				}

 			} catch (MalformedURLException e) {

 				e.printStackTrace();
 			} catch (IOException e) {

 				e.printStackTrace();
 			}
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
	public void afterSuite()
	{
		ExtentReportsGeneration.completeReport();
	}
	

}
