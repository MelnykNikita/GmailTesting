package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

public class NewMessagePage extends AbstractPage<NewMessagePage> {

    @FindBy(how = How.NAME, using = "to")
    private WebElement recipientPlaceholder;
    @FindBy(how = How.NAME, using = "subjectbox")
    private WebElement messageTitlePlaceholder;
    @FindBy(how = How.XPATH, using = "//div[@role='textbox']")
    private WebElement textBox;
    @FindBy(how = How.XPATH, using = "//*[.='Отправить' or .='Send']")
    private WebElement sendButton;

    public NewMessagePage(WebDriver driver) {
        super(driver);
    }

    @Step
    public NewMessagePage inputRecipientEmail(String recipientEmail) {
        inputData(recipientPlaceholder, recipientEmail);
        return this;
    }

    @Step
    public NewMessagePage inputMessageTitle(String msgTitle) {
        inputData(messageTitlePlaceholder, msgTitle);
        return this;
    }

    @Step
    public NewMessagePage inputMessageInTextbox(String msg) {
        inputData(textBox, msg);
        return this;
    }

    @Step
    public ProfilePage clickOnSendButton() {
        clickElement(sendButton);
        return new ProfilePage(driver);
    }

}
