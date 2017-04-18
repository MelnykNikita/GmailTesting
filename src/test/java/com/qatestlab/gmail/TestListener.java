package com.qatestlab.gmail;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener extends TestListenerAdapter {
    /*@Override
    public void onTestFailure(ITestResult tr) {
        WebDriver driver = GmailTest.getDriver();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        String destinationDirectory = "target/surefire-reports/screenshots";
        new File(destinationDirectory).mkdirs();
        String destinationFile = dateFormat.format(new Date() + ".png");

        try {
            FileUtils.copyFile(screenshot, new File(destinationDirectory + "/" + destinationFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void onTestFailure(ITestResult tr) {
        //attachScreenshot();
        try {
            FileUtils.writeByteArrayToFile(new File("Screenshot"), attachScreenshot());
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        String destinationDirectory = "target/surefire-reports/screenshots";
        new File(destinationDirectory).mkdirs();
        String destinationFile = dateFormat.format(new Date() + ".png");

        try {
            FileUtils.copyFile(FileUtils.getFile("Screenshot"),
                    new File(destinationDirectory + "/" + destinationFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        attachScreenshot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] attachScreenshot() {
        byte[] screenshotAs = null;
        try {
            screenshotAs = ((TakesScreenshot) GmailTest.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshotAs;
    }
}
