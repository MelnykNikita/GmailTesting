package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

public class EmailPage extends AbstractPage<EmailPage> {

    @FindBy(how = How.ID, using = "Email")
    private WebElement emailPlaceholder;

    @FindBy(how = How.ID, using = "next")
    private WebElement nextButton;

    public EmailPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public EmailPage inputEmail(String email) {
        inputData(emailPlaceholder, email);
        return this;
    }

    @Step
    public PasswordPage clickNextButton() {
        clickElement(nextButton);
        return new PasswordPage(driver);
    }
}
