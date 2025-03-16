package Ru.Avito.Utils.Singleton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.security.PublicKey;
import java.time.Duration;

public class AvitoSingleton {
    private static WebDriver driver;

    private AvitoSingleton() {

    }

    public static ChromeOptions setOptions() {
        ChromeOptions options = new ChromeOptions();
        //  options.addArguments("--headless=new"); // Для Chrome 112+
        options.addArguments("--disable-gpu", "--no-sandbox", "--window-size=1920,1080");
        return options;
    }

    public static WebDriver getDriver() {

        if (driver == null) {
           setupChromeDriver();
           setOptions();
        }
        //  driver.manage().window().maximize();
        return driver;
    }

    private static void setupChromeDriver() {
        WebDriverManager.chromedriver()
//                .clearDriverCache()
                .cachePath("C:\\jenkins\\webdriver_cache")
                .forceDownload()
                .avoidBrowserDetection()
                .setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--start-maximized",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*"
        );

        driver = new ChromeDriver(options);
    }

    private static void configureDriver() {
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(30))
                .pageLoadTimeout(Duration.ofSeconds(60));
    }
    public static void driverQuit() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static boolean checkDriverStatus() {
        if (driver != null) {
            return true;
        } else return false;
    }

}
