import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestForm extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestForm.class);

    @DisplayName("Test Error Messages 1")
    @Description("Testing with testData.csv")
    @ParameterizedTest
    @CsvFileSource(resources = "testData.csv", useHeadersInDisplayName = true)
    public void TestInput1(String name, String date, String expectedMessage) {
        BasePage basePage = new BasePage(driver);
        MainPage mainPage = new MainPage(driver);

        basePage.navigate();
        mainPage.fillForm(name, date);
        List<String> filledData = mainPage.getFormData();
        LOGGER.info("Filled data: " + filledData);
        mainPage.clickTestButton();

        String actualMessage = mainPage.getMessage();
        if (expectedMessage.equals(actualMessage)) {
            LOGGER.info("Message -> " + "\t" + actualMessage);
        } else {
            LOGGER.warn("Expected message: " + "\t" + expectedMessage);
            LOGGER.warn("Actual message: " + "\t" + actualMessage);
        }
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
