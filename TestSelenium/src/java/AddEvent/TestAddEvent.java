package AddEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


/**
 * Created by Владимир on 19.12.2015.
 */
public class TestAddEvent
{
    private AddEvent addEvent;
    private static final int sevenSec = 7000;
    private static final int threeSec = 3000;
    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Java\\jdk1.8.0_65\\bin\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        addEvent = PageFactory.initElements(driver, AddEvent.class);
    }

    @After
    public void closeBrowser()
    {
        addEvent.close();
    }

    @Test
    public void testInputsWithoutDescription()
    {
        addEvent.open();
        addEvent.loginRelaxup();
        addEvent.pause(sevenSec);
        addEvent.linkAddEvent.click();
        addEvent.textBoxPhone.clear();
        addEvent.textBoxPhone.sendKeys("8005553535");
        addEvent.textBoxEmail.clear();
        addEvent.textBoxEmail.sendKeys("test@box.ru");
        addEvent.textBoxTitle.clear();
        addEvent.textBoxTitle.sendKeys("test");
        addEvent.linkTimetableAndPrice.click();
        addEvent.chooseListLocation.sendKeys("ДК XXX-летия Победы");
        addEvent.dataTimeEndDate.sendKeys("2016-05-11");
        addEvent.dataTimeEndTime.sendKeys("00:00");
        addEvent.xButton.click();

        Assert.assertTrue("Test passed without description!", addEvent.isTextPresent("Необходимо заполнить «Описание»."));
        addEvent.close();
    }
    //I am testing "Edit Event" now.

    @Test
    public void testInputsWithoutTitle()
    {
        addEvent.open();
        addEvent.loginRelaxup();
        addEvent.pause(sevenSec);
        addEvent.goToEditEvent();
        addEvent.textBoxTitle.clear();
        addEvent.xButton.click();

        Assert.assertTrue("Test passed without title!", addEvent.isTextPresent("Необходимо заполнить «Заголовок»."));
        addEvent.close();
    }

    @Test
    public void testInputsWithoutEndTime()
    {
        addEvent.open();
        addEvent.loginRelaxup();
        addEvent.pause(sevenSec);
        addEvent.goToEditEvent();
        addEvent.linkTimetableAndPrice.click();
        addEvent.dataTimeEndDate.clear();
        addEvent.dataTimeEndTime.clear();
        addEvent.xButton.click();
        addEvent.pause(threeSec);

        Assert.assertTrue("Test passed without end date!", addEvent.isTextPresent("Место проведения #1: Расписание #1: Необходимо указать время окончания мероприятия"));
        addEvent.close();
    }

    @Test
    public void testInputsWithIncorrectSecPhoneNumber()
    {
        addEvent.open();
        addEvent.loginRelaxup();
        addEvent.pause(sevenSec);
        addEvent.goToEditEvent();
        addEvent.linkTimetableAndPrice.click();
        addEvent.textBoxSecPhone.clear();
        addEvent.textBoxSecPhone.sendKeys("****77^^^%%$$$###@@::::aaa");
        addEvent.xButton.click();
        addEvent.pause(threeSec);

        Assert.assertFalse("Test passed with incorrect phone number!", addEvent.isTextPresent("Мероприятие успешно сохранено."));
        addEvent.close();
    }
}
