package com.auto.test.browser;

import com.auto.page.browser.GooglePage;
import org.testng.annotations.Test;

public class GoogleTests extends BrowserTestBase {
    private GooglePage googlePage = new GooglePage();

    @Test
    public void TestCase() {
        googlePage.search("Selenium");
    }
}
