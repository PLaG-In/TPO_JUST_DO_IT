package AddEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.sleep;

/**
 * Created by Владимир on 19.12.2015.
 */
public class AddEvent
{
    public static final String email = "test@mail.te";
    public static final String pass = "qwerty1";
    public static final String url = "http://www.relaxup.ru/";
    private WebDriver driver;

    @FindBy(linkText = "ВХОД")
    public WebElement linkEnter;

    @FindBy(className = "dropdown-toggle")
    public WebElement linkMyProfile;

    @FindBy(xpath = "//*[@id=\"menu-top\"]/ul/li[4]/ul/li[3]/a")
    public WebElement linkMyEvents;

    @FindBy(className = "edit_link")
    public WebElement linkEditEvent;

    @FindBy(linkText = "ДОБАВИТЬ МЕРОПРИЯТИЕ")
    public WebElement linkAddEvent;

    @FindBy(id = "loginform-email")
    public WebElement textBoxLogin;

    @FindBy(id = "loginform-password")
    public WebElement textBoxPassword;

    @FindBy(name = "login-button")
    public WebElement loginButton;

    @FindBy(linkText = "Расписание и цены")
    public WebElement linkTimetableAndPrice;

    @FindBy(id = "event-contact_phone")
    public WebElement textBoxPhone;

    @FindBy(id = "event-contact_email")
    public WebElement textBoxEmail;

    @FindBy(id = "event-title")
    public WebElement textBoxTitle;

    @FindBy(xpath = "//*[@id=\"event-event_location\"]")
    public WebElement chooseListLocation;

    @FindBy(id = "event-eventLocations_0_phone_order")
    public WebElement textBoxSecPhone;

    @FindBy(id = "event-eventLocations_0_eventTimesheets_0_end_date")
    public WebElement dataTimeEndDate;

    @FindBy(id = "event-eventLocations_0_eventTimesheets_0_end_time")
    public WebElement dataTimeEndTime;

    @FindBy(xpath = "//*[@id=\"event-form\"]/div/div[2]/div/div[4]/div/button")
    public WebElement xButton;

    public void open() {
        driver.get(url);
    }

    public AddEvent(WebDriver driver) {
        this.driver = driver;
    }

    public void close() {
        driver.quit();
    }

    public String getTitle()
    {
        return driver.getTitle();
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

    public void goToEditEvent()
    {
        linkMyProfile.click();
        linkMyEvents.click();
        linkEditEvent.click();
    }

    public void loginRelaxup()
    {
        linkEnter.click();
        textBoxLogin.sendKeys(email);
        textBoxPassword.sendKeys(pass);
        loginButton.click();
    }

    public boolean isTextPresent(String text)
    {

        boolean result = false;
        String data = driver.getPageSource();
        result = data.contains(text);
        return result;
    }
}
