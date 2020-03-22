package com.automation.tests.day9;

import com.google.gson.internal.$Gson$Preconditions;
import com.util.BrowserUtil;
import com.util.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsTests {
    private WebDriver driver;
    private Actions actions;
    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        actions = new Actions(driver);
    }

    @Test
    public void hoverOnImage(){
        driver.get("http://practice.cybertekschool.com/hovers");
        driver.manage().window().maximize();
        BrowserUtil.wait(3);

        WebElement img1 = driver.findElement(By.xpath("(//img)[1]"));
        WebElement img2 = driver.findElement(By.xpath("(//img)[2]"));
        WebElement img3 = driver.findElement(By.xpath("(//img)[3]"));
        BrowserUtil.wait(3);
        actions.moveToElement(img1).pause(1000).moveToElement(img2).pause(1000).moveToElement(img3).
                build().perform();

        WebElement imgText1 = driver.findElement(By.xpath("//h5[contains(text(),'name: user3')]"));

        Assert.assertTrue(imgText1.isDisplayed());

        BrowserUtil.wait(2);

        actions.moveToElement(img3).perform();
        WebElement img3Text = driver.findElement(By.xpath("//h5[contains(text(),'name: user3')]"));
        Assert.assertTrue(img3Text.isDisplayed());
    }
    @Test
    public void jQueryMenuTest(){
        driver.get("http://practice.cybertekschool.com/jqueryui/menu#");
        actions.moveToElement(driver.findElement(By.xpath("//a[@id='ui-id-3']"))).
                pause(1000).moveToElement(driver.findElement(By.xpath("//a[@id='ui-id-4']"))).pause(1000).
                moveToElement(driver.findElement(By.xpath("//a[@id='ui-id-5']"))).click().build().perform();
    }

    @Test
    public void dragAndDropTest(){
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
        driver.manage().window().maximize();
        BrowserUtil.wait(2);

        WebElement earth = driver.findElement(By.cssSelector("#droptarget"));
        WebElement moon = driver.findElement(By.cssSelector("#draggable"));
        WebElement acceptCookies = driver.findElement(By.xpath("//button[@class='optanon-allow-all accept-cookies-button']"));
        acceptCookies.click();
        BrowserUtil.wait(3);

        actions.dragAndDrop(moon,earth).build().perform();

        Assert.assertEquals(earth.getText(), "You did great!");
    }


    @AfterMethod
    public void teardown(){
        BrowserUtil.wait(100);
        driver.quit();
    }
}
