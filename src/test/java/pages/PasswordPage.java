package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

public class PasswordPage extends AbstractPage<PasswordPage>{

    @FindBy(how = How.ID, using = "Passwd")
    private WebElement passwordPlaceholder;
    @FindBy(how = How.ID, using = "signIn")
    private WebElement signInButton;

    public PasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public PasswordPage inputPassword(String password) {
        inputData(passwordPlaceholder, password);
        return this;
    }

    @Step
    public ProfilePage clickOnSignInButton() {
        clickElement(signInButton);
        return new ProfilePage(driver);
    }



}
