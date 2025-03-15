package Ru.Avito.Utils.Singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.security.PublicKey;

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
            driver = new ChromeDriver(setOptions());
        }
        //  driver.manage().window().maximize();
        return driver;
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
