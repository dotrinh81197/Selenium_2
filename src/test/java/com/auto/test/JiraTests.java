package com.auto.test;

import com.auto.integration.TestCase;
import com.auto.utils.Assertion;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class JiraTests {

    @TestCase(id = "C2")
    @Test(description = "Able to create new item")
    public void testCase() {
        Allure.step("Login to System");
        Allure.step("Go to create new item page");
        Allure.step("Create new item");
        Assertion.assertTrue(false, "Verify user can create new item successfully");
    }
}
