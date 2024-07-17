package simple.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PracticeForm {

    private WebDriver driver;

    private By firstNameField = By.xpath("//input[@id=\"firstName\"]");
    private By lastNameField = By.xpath("//input[@id=\"lastName\"]");
    private By emailField = By.id("userEmail");
    private By genderRadioBtn = By.xpath("//label[text()='Male']");
    private By mobileField = By.xpath("//input[@id=\"userNumber\"]");
    private By calendar = By.xpath("//input[@id=\"dateOfBirthInput\"]");
    private By particularMonth = By.xpath("//div[@class=\"react-datepicker\"]//select[@class=\"react-datepicker__month-select\"]//option[@value=\"5\"]");
    private By particularYear = By.xpath("//div[@class=\"react-datepicker\"]//select[@class=\"react-datepicker__year-select\"]//option[@value=\"2024\"]");
    private By particularDay = By.xpath("//*[@id=\"dateOfBirth\"]//*[text()='11']");
    private By subjectsField = By.xpath("//*[@id=\"subjectsContainer\"]");
    private By hobbiesCheckBoxSports = By.xpath("//*[text()='Sports']");
    private By pictureFile = By.cssSelector("input.form-control-file");
    private By addressField = By.xpath("//textarea[@id=\"currentAddress\"]");
    private By state = By.xpath("//div[@id = \"state\"]");
    private By stateItem = By.xpath("//*[text()='Uttar Pradesh']");
    private By city = By.xpath("//div[@id=\"city\"]");
    private By cityItem = By.xpath("//*[text()='Agra']");
    private By submitBtn = By.xpath("//button[@id=\"submit\"]");

    public PracticeForm (WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String str) {
        driver.findElement(firstNameField).sendKeys(str);
    }
    public void setLastName(String str) {
        driver.findElement(lastNameField).sendKeys(str);
    }
    public void setEmail(String str) {
        driver.findElement(emailField).sendKeys(str);
    }
    public void genderClick() {
        driver.findElement(genderRadioBtn).click();
    }
    public void setMobile(String str) {
        driver.findElement(mobileField).sendKeys(str);
    }
    public void calendarClick() {
        driver.findElement(calendar).click();
    }
    public void particularDayClick() {
        driver.findElement(particularDay).click();
    }
    public void particularMonthClick() {
        driver.findElement(particularMonth).click();
    }
    public void particularYearClick() {
        driver.findElement(particularYear).click();
    }
    public void hobbiesSportsClick() {
        driver.findElement(hobbiesCheckBoxSports).click();
    }
    public void scroll(String scrollParam) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("window.scrollBy(0,%s)", scrollParam), "");
    }
    public void setPicture(String str) {
        driver.findElement(pictureFile).sendKeys(str);
    }
    public void setAddress(String str) {
        driver.findElement(addressField).sendKeys(str);
    }
    public void stateClick() {
        driver.findElement(state).click();
    }
    public void stateItemClick() {
        driver.findElement(stateItem).click();
    }
    public void cityClick() {
        driver.findElement(city).click();
    }
    public void cityItemClick() {
        driver.findElement(cityItem).click();
    }
    public void submitBtnClick() {
        driver.findElement(submitBtn).click();
    }
}