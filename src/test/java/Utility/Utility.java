package Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import testbase.TestBase;

public class Utility extends TestBase {
	
	//scroll to the element method
	public static void scrollToElementActions(WebElement ele)
	{
		Actions ac = new Actions(driver);
		ac.scrollToElement(ele).scrollByAmount(0, 800).perform();
		
	}
	
	//scroll to element using javascriptexecutor
	public static void scrollToElement() throws InterruptedException
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollTo(0,200)", "");
		Thread.sleep(2000);
		je.executeScript("window.scrollTo(200,600)", "");
				
	}
		
	public static String captureScreenAsFileName() throws IOException
	{
		
		File f = new File("./Screenshot");
		if(!f.isDirectory())
		{
			f.mkdir();
		}
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File(f+"/"+getDate()+"_image.jpg"));//jpg, png
		return f+"/"+getDate()+"_image.jpg";
	}
	
	public static String getDate()
	{
		Date dt = new Date();
		System.out.println(dt);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_d_HH_mm_ss_SSS");
		String date = sdf.format(dt);
		return date;
	}
	
	public static String attacheSceenshot() throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		String file = ts.getScreenshotAs(OutputType.BASE64);
		//String tag = "<img src=\"data:image/png;base64,"+file+"\" height=\"600\" width=\"800\" />";
		//Reporter.log(tag);
		return file;
	}
		
}

