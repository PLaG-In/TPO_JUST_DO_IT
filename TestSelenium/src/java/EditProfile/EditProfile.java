package EditProfile;

import org.hamcrest.Factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.sleep;

/**
 * Created by Владимир on 19.12.2015.
 */
public class EditProfile
{
    public String email = "test@mail.te";
    public String pass = "qwerty1";
    public static final String url = "http://www.relaxup.ru/";
    private WebDriver driver;

    @FindBy(linkText = "ВХОД")
    public WebElement linkEnter;

    @FindBy(id = "loginform-email")
    public WebElement textBoxLogin;

    @FindBy(id = "loginform-password")
    public WebElement textBoxPassword;

    @FindBy(name = "login-button")
    public WebElement loginButton;

    @FindBy(className = "dropdown-toggle")
    public WebElement linkMyProfile;

    @FindBy(xpath = "//*[@id=\"menu-top\"]/ul/li[4]/ul/li[2]/a")
    public WebElement linkEditProfile;

    @FindBy(id = "user-username")
    public WebElement textBoxName;

    @FindBy(id = "user-email")
    public WebElement textBoxEmail;

    @FindBy(xpath = "//*[@id=\"user-form\"]/div[4]/div/button")
    public WebElement saveButton;

    @FindBy(id = "user-password")
    public WebElement textBoxNewPassword;

    @FindBy(id = "user-password_repeat")
    public WebElement textBoxNewPasswordRepeat;

    @FindBy(xpath = "//*[@id=\"user-password-form\"]/div[3]/div/button")
    public WebElement saveButton2;

    public void open() {
        driver.get(url);
    }

    public EditProfile(WebDriver driver) {
        this.driver = driver;
    }

    public void close() {
        driver.quit();
    }

    public String getTitle()
    {
        return driver.getTitle();
    }

    public void loginRelaxup()
    {
        linkEnter.click();
        textBoxLogin.sendKeys(email);
        textBoxPassword.sendKeys(pass);
        loginButton.click();
    }
    public void pause(int time)
    {
        try
        {
            sleep(time);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isTextPresent(String text)
    {

        boolean result = false;
        String data = driver.getPageSource();
        result = data.contains(text);
        return result;
    }
}
