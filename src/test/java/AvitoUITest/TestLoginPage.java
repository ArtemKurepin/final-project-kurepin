package AvitoUITest;

import Ru.Avito.PageObjectsUI.LoginPage;
import Ru.Avito.PageObjectsUI.MainPage;
import Ru.Avito.Utils.LogSaver;
import Ru.Avito.Utils.Singleton.AvitoSingleton;
import org.junit.jupiter.api.*;

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

    @AfterAll
    public static void saveLogs() {
        LogSaver.logSave();
    }
}
