package Ru.Avito.PageObjectsUI;

import Ru.Avito.Utils.Singleton.AvitoSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private Wait wait;

    private final String URL = "https://www.avito.ru";

    @FindBy(className = "styles-module-input-rA1dB")
    public WebElement inputElement;
    @FindBy(tagName = "body")
    private WebElement body;
    @FindBy(xpath = "//a[@data-marker='header/login-button']")
    private WebElement loginButton;
    @FindBy(xpath = "//ul[@data-marker=\"side-block/navbar/about\"]/li[1]")
    private WebElement copyrightElement;

    public MainPage() {
        this.driver = AvitoSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(this.driver, this);

    }
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(this.driver, this);

    }
    public void openHomePage() {
        driver.get(URL);
    }

    public String getCopyrightText() {
        wait.until(ExpectedConditions.visibilityOf(body));
        return copyrightElement.getText();
    }

    public WebElement openLoginElement() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class=\"AuthorizationMainScreen-content-rdZSs\"]")));
        return driver.findElement(By.xpath("//div[@class=\"AuthorizationMainScreen-content-rdZSs\"]"));
    }
}
