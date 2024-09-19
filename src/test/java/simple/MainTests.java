package simple;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import simple.page.PracticeForm;
import simple.page.TableForm;
import simple.utils.TestUtils;

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
        driver.get(TestUtils.getProperty("demoqa.com"));
    }

    @Test
    public void submitPracticeFormTest() {

        int length = 10;
        String postfix = "@mail.ru";
        String firstName = RandomStringUtils.random(length, true, false);
        String lastName = RandomStringUtils.random(length, true, false);
        String email = RandomStringUtils.random(length, true, false) + postfix;
        String mobile = RandomStringUtils.random(length, false, true);
        String address = RandomStringUtils.random(length, true, false);


        PracticeForm practiceForm = new PracticeForm(driver);

        practiceForm.setFirstName(firstName);
        practiceForm.setLastName(lastName);
        practiceForm.setEmail(email);
        practiceForm.genderClick();
        practiceForm.setMobile(mobile);
        practiceForm.calendarClick();
        practiceForm.particularMonthClick();
        practiceForm.particularYearClick();
        practiceForm.particularDayClick();
        practiceForm.hobbiesSportsClick();
        practiceForm.scroll("350");
        practiceForm.setPicture(System.getProperty("user.dir") + "\\src\\test\\resources\\0.jpg");
        practiceForm.setAddress(address);
        practiceForm.stateClick();
        practiceForm.stateItemClick();
        practiceForm.cityClick();
        practiceForm.cityItemClick();
        practiceForm.submitBtnClick();

        TableForm tableForm = new TableForm(driver);
        Assertions.assertEquals(firstName + " " + lastName, tableForm.getTextFromStudentNameResult());
        Assertions.assertEquals(email, tableForm.getTextFromStudentEmailResult());
        Assertions.assertEquals("Male", tableForm.getTextFromGenderResult());
        Assertions.assertEquals(mobile, tableForm.getTextFromMobileResult());
        Assertions.assertEquals("11 June,2024", tableForm.getTextFromDateOfBirthResult());
        Assertions.assertEquals("Sports", tableForm.getTextFromHobbiesResult());
        Assertions.assertEquals("0.jpg", tableForm.getTextFromPictureResult());
        Assertions.assertEquals(address, tableForm.getTextFromAddressResult());
        Assertions.assertEquals("Uttar Pradesh Agra", tableForm.getTextFromStateAndCityResult());
    }

}
