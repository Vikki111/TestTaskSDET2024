package simple.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

    @FindBy(xpath = "//input[@ng-model='fName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@ng-model='lName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@ng-model='postCd']")
    private WebElement postCodeField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    public AddCustomerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public By getFirstNameField() {
        return By.xpath("//input[@ng-model=\"fName\"]");
    }

    public AddCustomerPage setFirstName(String str) {
        firstNameField.sendKeys(str);
        return this;
    }

    public AddCustomerPage setLastName(String str) {
        lastNameField.sendKeys(str);
        return this;
    }

    public AddCustomerPage setPostCode(String str) {
        postCodeField.sendKeys(str);
        return this;
    }

    public AddCustomerPage clickSubmit() {
        submitBtn.click();
        return this;
    }
}
