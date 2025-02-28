package Ru.Avito.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InputUtils {


    public static void inputText(WebElement input, String text) {
        input.sendKeys(text);
    }

    public static List<WebElement> getSuggestDropdownItems(WebDriver driver) {
        WebElement suggestDiv = driver.findElement(By.className("suggest-dropdownItems-El1Sw"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(suggestDiv));
        List<WebElement> allSuggestions = suggestDiv.findElements(By.xpath(
                "//button[@data-marker='suggest/list/custom-option']"));
        return allSuggestions;
    }

    public static void clickOnSelectedItem(int itemNumber, List<WebElement> items) {
        items.get(itemNumber).click();
    }

    public static String getTextOnSelectedItem(int itemNumber, List<WebElement> items) {
       return items.get(itemNumber).getText();
    }
}
