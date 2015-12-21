package ShowEventAndWriteComment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.sleep;

/**
 * Created by Владимир on 20.12.2015.
 */
public class ShowEventAndWriteComment
{
    public static final String email = "2131***()&&&";
    public static final String pass = "qwerty1";
    public static final String url = "http://www.relaxup.ru/";
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"event_16577\"]/div/div[1]")
    public WebElement linkEvent;

    @FindBy(linkText = "Перейти на страницу мероприятия")
    public WebElement linkEventMain;

    @FindBy(linkText = "ВХОД")
    public WebElement linkEnter;

    @FindBy(id = "loginform-email")
    public WebElement textBoxLogin;

    @FindBy(id = "loginform-password")
    public WebElement textBoxPassword;

    @FindBy(name = "login-button")
    public WebElement loginButton;

    @FindBy(id = "comments-text")
    public WebElement textBoxComment;

    @FindBy(className = "green-white-btn")
    public WebElement sendButton;

    public void open()
    {
        driver.get(url);
    }

    public ShowEventAndWriteComment(WebDriver driver) {
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
        String data = this.driver.getPageSource();
        result = data.contains(text);
        return result;
    }
}
