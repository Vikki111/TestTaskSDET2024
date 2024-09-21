package simple.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
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
import java.util.*;

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
        String firstName = getFirstNameFromPostCode(postCode);

        HomePage homePage = new HomePage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getAddCustomerBtn()));
        homePage.addCustomerClick();

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(addCustomerPage.getFirstNameField()));

        addCustomerPage.setFirstName(firstName);
        addCustomerPage.setLastName(lastName);
        addCustomerPage.setPostCode(postCode);

        addCustomerPage.submitClick();

        Alert alert = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Test
    public void failedAddCustomerTest() {
        String postCode = RandomStringUtils.random(10, false, true);
        String lastName = RandomStringUtils.random(10, true, false);
        String firstName = getFirstNameFromPostCode(postCode);

        HomePage homePage = new HomePage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getAddCustomerBtn()));
        homePage.addCustomerClick();

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(addCustomerPage.getFirstNameField()));

        addCustomerPage.setFirstName(firstName);
        addCustomerPage.setLastName(lastName);

        addCustomerPage.submitClick();

        Assertions.assertFalse(isAlertPresent());
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

        List<String> actualListOfFirstNames = new LinkedList<>();

        for (int i = 1; i <= listCustomersPage.getSizeOfTable(); i++) {
            actualListOfFirstNames.add(listCustomersPage.getCellByRowNumberForFirstName(Integer.toString(i)));
        }
        Assertions.assertEquals(expectedListOfFirstNames, actualListOfFirstNames);
    }

    @Test
    public void deleteCustomerTest() {

        HomePage homePage = new HomePage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getCustomersListBtn()));

        homePage.listCustomersClick();

        ListCustomersPage listCustomersPage = new ListCustomersPage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(listCustomersPage.getFirstNameSortLink()));

        List<String> actualListOfFirstNames = new LinkedList<>();

        for (int i = 1; i <= listCustomersPage.getSizeOfTable(); i++) {
            actualListOfFirstNames.add(listCustomersPage.getCellByRowNumberForFirstName(Integer.toString(i)));
        }

        Map<String, String> mapNameToIndex = getMapNameToIndex(actualListOfFirstNames);

        String nameForDeletion = getNameForDeletion(actualListOfFirstNames);

        listCustomersPage.deleteCustomerByRowNumber(mapNameToIndex.get(nameForDeletion));

        actualListOfFirstNames.clear();

        for (int i = 1; i <= listCustomersPage.getSizeOfTable(); i++) {
            actualListOfFirstNames.add(listCustomersPage.getCellByRowNumberForFirstName(Integer.toString(i)));
        }

        Assertions.assertFalse(actualListOfFirstNames.contains(nameForDeletion));
    }

    @Test
    public void addAndDeleteCustomerTest() {

        String postCode = RandomStringUtils.random(28, false, true);
        String lastName = RandomStringUtils.random(10, true, true);
        String firstName = getFirstNameFromPostCode(postCode);

        HomePage homePage = new HomePage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getAddCustomerBtn()));
        homePage.addCustomerClick();

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(addCustomerPage.getFirstNameField()));

        addCustomerPage.setFirstName(firstName);
        addCustomerPage.setLastName(lastName);
        addCustomerPage.setPostCode(postCode);

        addCustomerPage.submitClick();

        Alert alert = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.elementToBeClickable(homePage.getCustomersListBtn()));

        homePage.listCustomersClick();

        ListCustomersPage listCustomersPage = new ListCustomersPage(driver);

        (new WebDriverWait(driver, Duration.ofSeconds(40))).until(ExpectedConditions.visibilityOfElementLocated(listCustomersPage.getFirstNameSortLink()));

        List<String> actualListOfFirstNames = new LinkedList<>();

        for (int i = 1; i <= listCustomersPage.getSizeOfTable(); i++) {
            actualListOfFirstNames.add(listCustomersPage.getCellByRowNumberForFirstName(Integer.toString(i)));
        }

        Map<String, String> mapNameToIndex = getMapNameToIndex(actualListOfFirstNames);

        String nameForDeletion = getNameForDeletion(actualListOfFirstNames);

        listCustomersPage.deleteCustomerByRowNumber(mapNameToIndex.get(nameForDeletion));

        actualListOfFirstNames.clear();

        for (int i = 1; i <= listCustomersPage.getSizeOfTable(); i++) {
            actualListOfFirstNames.add(listCustomersPage.getCellByRowNumberForFirstName(Integer.toString(i)));
        }

        Assertions.assertFalse(actualListOfFirstNames.contains(nameForDeletion));
    }

    private static String getFirstNameFromPostCode(String postCode) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < postCode.length(); i += 2) {
            String twoDigitStr = postCode.substring(i, i + 2);
            int number = Integer.parseInt(twoDigitStr);

            char letter = getLetterFromNumber(number);
            result.append(letter);
        }

        return result.toString();
    }

    private static char getLetterFromNumber(int number) {
        int index = number % 26;

        return (char) ('a' + index);
    }

    private boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){
            return false;
        }
    }

    private static Map<String, String> getMapNameToIndex(List<String> actualListOfFirstNames) {
        Map<String, String> mapNameToIndex = new HashMap<>();
        for (int i = 0; i < actualListOfFirstNames.size(); i++) {
            mapNameToIndex.put(actualListOfFirstNames.get(i), Integer.toString(i+1));
        }
        return mapNameToIndex;
    }

    private String getNameForDeletion(List<String> actualListOfFirstNames) {
        OptionalDouble averageLength = actualListOfFirstNames.stream()
                .mapToInt(String::length)
                .average();

        return actualListOfFirstNames.stream()
                .min(Comparator.comparingDouble(s -> Math.abs(s.length() - averageLength.getAsDouble()))).get();
    }

}
