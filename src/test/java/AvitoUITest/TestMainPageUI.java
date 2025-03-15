package AvitoUITest;

import Ru.Avito.PageObjectsUI.InputUtils;
import Ru.Avito.PageObjectsUI.MainPage;
import Ru.Avito.Utils.LogSaver;
import Ru.Avito.Utils.Singleton.AvitoSingleton;
import org.junit.jupiter.api.*;



public class TestMainPageUI {
    public MainPage mp;

    @BeforeEach
    public void beforeEach() {
        mp = new MainPage();
        mp.openHomePage();
    }

    @Test
    public void testMainPage() {
        Assertions.assertEquals("© ООО «КЕХ еКоммерц» 2007–2025.", mp.getCopyrightText());
    }

    @Test
    public void testInputSearch() {
        mp.openHomePage();
        InputUtils.inputText(mp.inputElement, "кирпич");
        String actual = InputUtils.getTextOnSelectedItem(1,
                InputUtils.getSuggestDropdownItems(AvitoSingleton.getDriver()));
        Assertions.assertEquals("кирпич", actual);
    }


    @AfterAll
    public static void saveLogs() {
        LogSaver.logSave();
            AvitoSingleton.driverQuit();
    }
}
