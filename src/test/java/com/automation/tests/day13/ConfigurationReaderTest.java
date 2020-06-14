package com.automation.tests.day13;

import com.automation.utilities.ConfigurationReader;
import org.testng.annotations.Test;


public class ConfigurationReaderTest {
    @Test
    public void readProperties(){
        String currentBrowser = ConfigurationReader.getPropertyLocal("browser");
        String url = ConfigurationReader.getPropertyLocal("qa1");

        System.out.println(currentBrowser);
        System.out.println(url);

        String storeManager = ConfigurationReader.getPropertyLocal("store_manager");
        String password = ConfigurationReader.getPropertyLocal("password");

        System.out.println(storeManager);
        System.out.println(password);

    }
}
