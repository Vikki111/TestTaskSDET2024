package simple;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import simple.page.PracticeForm;
import simple.page.TableForm;

public class MainTests {

    WebDriver driver;

    @AfterEach
    public void close() {
        driver.close();
    }

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");

    }

    @Test
    public void seleniumTest() {

        PracticeForm practiceForm = new PracticeForm(driver);

        practiceForm.setFirstName("Test first name");
        practiceForm.setLastName("Test last name");
        practiceForm.setEmail("test@mail.ru");
        practiceForm.genderClick();
        practiceForm.setMobile("1234567890");
        practiceForm.calendarClick();
        practiceForm.particularMonthClick();
        practiceForm.particularYearClick();
        practiceForm.particularDayClick();
        practiceForm.hobbiesSportsClick();
        practiceForm.scroll("350");
        practiceForm.setPicture(System.getProperty("user.dir")+"\\src\\test\\resources\\0.jpg");
        practiceForm.setAddress("test address");
        practiceForm.stateClick();
        practiceForm.stateItemClick();
        practiceForm.cityClick();
        practiceForm.cityItemClick();
        practiceForm.submitBtnClick();

        TableForm tableForm = new TableForm(driver);
        Assertions.assertEquals("Test first name Test last name", tableForm.getTextFromStudentNameResult());
        Assertions.assertEquals("test@mail.ru", tableForm.getTextFromStudentEmailResult());
        Assertions.assertEquals("Male", tableForm.getTextFromGenderResult());
        Assertions.assertEquals("1234567890", tableForm.getTextFromMobileResult());
        Assertions.assertEquals("11 June,2024", tableForm.getTextFromDateOfBirthResult());
        Assertions.assertEquals("Sports", tableForm.getTextFromHobbiesResult());
        Assertions.assertEquals("0.jpg", tableForm.getTextFromPictureResult());
        Assertions.assertEquals("test address", tableForm.getTextFromAddressResult());
        Assertions.assertEquals("Uttar Pradesh Agra", tableForm.getTextFromStateAndCityResult());

        int i = 1;
    }

}
