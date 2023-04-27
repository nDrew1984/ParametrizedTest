import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected final String url = "https://www.kesmarki.com/test_form";

    public void navigate() {
        driver.get(url);
        LOGGER.info("Navigated to: " + url);
    }

    public void sendInputText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    public List<String> getInputData(By... locators) {
        List<String> result = new ArrayList<>();
        for (By locator : locators) {
            result.add(driver.findElement(locator).getAttribute("value"));
        }
        return result;
    }

    public void clickElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
}
