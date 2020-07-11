package com.automation.tests.practice;

import com.automation.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class PracticeTests {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        long id = Thread.currentThread().getId();
        System.out.println("@BeforeClass-PracticeTests. Thread id is: " + id);
    }

    @Test
    public  void loginTest(){
        long id = Thread.currentThread().getId();
        System.out.println("loginTest thread id is: " + id);

        driver.findElement(By.linkText("Form Authentication")).click();
        BrowserUtil.wait(2);
        driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("SuperSecretPassword", Keys.RETURN);

        //driver.findElement(By.xpath("//button[@id = 'wooden_spoon']")).click();
        BrowserUtil.wait(2);

        String actual = driver.findElement(By.xpath("//h4[@class = 'subheader']")).getText();
        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        BrowserUtil.wait(2);
        Assert.assertEquals(expected, actual, "Subheader message is not matching.");

        driver.findElement(By.xpath("//i[@class= 'icon-2x icon-signout']")).click();
    }
    @Test
    public  void forgotPassword(){
        long id = Thread.currentThread().getId();
        System.out.println("forgotPassword thread id is: " + id);

        driver.findElement(By.xpath("//a[@href = '/forgot_password']")).click();
        BrowserUtil.wait(2);
        driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("abc@gmail.com", Keys.RETURN);

        BrowserUtil.wait(2);

        String actual = driver.findElement(By.xpath("//h4")).getText();
        String expected = "Your e-mail's been sent!";
        BrowserUtil.wait(2);
        Assert.assertEquals(expected, actual, "Subheader message is not matching.");
    }

    @Test
    public  void checkboxTest1(){
        long id = Thread.currentThread().getId();
        System.out.println("checkboxTest1 thread id is: " + id);

        driver.findElement(By.linkText("Checkboxes")).click();
        BrowserUtil.wait(2);
       List<WebElement> checkboxes = driver.findElements(By.tagName("input"));
       BrowserUtil.wait(3);
       checkboxes.get(0).click();

        Assert.assertTrue( checkboxes.get(0).isSelected(),  "Checkbox#1 is not selected.");
        BrowserUtil.wait(3);
    }

    @BeforeMethod
    public  void setup(){

            long id = Thread.currentThread().getId();
            System.out.println("@Beforemethod thread id is: " + id);

            WebDriverManager.firefoxdriver().setup();
            /**Interview Question: How to handle ssl issues in selenium?*/
            //FirefoxOptions - use to customize browser for tests
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            //to ignore "Your connection is not private" issue
            firefoxOptions.setAcceptInsecureCerts(true);
            //run your browser headless
            firefoxOptions.setHeadless(true);
            // provide chromeOptions object into chromedriverconstructor
         driver = new FirefoxDriver(firefoxOptions);

          driver.get("http://practice.cybertekschool.com/");
            BrowserUtil.wait(2);
        }


    @AfterMethod
    public  void teardown(){
        long id = Thread.currentThread().getId();
        System.out.println("@Aftermethod thread id is: " + id);

        //close browser and destroy webdriver object
        driver.quit();
    }
    @AfterClass
    public void afterClass() {
        long id = Thread.currentThread().getId();
        System.out.println("@AfterClass-PracticeTests. Thread id is: " + id);
    }
}
