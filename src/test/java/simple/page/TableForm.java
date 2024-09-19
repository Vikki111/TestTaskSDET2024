package simple.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TableForm {
    private WebDriver driver;

    public TableForm(WebDriver driver) {
        this.driver = driver;
    }

    private By studentNameResult = By.xpath("//td[text()=\"Student Name\"]/../td[2]");
    private By studentEmailResult = By.xpath("//td[text()=\"Student Email\"]/../td[2]");
    private By genderResult = By.xpath("//td[text()=\"Gender\"]/../td[2]");
    private By mobileResult = By.xpath("//td[text()=\"Mobile\"]/../td[2]");
    private By dateOfBirthResult = By.xpath("//td[text()=\"Date of Birth\"]/../td[2]");
    private By hobbiesResult = By.xpath("//td[text()=\"Hobbies\"]/../td[2]");
    private By pictureResult = By.xpath("//td[text()=\"Picture\"]/../td[2]");
    private By addressResult = By.xpath("//td[text()=\"Address\"]/../td[2]");
    private By stateAndCityResult = By.xpath("//td[text()=\"State and City\"]/../td[2]");

    public String getTextFromStudentNameResult() {
        return driver.findElement(studentNameResult).getText();
    }

    public String getTextFromStudentEmailResult() {
        return driver.findElement(studentEmailResult).getText();
    }

    public String getTextFromGenderResult() {
        return driver.findElement(genderResult).getText();
    }

    public String getTextFromMobileResult() {
        return driver.findElement(mobileResult).getText();
    }

    public String getTextFromDateOfBirthResult() {
        return driver.findElement(dateOfBirthResult).getText();
    }

    public String getTextFromHobbiesResult() {
        return driver.findElement(hobbiesResult).getText();
    }

    public String getTextFromPictureResult() {
        return driver.findElement(pictureResult).getText();
    }

    public String getTextFromAddressResult() {
        return driver.findElement(addressResult).getText();
    }

    public String getTextFromStateAndCityResult() {
        return driver.findElement(stateAndCityResult).getText();
    }
}
