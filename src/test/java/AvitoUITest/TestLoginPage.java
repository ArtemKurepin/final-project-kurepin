package AvitoUITest;

import io.qameta.allure.*;
import Ru.Avito.PageObjectsUI.LoginPage;
import Ru.Avito.PageObjectsUI.MainPage;
import Ru.Avito.Utils.LogSaver;
import Ru.Avito.Utils.Singleton.AvitoSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLoginPage {
    public MainPage mp;
    public LoginPage lp;
    public String phoneNumber = "+375293333333";
    public String password = "124569787";

    @Epic("UI Тесты")
    @BeforeEach
    public void before() {

        mp = new MainPage();
        mp.openHomePage();
        lp = new LoginPage(mp.openLoginElement());

    }


    @Test
    @Order(1)
    public void testOpenLoginPage() {
        Assertions.assertEquals("Вход", lp.getHeaderText());
    }

    @Test
    @Order(2)
    public void testFillLoginWithoutPass() {

        String actual = lp.fillLoginInput(phoneNumber).getAttribute("value");
        Assertions.assertEquals(phoneNumber, actual);
    }

    @Test
    @Order(3)
    public void testFillPasswordWithoutLogin() {
        String actual = lp.fillPasswordInput(password).getAttribute("value");
        Assertions.assertEquals(password, actual);
    }

    @AfterAll
    public static void afterAll() {
        LogSaver.logSave();
        if (AvitoSingleton.checkDriverStatus()) {
            AvitoSingleton.driverQuit();
        }
    }
}
