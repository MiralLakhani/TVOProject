package Pages;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TVOPage {

	private static Logger log = Logger.getLogger(TVOPage.class);
	private WebDriver driver;
	
	//constructor
	public TVOPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver,this);	
		
	}
	
	 // Navigate to Elementary (K-8) dropdown 
	 @FindBy(xpath="//div[@class='grid grid--no-gutters grid--table site-header__mobile-nav']//li[1]//button[1]")
	 WebElement elementaryDropdown;
	 
	 public TVOPage openDropDown() throws IOException {
		 
		
		 elementaryDropdown.click();
		 log.info("Elementary (K-8) dropdown Locator:"+elementaryDropdown);
		 return this;
	 }
	
	// Select any Grade
	 @FindBy(xpath="//span[@class='site-nav__label'][normalize-space()='Grade 5']")
	 private WebElement grade;
	
	 public void clickOnGrade()
	 {
		 grade.click();
		 log.info("Grade Locator:"+grade);
	 }
	 
	 // Select Science and technology card
	 //@FindBy(css="#s-ace35f53-832c-4a69-8fd1-454cf9205641")
	 @FindBy(xpath="//a[@id='s-ace35f53-832c-4a69-8fd1-454cf9205641']//div[@class='button-subject-link'][normalize-space()='Explore Details']")
	 private WebElement card;
	 
	 public void clickCard()
	 {
		
		card.click();
		log.info("Card Locator:"+card);
	 }
	
}

