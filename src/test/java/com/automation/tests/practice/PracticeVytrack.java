package com.automation.tests.practice;


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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PracticeVytrack {
    private WebDriver driver;
    private String URL = "https://www.penfed.org/";
    // Correct Credentials for store manager
    private String username = "storemanager85";
    private String password = "UserUser123";

    private By usernameBy = By.id("prependedInput");
     private By passwordBy = By.id("prependedInput2");
    private By warningMessageBy = By.cssSelector("[class = 'alert alert-error'] > div");

    private By fleetBy = By.xpath("//span[@class='title title-level-1'][contains(text(),'Fleet')]");
    private By subtitleBy = By.xpath("//h1[@class='oro-subtitle']");

    private By pageNumberBy = By.xpath("//input[@class='input-widget']");

    //https://www.penfed.org/
    private By checkingAndSavingsBy = By.xpath("//span[contains(text(),'Checking & Savings')]");
    private By acceptCookiesBy = By.xpath("//button[@class='banner-cookies__button banner-cookies__button--accept button']");
    private By allCheckingAccountsBy = By.xpath("//a[contains(text(),'All Checking Accounts')]");
    private By openNowBy = By.xpath("//a[contains(@class,'get-started-button pfui-margin-top-0 dtm-get-started-button')]");
    private By alreadyMemberBy = By.xpath("//input[@id='login-user-name-input-get-started']");
    private By passwordInputBy = By.xpath("//input[@id='ctl00_ctl00_MainContentPlaceHolder_cphSecurityMainContent_txtPassword']");
    private By errorMessageBy = By.xpath("//span[@id='ml-form-error']");



    @Test (description = "Verify page subtitle")
    public void verifyPageSubtitle() {
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

        //collect all element in the first row
        WebElement parentElement = driver.findElement(By.xpath("//tbody[@class='grid-body']//tr[1]"));
        List<WebElement> list = parentElement.findElements(By.xpath("*"));
        System.out.println(list.size());
        WebElement element = driver.findElement(By.xpath("//td[contains(text(), 'Masha')]"));
        System.out.println(element.getCssValue("data-column-label"));
    }

    @Test
    public void verifyWarningMessage(){
        //click on accept cookies button
        driver.findElement(acceptCookiesBy).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //hover over checking & savings button to display dropdown menu
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(checkingAndSavingsBy)).perform();
        BrowserUtil.wait(5);
        //click on All Checking Accounts Button
        driver.findElement(allCheckingAccountsBy).click();
        BrowserUtil.wait(5);

        //click on Open Now Button
        driver.findElement(openNowBy).click();
        BrowserUtil.wait(5);

        //send keys to already member input box
        driver.findElement(alreadyMemberBy).sendKeys("AlexSimone", Keys.ENTER);
        BrowserUtil.wait(5);

        //send keys to password input box
        driver.findElement(passwordInputBy).sendKeys("Hello World!", Keys.ENTER);
        BrowserUtil.wait(5);

        //display warning/error message
        String actual = driver.findElement(errorMessageBy).getText();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String expected = "We're sorry. The username and password you have entered do not match. Please try again.";

        Assert.assertEquals(expected, actual, "Expected message does not match actual message");

    }

    @Test (description = "Trying to retrieve child elements of parent")
    public void getListElements(){
        WebElement parentElement = driver.findElement(By.xpath("//div[@id='rates']//div[2]//div[2]//div[2]//div[1]//div[1]//div[2]//table[1]//tbody[1]//tr[2]"));
        List<WebElement> list = parentElement.findElements(By.xpath("*"));
        System.out.println(list.size());
    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.firefoxdriver().version("74").setup();
        driver = new FirefoxDriver();
        driver.get(URL);
        BrowserUtil.wait(1);
        driver.manage().window().maximize();
        BrowserUtil.wait(3);
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

