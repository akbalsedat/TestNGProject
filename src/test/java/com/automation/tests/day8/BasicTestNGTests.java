package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.*;

public class BasicTestNGTests {
    //runs before every test automatically
    //works as a pre-condition setup
    @BeforeMethod
    public void setup(){
        System.out.println("Inside @BeforeMethod");
    }
    // works automatically after every test method
    @AfterMethod
    public void teardown(){
        System.out.println("Inside @AfterMethod");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Inside @BeforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("Inside @AfterClass");
    }
    //runs only once before @BeforeClass and @BeforeMethod
    @BeforeTest
    public void beforeTest(){
        System.out.println("Inside @BeforeTest");
    }
    //runs only once after @AfterClass and @AfterMethod
    @AfterTest
    public void afterTest(){
        System.out.println("Inside @AfterTest");
    }

    //runs only once before @BeforeTest, @BeforeClass and @BeforeMethod
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Inside @BeforeSuite");
    }

    //runs only once after @AfterTest @AfterClass and @AfterMethod
    @AfterSuite
    public void afterSuite(){
        System.out.println("Inside @AfterSuite");
    }


    @Test
    public void test1(){
        System.out.println("Inside test1()");
        String expected = "f86f27a59d96402f9d665ae68bfd631bed7ac6e49364a8dde774d09d6ab7c2d119cd91dfd1378c77ae61bc7c982888112bd75d5a37cc4bd72dde7b0ef01a0e5f *tomcat-connectors-1.2.48-src.zip";
        String actual = "f86f27a59d96402f9d665ae68bfd631bed7ac6e49364a8dde774d09d6ab7c2d119cd91dfd1378c77ae61bc7c982888112bd75d5a37cc4bd72dde7b0ef01a0e5f *tomcat-connectors-1.2.48-src.zip";
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test2(){
        System.out.println("Inside test2()");
        String expected = "banana";
        String actual = "banana";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test3(){
        System.out.println("Inside test3()");
        int num1 = 50;
        int num2 = 89;
        // it calls hard assertion.
        // if assertion fails - it stops.
        Assert.assertTrue(num1 < num2);
    }
}
