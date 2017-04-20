package com.qatestlab.gmail;

import dataProvider.DataProviderSource;
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
public class GmailTest extends BaseTest{

    ProfilePage profilePage;

    private final String LINK = "http://mail.google.com";
    private final String MESSAGE_TITLE = "TEST";
    private final String MESSAGE = "hello, this is test module";
    private final String RECIPIENT_EMAIL = "NikitaMelnikQATestLab@gmail.com";

    @BeforeClass
    public void setUpDriver() {
        super.setUpDriver();
    }

    @AfterClass
    public void quitDriver() {
        super.quitDriver();
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
                .equalsIgnoreCase(MESSAGE_TITLE), "FAILURE...");
    }

    @Step
    private void setPreconditions(String email, String password) {
        getDriver().get(LINK);
        profilePage = new EmailPage(getDriver())
                .inputEmail(email)
                .clickNextButton()
                .inputPassword(password)
                .clickOnSignInButton();
    }

}