package WebDriverFactory;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class WebDrivers {

    public static WebDriver getDriver(String driverName) {

        if (driverName.equalsIgnoreCase("CHROME")) {
            //ChromeDriverManager.getInstance().setup();
            /*File file = new File(WebDrivers.class.getResource("/chromedriver.exe").getFile());*/
            /*System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());*/

            String s = File.separator;
            String path = System.getProperty("user.dir") + s + "src" + s + "test" + s + "resources" + s;
            String fileName = "chromedriver.exe";
            File file = new File(path + fileName);
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

            /*File file = new File("D:\\Никита Мельник\\Java Projects\\Gmail\\gmail\\src\\test\\resources\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());*/

            return new ChromeDriver();
        }
        else if (driverName.equalsIgnoreCase("FIREFOX")) {
            FirefoxDriverManager.getInstance().setup();
            return new FirefoxDriver();
        }
        else {
            return null;
        }
    }

}


