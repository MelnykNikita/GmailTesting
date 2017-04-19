package com.qatestlab.gmail;

import dataProvider.DataProviderSource;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.EmailPage;
import pages.ProfilePage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import testListener.TestListener;

@Listeners(TestListener.class)
@Features("Verify possibility to send letter to myself")
@Stories("Google Chrome browser")
public class GmailTest {

    private static WebDriver driver;

    ProfilePage profilePage;

    private final String LINK = "http://mail.google.com";
    private final String MESSAGE_TITLE = "TEST";
    private final String MESSAGE = "hello, this is test module";
    private final String RECIPIENT_EMAIL = "NikitaMelnikQATestLab@gmail.com";

    @BeforeClass
    public void setUpDriver() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

    @TestCaseId("Test-1")
    @Test(dataProvider = "user1", dataProviderClass = DataProviderSource.class)
    public void verifyMessageSentToMyself(String email, String password) {
        setPreconditions(email, password);
        profilePage.clickOnWriteButton()
                .inputRecipientEmail(RECIPIENT_EMAIL)
                .inputMessageTitle(MESSAGE_TITLE)
                .inputMessageInTextbox(MESSAGE)
                .clickOnSendButton()
                .clickOnInboxLink();
        Assert.assertTrue(profilePage
                .getLetterTitle()
                .getText()
                .equalsIgnoreCase(MESSAGE_TITLE), "Element is not present...");
    }

    @Step
    private void setPreconditions(String email, String password) {
        driver.get(LINK);
        profilePage = new EmailPage(driver)
                .inputEmail(email)
                .clickNextButton()
                .inputPassword(password)
                .clickOnSignInButton();
    }

}