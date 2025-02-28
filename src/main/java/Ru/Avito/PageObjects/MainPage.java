package Ru.Avito.PageObjects;

import Ru.Avito.Singleton.AvitoSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private Wait wait;

    private final String URL = "https://www.avito.ru/";

    @FindBy(className = "styles-module-input-rA1dB")
    private WebElement input_Element;
    @FindBy(tagName = "body")
    private WebElement body;

    public MainPage() {
        this.driver = AvitoSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openLoginPage() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOf(body));
    }


}
