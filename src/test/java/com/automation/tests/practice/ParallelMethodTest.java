package com.automation.tests.practice;

import org.testng.annotations.*;

public class ParallelMethodTest {

    @BeforeClass
    public void beforeClass() {
        long id = Thread.currentThread().getId();
        System.out.println("@BeforeClass-ParallelMethodTest. Thread id is: " + id);
    }

    @BeforeMethod
    public void beforeMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method. Thread id is: " + id);
    }

    @Test
    public void testMethodsOne() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method One. Thread id is: " + id);
    }

    @Test
    public void testMethodsTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Two. Thread id is: " + id);
    }

    @AfterMethod
    public void afterMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("After test-method. Thread id is: " + id);
    }
    @AfterClass
    public void afterClass() {
        long id = Thread.currentThread().getId();
        System.out.println("@Afterclass-ParallelMethodTest. Thread id is: " + id);
    }
}
