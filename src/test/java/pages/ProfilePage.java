package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

public class ProfilePage extends AbstractPage<ProfilePage> {

    //private static String MESSAGE_TITLE;
    private static String xpathForLetterTitleLocator;

    @FindBy(how = How.XPATH, using = "//*[.='НАПИСАТЬ' or .='COMPOSE']")
    private WebElement writeButton;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Входящие')]")
    private WebElement inboxLink;

    @FindBy(how = How.XPATH, using = "//body//tbody/tr[1]/td[6]/div/div/div/span[1]/b")
    private WebElement letterTitle;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step
    public NewMessagePage clickOnWriteButton() {
        clickElement(writeButton);
        return new NewMessagePage(driver);
    }

    @Step
    public ProfilePage clickOnInboxLink() {
        clickElement(inboxLink);
        return this;
    }

    public WebElement getLetterTitle() {
        return letterTitle;
    }
}
