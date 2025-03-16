package Ru.Avito.PageObjectsUI;

import Ru.Avito.Utils.Singleton.AvitoSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class LoginPage {
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    WebDriver driver;
    WebDriverWait wait;
    WebElement loginPage;

    private static final String FORM_PATH = "//form[@data-marker=\"login-form\"]";
    private static final By FORM_HEADER = By.xpath("//h2[contains(text(), 'Вход')]");
    private static final By LABEL_LOGIN = By.xpath("//label[@data-marker=\"login-form/login\"]");
    private static final By INPUT_PASSWORD = By.xpath("//input[@name='password']");

    public LoginPage(WebElement loginPage) {
        this.driver = AvitoSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.loginPage = loginPage;
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage() {
        this.driver = AvitoSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(this.driver, this);
    }

    public WebElement fillLoginInput(String login) {
        wait.until(ExpectedConditions.presenceOfElementLocated(LABEL_LOGIN));
        driver.findElement(LABEL_LOGIN).findElement(By.tagName("input")).sendKeys(login);
        return driver.findElement(LABEL_LOGIN).findElement(By.tagName("input"));
    }

    public WebElement fillPasswordInput(String pass) {
        wait.until(ExpectedConditions.presenceOfElementLocated(INPUT_PASSWORD));
        driver.findElement(INPUT_PASSWORD).sendKeys(pass);
        return driver.findElement(INPUT_PASSWORD);
    }

    public void clickSubmit() {
        driver.findElement(By.xpath(FORM_PATH)).
                findElement(By.tagName("Button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public String getNonCorrectLoginPass() {
        clickSubmit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FORM_PATH + "/p")));
        return driver.findElement(By.xpath(FORM_PATH + "/p")).getText();
    }

    public String getHeaderText() {
        WebElement headerElement = wait.until(ExpectedConditions
                .presenceOfElementLocated(FORM_HEADER));
        return headerElement.getText();
    }
}
