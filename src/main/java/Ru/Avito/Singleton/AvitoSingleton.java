package Ru.Avito.Singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AvitoSingleton {
    private static WebDriver driver;

    private AvitoSingleton() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void driverQuit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
