package ShowEventAndWriteComment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Владимир on 20.12.2015.
 */
public class TestShowEventAndWriteComment
{
    private static final int sevenSec = 7000;
    private ShowEventAndWriteComment seawc;
    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Java\\jdk1.8.0_65\\bin\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        seawc = PageFactory.initElements(driver, ShowEventAndWriteComment.class);
    }

    @After
    public void closeBrowser()
    {
        seawc.close();
    }

    @Test
    public void testCommentSending()
    {
        seawc.open();
        seawc.linkEnter.click();
        seawc.textBoxLogin.sendKeys(seawc.email); //2131***()&&&
        seawc.textBoxPassword.sendKeys(seawc.pass);
        seawc.loginButton.click();
        seawc.pause(sevenSec);
        seawc.linkEvent.click();
        seawc.pause(sevenSec);
        seawc.linkEventMain.click();
        seawc.textBoxComment.sendKeys("This is test message");
        seawc.sendButton.click();
        Assert.assertTrue("Can't send comment!", seawc.isTextPresent("This is test message"));
        seawc.close();
    }
}
