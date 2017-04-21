package WebDriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class WebDrivers {

    public static WebDriver getDriver(String driverName) {

        if (driverName.equalsIgnoreCase("CHROME")) {
            //ChromeDriverManager.getInstance().setup();
            String resource = getResource("/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", resource);

            /*ChromeOptions options = new ChromeOptions();
            addExtensionToChromeFromResources(options,"Adblock-Plus_v1.13.2.crx");*/

            return new ChromeDriver();
        }
        else if (driverName.equalsIgnoreCase("FIREFOX")) {
            //FirefoxDriverManager.getInstance().setup();
            String resource = getResource("/geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", resource);

            return new FirefoxDriver();
        }
        else {
            return null;
        }
    }

    private static void setProperty(String executableFileName, String fileName) {
        String s = File.separator;
        String path = System.getProperty("user.dir") + s + "src" + s + "test" + s + "resources" + s;
        File file = new File(path + executableFileName);
        System.setProperty(fileName, file.getAbsolutePath());
    }

    private static String getResource(String resourceName) {
        try {
            return Paths.get(WebDrivers.class.getResource(resourceName).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resourceName;
    }

    private static void addExtensionToChromeFromResources(ChromeOptions options, String nameOfExtension) {
        String s = File.separator;
        String path = System.getProperty("user.dir") + s + "src" + s + "test" + s + "resources" + s;
        options.addExtensions(new File(path + nameOfExtension));
    }

}


