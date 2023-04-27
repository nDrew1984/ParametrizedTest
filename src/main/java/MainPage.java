import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class MainPage extends BasePage {
    private final static Logger LOGGER = LoggerFactory.getLogger(MainPage.class);
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final By nameField = By.id("name");
    private final By dateField = By.id("date");
    private final By testButton = By.xpath("//button[@type=\"submit\"]");
    private final By alert = By.xpath("//div[contains(@class, \"alert\")]/h3");

    public void fillForm(String name, String date) {
        LOGGER.info("Filling form...");
        sendInputText(nameField, name);
        sendInputText(dateField, date);
        LOGGER.info("Form has been filled");
    }

    public List<String> getFormData() {
        return getInputData(nameField, dateField);
    }

    public void clickTestButton() {
        clickElement(testButton);
    }

    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(alert));
        return driver.findElement(alert).getText().replace(",", "");
    }

    public void clearFields() {
        driver.findElement(nameField).clear();
        driver.findElement(dateField).clear();
    }


}
