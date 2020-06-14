package com.automation.tests.day9;
// https://www.guru99.com/execute-javascript-selenium-webdriver.html
import com.automation.utilities.BrowserUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JavaSE_Test {
    private WebDriver driver;
    private String URL = "http://demo.guru99.com/V4/";
    private String URL2 = "http://moneyboats.com/";
    private String URL3 = "https://qa2.vytrack.com/user/login";
    private JavascriptExecutor js;

    private Actions actions;

    //    private String truckDriver1 = "user19";
//    private String truckDriver2 = "user20";
//    private String truckDriver3 = "user21";
//
//    private String storeManager1 = "storemanager63";
//    private String storeManager2 = "storemanager64";
//
//    private String salesManager1 = "salesmanager119";
//    private String salesManager2 = "salesmanager120";
//    private String salesManager3 = "salesmanager121";
    private String[] userNames = {"user19", "user20", "user21", "storemanager63", "storemanager64",
            "salesmanager119", "salesmanager120","salesmanager121"};

    private String password = "UserUser123";

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.gecko.driver" , "C:/Users/myuser/Downloads/geckodriver-v0.26.0-win64/geckodriver.exe");
        driver = new FirefoxDriver();
        // Creating the JavaScriptExecutor interface reference by Type casting
        js = (JavascriptExecutor) driver;
        driver.get(URL3);
        driver.manage().window().maximize();
        BrowserUtil.wait(3);

        // close annoying video advertisement for URL website
        // driver.switchTo().frame("flow_close_btn_iframe");
        // driver.findElement(By.xpath("//div[@id='closeBtn']")).click();
        // driver.switchTo().defaultContent();
//
    }

    @Test
    public void login(){
        // find webelement of login button
        WebElement buttonElement = driver.findElement(By.cssSelector("input[value = 'LOGIN']"));

        // Login to Guru99
        driver.findElement(By.cssSelector("[name=uid]")).sendKeys("mngr34926");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("amUpenu");
        BrowserUtil.wait(2);
        js.executeScript("arguments[0].click();", buttonElement);

        //To generate Alert window using JavascriptExecutor. Display the alert message
        js.executeScript("alert('Welcome sons of bitches')");
    }

    @Test
    public void login2(){
        // Set the Script Timeout to 20 seconds
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        // Declare and set the start time
        long start_time = System.currentTimeMillis();

        // Call executeAsyncScript() method to wait for 5 seconds
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length -1], 5000);");

        // Get the difference (currentTime - startTime) of times.
        System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));
    }
    /**
     Launch the site
     Fetch the details of the site like URL of the site, title name and domain name of the site.
     Then navigate to a different page.
     */
    @Test (description = "to navigate to a different page")
    public void login3(){
        // Fetching Domain name of the site. ToString() change object to name
        String domainName = js.executeScript("return document.domain;").toString();
        System.out.println("domainName = " + domainName);

        //Fetching the URL of website. ToString() change object to name
        String url = js.executeScript("return document.URL;").toString();
        System.out.println("url = " + url);

        //Method document.title fetch the Title name of the site. ToString() change object to name
        String titleName = js.executeScript("return document.title;").toString();
        System.out.println("titleName = " + titleName);

        //Navigate to new Page i.e. to generate access page. (launch new url)
        js.executeScript("window.location = 'https://www.penfed.org/'");
    }

    @Test(description = "Another example of scroll downing a webpage")
    public void anotherScrolldownTest(){
        //Vertical sroll down by 600 pixels
        //js.executeScript("window.scrollBy(0, 600)");
        WebElement pageFooterElement = driver.findElement(By.xpath("//a[contains(text(),'Enfold Theme by Kriesi')]"));
        js.executeScript("arguments[0].scrollIntoView(true)", pageFooterElement);

        List<WebElement> list = driver.findElements(By.cssSelector(".avia-menu-text"));
        list.get(1).click();
        BrowserUtil.wait(5);
    }

    @Test(description = "Vytrack assigned ticket@ BO7-38/Vehicle Contract")
    public void verifyUserLogin(){
        for (String each:
                userNames) {
            BrowserUtil.wait(3);
            System.setProperty("webdriver.gecko.driver" , "C:/Users/myuser/Downloads/geckodriver-v0.26.0-win64/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.get(URL3);
            driver.manage().window().maximize();

            js = (JavascriptExecutor) driver;

            WebElement userNameElement = driver.findElement(By.cssSelector("#prependedInput"));
            WebElement passwordElement = driver.findElement(By.cssSelector("#prependedInput2"));

            BrowserUtil.wait(3);
            userNameElement.sendKeys(each);
            BrowserUtil.wait(1);
            passwordElement.sendKeys(password);
            BrowserUtil.wait(1);
            js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#_submit")));
            BrowserUtil.wait(5);

            if(each.startsWith("user")){
                System.out.println("User with username: " + each);
                actionMethod();
                //click on Vehicle Contracts again
                driver.findElement(By.xpath("//span[contains(text(),'Vehicle Contracts')]")).click();

                // get the element of QuickLaunchpad after clickin on vehicle contracts
                WebElement quickLaunchpadElement = driver.findElement(By.cssSelector(".oro-subtitle"));
                Assert.assertTrue(quickLaunchpadElement.isDisplayed());
            }else{
                System.out.println("User with username: " + each);
                actionMethod();
                //click on Vehicle Contracts
                driver.findElement(By.xpath("//span[contains(text(),'Vehicle Contracts')]")).click();
                WebElement element = driver.findElement(By.cssSelector(".oro-subtitle"));
                Assert.assertTrue(element.isDisplayed());
            }
            BrowserUtil.wait(3);
            driver.quit();
        }

    }

    public void actionMethod(){
        //hover  over Fleet heading
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Fleet')]"))).perform();
        BrowserUtil.wait(3);
    }

    @Test(description = "Table test with xpath")
    public void getTableCellTextTest(){
        WebElement userNameElement = driver.findElement(By.cssSelector("#prependedInput"));
        WebElement passwordElement = driver.findElement(By.cssSelector("#prependedInput2"));
        BrowserUtil.wait(1);
        js.executeScript("arguments[0].value='storemanager63';", userNameElement);
        BrowserUtil.wait(1);
        js.executeScript("arguments[0].value='UserUser123';", passwordElement);
        BrowserUtil.wait(1);
        js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#_submit")));
        BrowserUtil.wait(3);

        actionMethod();
        //click on Vehicles
        driver.findElement(
                By.xpath("//li[@class='dropdown-menu-single-item first']//span[@class='title title-level-2'][contains(text(),'Vehicles')]"))
                .click();

        BrowserUtil.wait(2);

//        WebElement plateElement = driver.findElement(By.cssSelector("tr:nth-child(1)"));
//        System.out.println("Plate number: " + plateElement.getText());

        // to Locate table
        WebElement myTable = driver.findElement(By.xpath("//table/tbody"));

        // To locate rows of table
        List<WebElement> rows_table = myTable.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        // Loop will execute for all the rows of table
        for (int row = 0; row < rows_count; row++) {
            // To locate columns(cells) of that specific row
            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

            // To calculate no of columns(cells) in that specific row
            int columns_count = Columns_row.size();
            System.out.println("columns_count = " + columns_count);

            // Loop will execute till the last cell of that specific row
            for (int column = 0; column < columns_count; column++) {
                // To retrieve text from the cells
                String cellText = Columns_row.get(column).getText();
                System.out.println("Cell Value of row number " + row + " and column number " +
                        column + " is " + cellText);
            }
        }

    }

    @AfterMethod
    public void teardown(){
        BrowserUtil.wait(5);
        driver.quit();
    }
}
