package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class TestBase {

	public static WebDriver driver;
	public String browser;
	public Properties prop;
	public static Logger log = Logger.getLogger(TestBase.class);
	public WebDriver getDriver() throws IOException
	{
		File file = new File("./src/main/resources/config/config.properties");
		prop = new Properties();
		FileInputStream inputStream = new FileInputStream(file);
		prop.load(inputStream);
		browser = prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
		ChromeOptions opt = new ChromeOptions();
		//opt.addArguments("start-fullscreen");
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeagent"));
		driver = new ChromeDriver(opt);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions opt = new FirefoxOptions();
			//opt.addArguments("start-fullscreen");
			opt.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxagent"));
			driver = new FirefoxDriver(opt);
			
		}else if(browser.equalsIgnoreCase("edge"))
		{
			EdgeOptions opt = new EdgeOptions();
			//opt.addArguments("start-fullscreen");
			System.setProperty("webdriver.edge.driver", prop.getProperty("edgeagent"));
			driver = new EdgeDriver(opt);
		}else
		{
			Throwable thr = new Throwable();
			thr.initCause(null);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		//driver.manage().window().minimize();
		//driver.manage().window().fullscreen();
		driver.get(prop.getProperty("url"));
		log.info("URL Openned:" + prop.getProperty("url"));
		return driver;
	}
	
	public void closeDriver()
	{
		driver.quit();
	}
}
