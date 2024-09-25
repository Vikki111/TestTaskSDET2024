package simple.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import simple.page.AddCustomerPage;
import simple.page.HomePage;
import simple.page.ListCustomersPage;
import simple.utils.TestUtils;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class XYZBankTests {
    WebDriver driver;

    @AfterEach
    public void close() {
        driver.close();
    }

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", TestUtils.getProperty("chromedriver.path"));
        driver = new ChromeDriver();
        driver.get(TestUtils.getProperty("globalsqa.com"));
    }

    @Test
    public void successfulAddCustomerTest() {

        String postCode = RandomStringUtils.random(10, false, true);
        String lastName = RandomStringUtils.random(10, true, true);
        String firstName = TestUtils.getFirstNameFromPostCode(postCode);

        HomePage homePage = new HomePage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getAddCustomerBtn()));
        homePage.addCustomerClick();

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(addCustomerPage.getFirstNameField()));

        addCustomerPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPostCode(postCode)
                .clickSubmit();

        Alert alert = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Test
    public void sortCustomersTest() {
        HomePage homePage = new HomePage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getCustomersListBtn()));

        homePage.listCustomersClick();

        List<String> expectedListOfFirstNames = new LinkedList<>();
        expectedListOfFirstNames.add("Albus");
        expectedListOfFirstNames.add("Harry");
        expectedListOfFirstNames.add("Hermoine");
        expectedListOfFirstNames.add("Neville");
        expectedListOfFirstNames.add("Ron");

        ListCustomersPage listCustomersPage = new ListCustomersPage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(listCustomersPage.getFirstNameSortLink()));

        listCustomersPage.firstNameSortLinkClick();
        listCustomersPage.firstNameSortLinkClick();

        List<String> actualListOfFirstNames = TestUtils.getActualListOfFirstNames(listCustomersPage);

        Assertions.assertEquals(expectedListOfFirstNames, actualListOfFirstNames);
    }

    @Test
    public void deleteCustomerTest() {

        HomePage homePage = new HomePage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getCustomersListBtn()));

        homePage.listCustomersClick();

        ListCustomersPage listCustomersPage = new ListCustomersPage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(listCustomersPage.getFirstNameSortLink()));

        List<String> actualListOfFirstNames = TestUtils.getActualListOfFirstNames(listCustomersPage);

        Map<String, String> mapNameToIndex = TestUtils.getMapNameToIndex(actualListOfFirstNames);

        String nameForDeletion = TestUtils.getNameForDeletion(actualListOfFirstNames);

        listCustomersPage.deleteCustomerByRowNumber(mapNameToIndex.get(nameForDeletion));

        actualListOfFirstNames.clear();

        actualListOfFirstNames = TestUtils.getActualListOfFirstNames(listCustomersPage);

        Assertions.assertFalse(actualListOfFirstNames.contains(nameForDeletion));
    }

    @Test
    public void addAndDeleteCustomerTest() {

        String postCode = RandomStringUtils.random(28, false, true);
        String lastName = RandomStringUtils.random(10, true, true);
        String firstName = TestUtils.getFirstNameFromPostCode(postCode);

        HomePage homePage = new HomePage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getAddCustomerBtn()));
        homePage.addCustomerClick();

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(addCustomerPage.getFirstNameField()));

        addCustomerPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPostCode(postCode)
                .clickSubmit();

        Alert alert = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getCustomersListBtn()));

        homePage.listCustomersClick();

        ListCustomersPage listCustomersPage = new ListCustomersPage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(listCustomersPage.getFirstNameSortLink()));

        List<String> actualListOfFirstNames = TestUtils.getActualListOfFirstNames(listCustomersPage);

        Map<String, String> mapNameToIndex = TestUtils.getMapNameToIndex(actualListOfFirstNames);

        String nameForDeletion = TestUtils.getNameForDeletion(actualListOfFirstNames);

        listCustomersPage.deleteCustomerByRowNumber(mapNameToIndex.get(nameForDeletion));

        actualListOfFirstNames.clear();

        actualListOfFirstNames = TestUtils.getActualListOfFirstNames(listCustomersPage);

        Assertions.assertFalse(actualListOfFirstNames.contains(nameForDeletion));
    }

}
