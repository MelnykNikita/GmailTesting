package com.qatestlab.gmail;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;

@Listeners(TestListener.class)
@Features("Verify possibility to send letter to myself")
@Stories("Google Chrome browser")
public class GmailTest {

    private static WebDriver driver;
    private WebDriverWait wait;

    private final String LINK = "http://mail.google.com";
    private final String MESSAGE_TITLE = "TEST";
    private final String MESSAGE = "hello, this is test module";
    private final String EMAIL = "NikitaMelnikQATestLab";
    private final String PASSWORD = "QATestLab22121989";
    private final String RECIPIENT_EMAIL = "NikitaMelnikQATestLab@gmail.com";

    private WebElement emailPlaceholder;
    private WebElement passwordPlaceholder;
    private WebElement nextButton;
    private WebElement signInButton;
    private WebElement writeButton;
    private WebElement recipientPlaceholder;
    private WebElement messageTitlePlaceholder;
    private WebElement textBox;
    private WebElement sendButton;
    private WebElement inboxLink;

    private By emailPlaceholderLocator = new By.ById("Email");
    private By passwordPlaceholderLocator = new By.ById("Passwd");
    private By nextButtonLocator = new By.ById("next");
    private By signInButtonLocator = new By.ById("signI"); //FAIL
    private By writeButtonLocator = new By.ByXPath("//*[.='НАПИСАТЬ' or .='COMPOSE']");
    private By recipientPlaceholderLocator = new By.ByName("to");
    private By messageTitlePlaceLocator = new By.ByName("subjectbox");
    private By textBoxLocator = new By.ByXPath("//div[@role='textbox']");
    private By sendButtonLocator = new By.ByXPath("//*[.='Отправить' or .='Send']");
    private By inboxLinkLocator = new By.ByXPath("//a[contains(text(),'Входящие')]");
    private By letterTitleLocator = new By.ByXPath("//b[contains(text(),'" + MESSAGE_TITLE + "')]");

    @BeforeClass
    public void setUpDriver() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

    @TestCaseId("Test-1")
    @Test(dataProvider = "user1", dataProviderClass = DataProviderSource.class)
    public void verifyMessageSentToMyself(String email, String password) {
        setPreconditions(email, password);
        clickOnWriteButton();
        inputRecipientEmail(RECIPIENT_EMAIL);
        inputMessageTitle(MESSAGE_TITLE);
        inputMessageInTextbox(MESSAGE);
        clickOnSendButton();
        clickOnInboxLink();
        Assert.assertTrue(isElementPresent(letterTitleLocator), "Element is not present");
    }

    @Step
    private void setPreconditions(String email, String password) {
        driver.get(LINK);
        logIn(email, password);
    }

    @Step
    private void logIn(String email, String password) {
        inputEmail(email);
        inputPassword(password);
    }

    @Step
    private void inputEmail(String email) {
        emailPlaceholder = wait.until(ExpectedConditions.presenceOfElementLocated(emailPlaceholderLocator));
        emailPlaceholder.click();
        emailPlaceholder.sendKeys(email);
        nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
        nextButton.click();
    }

    @Step
    private void inputPassword(String password) {
        passwordPlaceholder = wait.until(ExpectedConditions.presenceOfElementLocated(passwordPlaceholderLocator));
        passwordPlaceholder.click();
        passwordPlaceholder.sendKeys(password);
        signInButton = wait.until(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        signInButton.click();
    }

    @Step
    private void clickOnWriteButton() {
        writeButton = wait.until(ExpectedConditions.presenceOfElementLocated(writeButtonLocator));
        writeButton.click();
    }

    @Step
    private void inputRecipientEmail(String email) {
        recipientPlaceholder = wait.until(ExpectedConditions.presenceOfElementLocated(recipientPlaceholderLocator));
        recipientPlaceholder.click();
        recipientPlaceholder.sendKeys(email);
    }

    @Step
    private void inputMessageTitle(String title) {
        messageTitlePlaceholder = wait.until(ExpectedConditions.presenceOfElementLocated(messageTitlePlaceLocator));
        messageTitlePlaceholder.sendKeys(title);
    }

    @Step
    private void inputMessageInTextbox(String message) {
        textBox = wait.until(ExpectedConditions.presenceOfElementLocated(textBoxLocator));
        textBox.click();
        textBox.sendKeys(message);
    }

    @Step
    private void clickOnSendButton() {
        sendButton = wait.until(ExpectedConditions.presenceOfElementLocated(sendButtonLocator));
        sendButton.click();
    }

    @Step
    private void clickOnInboxLink() {
        inboxLink = wait.until(ExpectedConditions.presenceOfElementLocated(inboxLinkLocator));
        inboxLink.click();
    }

    private boolean isElementPresent(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}