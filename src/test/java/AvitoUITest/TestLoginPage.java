package AvitoUITest;

import Ru.Avito.PageObjects.LoginPage;
import Ru.Avito.PageObjects.MainPage;
import Ru.Avito.Singleton.AvitoSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PublicKey;

public class TestLoginPage {
    public MainPage mp;
    public LoginPage lp;
    public String phoneNumber = "+375293333333";
    public String password = "124569787";

    @BeforeEach
    public void beforeEach() {
        mp = new MainPage();
        mp.openHomePage();
        lp = new LoginPage(mp.openLoginElement());

    }

    @Test
    public void testOpenLoginPage() {
        Assertions.assertEquals("Вход", lp.getHeaderText());
    }

    @Test
    public void testFillLoginWithoutPass() {
        String actual = lp.fillLoginInput(phoneNumber).getAttribute("value");
        Assertions.assertEquals(phoneNumber, actual);
    }

    @Test
    public void testFillPasswordWithoutLogin() {
        String actual = lp.fillPasswordInput(password).getAttribute("value");
        Assertions.assertEquals(password, actual);
    }

    @Test
    public void testFullLogin() {
        lp.fillLoginInput(phoneNumber);
        lp.fillPasswordInput(password);
        lp.clickSubmit();
        String actual = lp.getNonCorrectLoginPass();
        Assertions.assertEquals("Неправильный телефон или почта", actual);
    }

    @AfterEach
    public void driverQuit() {
        AvitoSingleton.driverQuit();
    }
}
