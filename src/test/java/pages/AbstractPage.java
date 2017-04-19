package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage<T extends AbstractPage<T>> {

    protected static WebDriver driver;
    protected WebDriverWait wait;
    protected int maxTimeWait = 10;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, maxTimeWait);
        PageFactory.initElements(driver, this);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public T waitForElementToBeClickable(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) this;
    }

    public T waitForVisibilityOfElement(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) this;
    }

    public void clickElement(WebElement webElement) {
        waitForElementToBeClickable(webElement);
        webElement.click();
    }

    public void inputData(WebElement webElement, String data) {
        waitForVisibilityOfElement(webElement);
        webElement.clear();
        webElement.sendKeys(data);
    }

    public boolean isElementPresent(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
