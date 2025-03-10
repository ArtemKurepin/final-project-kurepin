package AvitoUITest;

import Ru.Avito.PageObjects.InputUtils;
import Ru.Avito.PageObjects.MainPage;
import Ru.Avito.Singleton.AvitoSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


    @AfterEach
    public void driverQuit() {
        AvitoSingleton.driverQuit();
    }

}
