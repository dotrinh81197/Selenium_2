package com.auto.test.sanity;

import com.auto.data.enums.Navigation;
import com.auto.model.User;
import com.auto.model.UserModel;
import com.auto.page.IHomePage;
import com.auto.page.ILoginPage;
import com.auto.page.PageFactory;
import com.auto.test.TestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ItemsTest extends TestBase {
    private UserModel user;
    private ILoginPage loginPage;
    private IHomePage homePage;


    @Test(description = "Able to create new item")
    public void C14580() {
        // 1. Login to system
        loginPage.login(user);

        // 2. Open items page
        homePage.openPage(Navigation.ITEMS);
    }

    @BeforeClass
    public void before() {
        loginPage = PageFactory.getLoginPage();
        homePage = PageFactory.getHomePage();
        user = User.instance().getUser();
    }

    @AfterClass
    public void after() {
        // Do somethings
    }
}


