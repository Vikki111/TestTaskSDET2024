package simple.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By addCustomerBtn = By.xpath("//button[@ng-class=\"btnClass1\"]");
    private By customersListBtn = By.xpath("//button[@ng-click=\"showCust()\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void addCustomerClick() {
        driver.findElement(addCustomerBtn).click();
    }

    public By getAddCustomerBtn() {
        return this.addCustomerBtn;
    }

    public By getCustomersListBtn() {
        return this.customersListBtn;
    }

    public void listCustomersClick() {
        driver.findElement(customersListBtn).click();
    }

}
