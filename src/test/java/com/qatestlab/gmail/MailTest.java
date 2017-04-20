package com.qatestlab.gmail;

import org.testng.annotations.Test;
import Mail.Mail;

public class MailTest {

    private final String EMAIL = "NikitaMelnikQATestLab@gmail.com";
    private final String PASSWORD = "QATestLab22121989";
    private final String MESSAGE_TITLE = "TEST";
    private final String MESSAGE = "hello, this is test module";

    @Test
    public void verifyMessage() {
        Mail.getLink(EMAIL, PASSWORD, "Inbox", MESSAGE_TITLE, MESSAGE);

    }
}
