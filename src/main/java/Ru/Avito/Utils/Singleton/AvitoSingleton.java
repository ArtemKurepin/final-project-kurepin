package Ru.Avito.Utils.Singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AvitoSingleton {
   private static WebDriver driver;

    private AvitoSingleton() {

    }

    public static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
      //  options.addArguments("--headless=new"); // Для Chrome 112+
        options.addArguments("--disable-gpu", "--no-sandbox", "--window-size=1920,1080");
        if (driver == null) {
            driver = new ChromeDriver(options);
        }
        //  driver.manage().window().maximize();
        return driver;
    }

    public static void driverQuit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
