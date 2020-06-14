package com.automation.tests.day8;

import com.automation.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests {
    private WebDriver driver;

    @Test
    public void googleSearchTest(){
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("java", Keys.RETURN);
        //since every search item has a tag name <h3>
        //it's the easiest way to collect all of them
        List<WebElement> elementList = driver.findElements(By.xpath("//h3[contains(text(),'Java')]"));
         elementList.forEach(each -> System.out.println(each.getText()));
        for (WebElement element:
             elementList) {
            String text = element.getText();
            if(!text.isEmpty()){
                System.out.println(text);
                Assert.assertTrue(text.toLowerCase().contains("java"));
            }
        }

    }

    @Test(description = "Search for Java book on amazon")
    public void amazonSearchTest(){
        driver.get("http://amazon.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java", Keys.RETURN);

        BrowserUtil.wait(5);
        List<WebElement> webElementList = driver.findElements(By.xpath("//h2//a"));
        webElementList.forEach(each -> System.out.println(each.getText()));

        BrowserUtil.wait(5);
        webElementList.get(0).click();
        BrowserUtil.wait(5);

        String productTitleString = driver.findElement(By.xpath("//span[@id = 'ebooksProductTitle']")).getText();

        System.out.println("productTitleString = " + productTitleString);

        Assert.assertTrue(productTitleString.contains("Java"));
    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown(){
        //close browser and destroy webdriver object
        driver.quit();
    }
}
