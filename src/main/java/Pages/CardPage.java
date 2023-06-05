package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardPage {

	private static Logger log = Logger.getLogger(CardPage.class);
	private WebDriver driver;
	
	//constructor
	public CardPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver,this);	
		
	}
	
	 // click on power pathway 
	 @FindBy(xpath="//p[normalize-space()='Power Pathways']")
	 WebElement clickPowerPathway;
	 
	 public void clickOnPowerPathway() {
		 
		 clickPowerPathway.click();
    	 log.info("Link Locator:"+clickPowerPathway);
    	
	 }
	 
}
