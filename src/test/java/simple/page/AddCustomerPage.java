package simple.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomerPage {
    private WebDriver driver;

    private By firstNameField = By.xpath("//input[@ng-model=\"fName\"]");
    private By lastNameField = By.xpath("//input[@ng-model=\"lName\"]");
    private By postCodeField = By.xpath("//input[@ng-model=\"postCd\"]");
    private By submitBtn = By.xpath("//button[@type=\"submit\"]");

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String str) {
        driver.findElement(firstNameField).sendKeys(str);
    }

    public By getFirstNameField() {
        return this.firstNameField;
    }

    public void setLastName(String str) {
        driver.findElement(lastNameField).sendKeys(str);
    }

    public void setPostCode(String str) {
        driver.findElement(postCodeField).sendKeys(str);
    }

    public void submitClick() {
        driver.findElement(submitBtn).click();
    }
}
