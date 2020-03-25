package com.automation.tests.day13;

import com.util.ConfigurationReader;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

public class ConfigurationReaderTest {
    @Test
    public void readProperties(){
        String currentBrowser = ConfigurationReader.getProperty("browser");
        String url = ConfigurationReader.getProperty("qa1");

        System.out.println(currentBrowser);
        System.out.println(url);

        String storeManager = ConfigurationReader.getProperty("store_manager");
        String password = ConfigurationReader.getProperty("password");

        System.out.println(storeManager);
        System.out.println(password);


    }
}
