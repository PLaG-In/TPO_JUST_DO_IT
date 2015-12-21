package Authorization;

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
public class TestAuthorization
{
    private Authorization auth;
    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Java\\jdk1.8.0_65\\bin\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        auth = PageFactory.initElements(driver, Authorization.class);
    }

    @After
    public void closeBrowser()
    {
        auth.close();
    }

    @Test
    public void testCorrectInputs()
    {
        auth.open();
        auth.linkEnter.click();
        auth.textBoxLogin.sendKeys("test@mail.te");
        auth.textBoxPassword.sendKeys("qwerty1");
        auth.loginButton.click();

        boolean result = auth.findText(auth.alertMessage.toString(), auth.alert);
        Assert.assertFalse("Authorization failed with correct input!", result);
        auth.close();
    }

    @Test
    public void testIncorrectLogin()
    {
        auth.open();
        auth.linkEnter.click();
        auth.textBoxLogin.sendKeys("axe1@w.s");
        auth.textBoxPassword.sendKeys("qwerty1");
        auth.loginButton.click();

        boolean result = auth.findText(auth.alertMessage.toString(), auth.alert);
        Assert.assertTrue("Authorization failed!Incorrect login!", result);
        auth.close();
    }

    @Test
    public void testIncorrectPassword()
    {
        auth.open();
        auth.linkEnter.click();
        auth.textBoxLogin.sendKeys("test@mail.te");
        auth.textBoxPassword.sendKeys("ssssss");
        auth.loginButton.click();

        boolean result = auth.findText(auth.alertMessage.toString(), auth.alert);
        Assert.assertTrue("Authorization failed!Incorrect password!", result);
        auth.close();
    }

    @Test
    public void testIncorrectPasswordAndLogin()
    {
        auth.open();
        auth.linkEnter.click();
        auth.textBoxLogin.sendKeys("axe1@w.s");
        auth.textBoxPassword.sendKeys("ssssss");
        auth.loginButton.click();

        boolean result = auth.findText(auth.alertMessage.toString(), auth.alert);
        Assert.assertTrue("Authorization failed!Incorrect password and login!", result);
        auth.close();
    }
}
