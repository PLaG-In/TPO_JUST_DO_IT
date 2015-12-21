package TestWD;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testHomePage {
	
	private HomePage hp;
	
	WebDriver driver = new FirefoxDriver();
	
	@Before
	public void openTheBrowser() {
		hp = PageFactory.initElements(driver, HomePage.class);
		hp.open("http://volgatech.net/");
	}
	 
	@After
	public void closeTheBrowser() {
		hp.close();
	}
	
	@Test
	public void testPageTitle() 
	{
		assertEquals(hp.getTitle(), "������� �������� - ���������� ��������������� ��������������� �����������" );
	}
	
	@Test
	public void testPhoneLink()
	{
		hp.lnkPhoneDirectory.click();
		assertEquals(hp.getTitle(), "���������� ���������� - ���������� ��������������� ��������������� �����������");
	}
	
	@Test
	public void testContactsLink()
	{
		hp.lnkContacts.click();
		assertEquals(hp.getTitle(), "�������� - ���������� ��������������� ��������������� �����������");
	}
	
	@Test
	public void testSearch()
	{
		hp.searchText("����");
		boolean result = hp.isTextPresent("��������� ����������� � �������������� �������");
		assertTrue(result);
	}
	

}
