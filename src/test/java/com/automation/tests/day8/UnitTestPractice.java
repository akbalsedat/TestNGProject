package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UnitTestPractice {

    public static void main(String[] args) {
        //Assertion
        //Unit test to check if our method works properly
        // if assertion fails, that means there is some bug in our code
        verifyEquals("cba", reverseString("abc"));
    }

    @Test (description = "Verify if method can reverse a string")
    public void Test() {
        String expected = "elppa";
        String actual = reverseString("apple");
        // to verify if expected result is same with actual result
        // Assert class comes from testNG library
        Assert.assertEquals(expected, actual);

    }

    public static String reverseString(String str){
        String reversedString = "";
        for (int index = str.length() -1; index >= 0 ; index--) {
            reversedString += str.charAt(index);
        }
        return reversedString;
    }

    public static boolean verifyEquals(String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("TEST PASSED");
            return true;
        } else {
            System.out.println("Test failed!!!");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            return false;
        }
    }
}
