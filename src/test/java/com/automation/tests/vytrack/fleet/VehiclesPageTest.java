package com.automation.tests.vytrack.fleet;

import com.automation.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class VehiclesPageTest {
    private WebDriver driver;
    private Actions actions;

    private String URL = "https://qa2.vytrack.com/user/login";
    // Correct Credentials for store manager
    private String username = "storemanager85";
    private String password = "UserUser123";

    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By warningMessageBy = By.cssSelector("[class = 'alert alert-error'] > div");

    private By fleetBy = By.xpath("//span[@class='title title-level-1'][contains(text(),'Fleet')]");
    private By subtitleBy = By.xpath("//h1[@class='oro-subtitle']");

    private By pageNumberBy = By.xpath("//input[@class='input-widget']");

    //---------------------------------
    private By activitiesButtonBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Activities')]");
    //private By titleBy = By.cssSelector("input[id = 'oro_calendar_event_form_title-uid-5e7170af9f92c']");
    private By calendarEventsButtonBy = By.xpath("//span[contains(text(),'Calendar Events')]");
    private By createCalendarEventBtnBy = By.xpath("//a[contains(@class,'btn main-group btn-primary pull-right')]");
    private By ownerNameBy = By.xpath("//span[@class='select2-chosen']");
    private By currentUserBy = By.cssSelector("#user-menu > a");
    private By titleBy = By.cssSelector("[name='oro_calendar_event_form[title]']");
    private By startDateBy = By.cssSelector("input[id*='date_selector_oro_calendar_event_form_start-uid']");
    private By startTimeBy = By.cssSelector("input[id*='time_selector_oro_calendar_event_form_start-uid']");

    @BeforeMethod
    public void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        BrowserUtil.wait(5);
    }

    @Test (description = "Verify page subtitle")
    public void verifyPageSubtitle(){
        //login
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);

        BrowserUtil.wait(5);
        //click on fleet
//            driver.findElement(fleetBy).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        BrowserUtil.wait(5);

        //click on Vehicles
        driver.findElement(By.linkText("Vehicles")).click();

        BrowserUtil.wait(2);

        //find subtitle element
        WebElement subTitleElement = driver.findElement(subtitleBy);
        String actual = subTitleElement.getText();
        String expected = "All Cars";

        Assert.assertEquals(actual, expected);

    }

    /**
     *
     *     ################ TASK 5 minutes
     *
     *     Given user is on the vytrack landing page
     *     When user logs on as a store manager
     *     Then user navigates to Fleet --> Vehicles
     *     And user verifies that page number is equals to "1"
     */
    @Test (description = "Verifying page number.")
    public void verifyPageNumber(){
        //login
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);

        BrowserUtil.wait(5);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        BrowserUtil.wait(5);

        //click on Vehicles
        driver.findElement(By.linkText("Vehicles")).click();

        BrowserUtil.wait(2);

        WebElement pageNumberElement = driver.findElement(pageNumberBy);
        String actualText = pageNumberElement.getAttribute("value");
        String expectedText = "1";

        Assert.assertEquals(actualText, expectedText);


    }

    /**
     * //in the @BeforeMethod
     * Test Case: Default options
     * Login as sales manager
     * Go to Activities --> Calendar Events
     *
     *
     * Click on Create Calendar Event
     * Default owner name should be current user
     * Default title should be blank
     * Default start date should be current date
     * Default start time should be current time
     */
    @Test(description = "Default options")
    public void verifyDefaultValues(){
        //login
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);

        BrowserUtil.wait(5);

        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(activitiesButtonBy)).perform();
        BrowserUtil.wait(3);

        driver.findElement(calendarEventsButtonBy).click();
        BrowserUtil.wait(3);

        //Click on Create Calendar Event
        driver.findElement(createCalendarEventBtnBy).click();
        BrowserUtil.wait(4);
        //Default owner name should be current user
        String currentUserName = driver.findElement(currentUserBy).getText().trim();
        String defaultOwnerName = driver.findElement(ownerNameBy).getText().trim();
        Assert.assertEquals(currentUserName, defaultOwnerName);
        BrowserUtil.wait(4);
//        Default title should be blank
        WebElement titleElement = driver.findElement(titleBy);
        Assert.assertTrue(titleElement.getAttribute("value").isEmpty());
        //date time syntax = https://www.journaldev.com/17899/java-simpledateformat-java-date-format

        Set<String> timeZones = ZoneId.getAvailableZoneIds();
        for (String each:
                timeZones) {
            if(each.startsWith("America"))
                System.out.println(each);
        }

        //Default start date should be current date
        BrowserUtil.wait(3);
        Date input = new Date();
        LocalDate date = input.toInstant().atZone(ZoneId.of("America/Los_Angeles")).toLocalDate();
        String expectedDate = date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        BrowserUtil.wait(4);
        String actualDate = driver.findElement(startDateBy).getAttribute("value");
        Assert.assertEquals(actualDate , expectedDate);
        BrowserUtil.wait(4);
        String expectedTime = LocalTime.now(ZoneId.of("GMT-7")).format(DateTimeFormatter.ofPattern("h:mm a"));
        String actualTime = driver.findElement(startTimeBy).getAttribute("value");
        Assert.assertEquals(actualTime, expectedTime);
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            //close browser and session, destroy driver object permanently
            driver.quit();
            driver = null;
        }
    }
}

