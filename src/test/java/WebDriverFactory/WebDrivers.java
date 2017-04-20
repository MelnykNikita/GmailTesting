package WebDriverFactory;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDrivers {

    public static WebDriver getDriver(String driver) {

        if (driver.equalsIgnoreCase("CHROME")) {
            //ChromeDriverManager.getInstance().setup();
            System.setProperty("webdriver.chrome.driver",
                    "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
            return new ChromeDriver();
        }
        else if (driver.equalsIgnoreCase("FIREFOX")) {
            FirefoxDriverManager.getInstance().setup();
            return new FirefoxDriver();
        }
        else {
            return null;
        }
    }
}


