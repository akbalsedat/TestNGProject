package com.automation.tests.day13;

import org.testng.annotations.Test;

public class SystemProperties {

    @Test
    public void test(){
        String path = System.getProperty("user.dir");
        String wholePath = path + "/src/test/java/com/automation/tests/day13/SystemProperties.java";
    }
}
