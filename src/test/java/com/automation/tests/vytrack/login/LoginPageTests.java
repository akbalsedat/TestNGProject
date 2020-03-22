package com.automation.tests.vytrack.login;

import com.util.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// IMPORT ALL STATIC METHODS OF ASSERTION CLASS
import static org.testng.Assert.*;

public class LoginPageTests {
    private WebDriver driver;

    private String URL = "https://qa2.vytrack.com/user/login";
    // Correct Credentials for store manager
    private String username = "storemanager85";
    private String password = "UserUser123";

    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By warningMessageBy = By.cssSelector("[class = 'alert alert-error'] > div");

    @Test(description = "display warning message when invalid username is used.")
    public void invalidUserName(){
        driver.findElement(usernameBy).sendKeys("storemanager851");
        driver.findElement(passwordBy).sendKeys("UserUser123", Keys.ENTER);
        BrowserUtil.wait(5);
        WebElement warningElement = driver.findElement(warningMessageBy);
        assertTrue(warningElement.isDisplayed());
    }

    @Test(description = "Login as store manager and verify that the title is equal to Dashboard")
    public void loginAsStoreManager(){
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtil.wait(5);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Dashboard";
        assertEquals(expectedTitle, actualTitle, "Page title is not correct!");
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
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
