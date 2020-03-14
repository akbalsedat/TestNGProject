package com.automation.tests.day8;

public class UnitTestPractice {

    public static void main(String[] args) {
        //Assertion
        //Unit test to check if our method works properly
        // if assertion fails, that means there is some bug in our code
        verifyEquals("cba", reverseString("abc"));
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
