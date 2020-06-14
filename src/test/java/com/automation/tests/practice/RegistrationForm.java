package com.automation.tests.practice;

import com.automation.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationForm {
    private String URL = "http://practice.cybertekschool.com/registration_form";
    private WebDriver driver;
    // p tag name of success message
    private By firstNameBy = By.name("firstname");
    private By lastNameBy = By.name("lastname");
    private By userNameBy = By.name("username");
    private By emailNameBy = By.name("email");
    private By passwordBy = By.name("password");
    private By phoneBy = By.name("phone");
    private By maleBy = By.cssSelector("input[value = 'male']");
    private By femaleBy = By.cssSelector("input[value = 'female']");
    private By otherBy = By.cssSelector("input[value = 'other']");
    private By birthdayBy = By.name("birthday");
    private By departmentBy = By.name("department");
    private By jobTitleBy = By.name("job_title");
    private By cplusplusBy = By.xpath("//label[text() = 'C++']/preceding-sibling::input");
    private By javaBy = By.xpath("//label[text() = 'Java']/preceding-sibling::input");
    private By javascriptBy = By.xpath("//label[text() = 'JavaScript']/preceding-sibling::input");

    private By singUpBy = By.id("wooden_spoon");

    @Test
    public void test1(){
        driver.findElement(firstNameBy).sendKeys("Patrick");
        driver.findElement(lastNameBy).sendKeys("Azure");
        driver.findElement(userNameBy).sendKeys("crazyguy76");
        driver.findElement(emailNameBy).sendKeys("abcd@gmail.com");
        driver.findElement(passwordBy).sendKeys("jajafjkfa;kjd");
        driver.findElement(phoneBy).sendKeys("234-987-9876");
        driver.findElement(maleBy).click();
        driver.findElement(birthdayBy).sendKeys("10/11/1989");

        Select departmentSelect = new Select(driver.findElement(departmentBy));
        departmentSelect.selectByVisibleText("MCTC");

        Select jobTitleSelect = new Select(driver.findElement(jobTitleBy));
        jobTitleSelect.selectByVisibleText("Developer");

        driver.findElement(cplusplusBy).click();
        driver.findElement(singUpBy).click();

        BrowserUtil.wait(5);
        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.tagName("p")).getText();

    }

    @Test
    public void verifyFirstNameLength(){
        driver.findElement(firstNameBy).sendKeys("a");
        BrowserUtil.wait(3);
        WebElement warningMessage = driver.findElement(By.xpath("//small[text() ='first name must be more than 2 and less than 64 characters long']"));
        Assert.assertTrue(warningMessage.isDisplayed());
    }

    @Test
    public void verifyAlphabeticLettersOnlyTest(){
        driver.findElement(firstNameBy).sendKeys("123");
        BrowserUtil.wait(3);
        WebElement warningMessage =
                driver.findElement(By.xpath("//small[text() = 'first name can only consist of alphabetical letters']"));
        Assert.assertTrue(warningMessage.isDisplayed());
    }



    @BeforeMethod
    public void setup(){
        WebDriverManager.firefoxdriver().version("79").setup();
        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
