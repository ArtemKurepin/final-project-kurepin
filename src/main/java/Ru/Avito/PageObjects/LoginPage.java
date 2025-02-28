package Ru.Avito.PageObjects;

import Ru.Avito.Singleton.AvitoSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    WebElement loginPage;
    WebDriver driver;
    @FindBy(name = "login")
    WebElement inputLogin;
    @FindBy(name = "password")
    WebElement inputPassword;

    public LoginPage(WebElement loginPage) {
        this.driver = AvitoSingleton.getDriver();
        this.loginPage = loginPage;
    }

    public void fillLoginInput(String login) {
        inputLogin.sendKeys(login);
    }

    public void fillPasswordInput(String pass) {
        inputPassword.sendKeys(pass);
    }

    public void clickSubmit() {
        loginPage.findElement(By.tagName("button")).click();
    }

    public String getHeaderText() {
        return loginPage.findElement(By.tagName("h2")).getText();
    }
}
