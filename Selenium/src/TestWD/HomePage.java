package TestWD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	private WebDriver driver;
	
	@FindBy(xpath="//nav/a")
	public WebElement lnkContacts;
	
	@FindBy(xpath="//nav/a[2]")
	public WebElement lnkPhoneDirectory;
	
	@FindBy(name="q")
	private WebElement inputSearch;
	
	@FindBy(name="s")
	private WebElement btnSubmitSearch;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void open(String url)
	{
		driver.get(url);
	}
	
	public void close() 
	{
		driver.quit();
	}
	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	public void searchText(String text)
	{
		inputSearch.sendKeys(text);
		btnSubmitSearch.click();
	}
	
	public boolean isTextPresent(String text)
	{
		boolean result=false;
		String data = driver.getPageSource();
		result = data.contains(text);
		return result;
	}
	
}
