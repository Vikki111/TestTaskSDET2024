package simple.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListCustomersPage {

    private WebDriver driver;

    private By firstNameSortLink = By.xpath("//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]");
    private By rowOfTable = By.xpath("//tr[1][@class=\"ng-scope\"]");
    private By tableRows = By.xpath("//tbody/tr");


    public ListCustomersPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCellByRowNumberForFirstName(String rowNumber) {
        return driver.findElement(By.xpath(String.format("//tr[%s][@class=\"ng-scope\"]/td[1]", rowNumber))).getText();
    }

    public void deleteCustomerByRowNumber(String rowNumber) {
        driver.findElement(By.xpath(String.format("//tr[%s][@class=\"ng-scope\"]/td[5]/button", rowNumber))).click();
    }

    public void firstNameSortLinkClick() {
        driver.findElement(firstNameSortLink).click();
    }

    public int getSizeOfTable() {
        return driver.findElements(tableRows).size();
    }

    public By getFirstNameSortLink() {
        return this.firstNameSortLink;
    }
}
