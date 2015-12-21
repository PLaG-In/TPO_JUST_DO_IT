package Authorization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Владимир on 20.12.2015.
 */
public class Authorization
{
    public static final String alert = "Не верный email или пароль";
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

    @FindBy(xpath = "//*[@id=\"form-login\"]/div[2]/p [text()='Не верный email или пароль']")
    public WebElement alertMessage;

    public void open() {
        driver.get(url);
    }

    public Authorization(WebDriver driver) {
        this.driver = driver;
    }

    public void close() { this.driver.quit(); }

    public boolean findText(String xpathText, String message)
    {
        if (xpathText.contains(message))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isTextPresent(String text)
    {
        boolean result = false;
        String data = this.driver.getPageSource();
        result = data.contains(text);
        return result;
    }
}

