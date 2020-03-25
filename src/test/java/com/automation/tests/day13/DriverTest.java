package com.automation.tests.day13;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DriverTest {
    @Test
    public void googleTest(){
        // Driver.getDriver() returns driver
        Driver.getDriver().get("http://google.com");
        Assert.assertEquals(Driver.getDriver().getTitle(), "Google");
        Driver.closeDriver();
    }

    @Test
    public void firefoxTest(){
        Driver.getDriver().get("https://duckduckgo.com");
        Assert.assertEquals(Driver.getDriver().getTitle(), "DuckDuckGo — Privacy, simplified.");
        Driver.closeDriver();
    }
}
