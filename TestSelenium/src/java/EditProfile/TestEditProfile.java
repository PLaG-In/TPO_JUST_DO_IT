package EditProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Владимир on 19.12.2015.
 */
public class TestEditProfile
{
    private EditProfile editProfile;
    private static final int sevenSec = 7000;
    private static final int fiveSec = 5000;
    private static String success = "Успешно сохрано.";
    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Java\\jdk1.8.0_65\\bin\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        editProfile = PageFactory.initElements(driver, EditProfile.class);
    }

    @After
    public void closeBrowser() {
        editProfile.close();
    }

    @Test
    public void testChangeNameAndEmail() {
        editProfile.open();
        editProfile.loginRelaxup();
        editProfile.pause(sevenSec);
        editProfile.linkMyProfile.click();
        editProfile.linkEditProfile.click();
        editProfile.textBoxEmail.clear();
        editProfile.textBoxEmail.sendKeys("test@mail.te");
        editProfile.email = "test@mail.te";
        editProfile.textBoxName.clear();
        editProfile.textBoxName.sendKeys("Monty");
        editProfile.saveButton.click();
        editProfile.pause(sevenSec);
        Assert.assertTrue("Correct changes don't passed!", editProfile.isTextPresent(success));
        editProfile.close();
    }
    @Test
    public void testChangePassword() {
        editProfile.open();
        editProfile.loginRelaxup();
        editProfile.pause(sevenSec);
        editProfile.linkMyProfile.click();
        editProfile.linkEditProfile.click();
        editProfile.textBoxNewPassword.clear();
        editProfile.textBoxNewPassword.sendKeys("qwerty1");
        editProfile.textBoxNewPasswordRepeat.clear();
        editProfile.textBoxNewPasswordRepeat.sendKeys("qwerty1");
        editProfile.saveButton2.click();
        editProfile.pause(sevenSec);
        Assert.assertTrue("Correct change password don't passed!", editProfile.isTextPresent(success));
        editProfile.close();
    }

    @Test
    public void testBadChangePassword() {
        editProfile.open();
        editProfile.loginRelaxup();
        editProfile.pause(sevenSec);
        editProfile.linkMyProfile.click();
        editProfile.linkEditProfile.click();
        editProfile.textBoxNewPassword.clear();
        editProfile.textBoxNewPassword.sendKeys("qwerty1");
        editProfile.textBoxNewPasswordRepeat.clear();
        editProfile.textBoxNewPasswordRepeat.sendKeys("qwerty7");
        editProfile.saveButton2.click();
        editProfile.pause(sevenSec);
        Assert.assertTrue("Incorrect change password passed!Failed!", editProfile.isTextPresent("Повтор пароля must be equal to \"Пароль\"."));
        editProfile.close();
    }

    @Test
    public void testBadChangeName() {
        editProfile.open();
        editProfile.loginRelaxup();
        editProfile.pause(sevenSec);
        editProfile.linkMyProfile.click();
        editProfile.linkEditProfile.click();
        editProfile.textBoxName.clear();
        editProfile.textBoxName.sendKeys("-asdjdaj-k@*");
        editProfile.saveButton.click();
        editProfile.pause(sevenSec);
        Assert.assertFalse("Incorrect name passed!Failed!", editProfile.isTextPresent(success));
        editProfile.close();
    }

    @Test
    public void testBadChangeEmail() {
        editProfile.open();
        //editProfile.email = "2131***()&&&";
        editProfile.loginRelaxup();
        editProfile.pause(fiveSec);
        editProfile.linkMyProfile.click();
        editProfile.linkEditProfile.click();
        editProfile.textBoxEmail.clear();
        editProfile.textBoxEmail.sendKeys("2131***()&&&");
        editProfile.email = "2131***()&&&";
        editProfile.saveButton.click();
        editProfile.pause(fiveSec);
        editProfile.textBoxEmail.clear();
        editProfile.textBoxEmail.sendKeys("test@mail.te");
        editProfile.saveButton.click();
        editProfile.pause(fiveSec);
        Assert.assertFalse("Incorrect email passed!Failed!", editProfile.isTextPresent(success));
        editProfile.close();
    }
}
