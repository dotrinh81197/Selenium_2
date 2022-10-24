package com.auto.page.tadashboard;

import com.logigear.element.Element;
import io.qameta.allure.Step;

public class DashboardPage {
    private final Element contentContainer = new Element("//div[@id='ccontent']");
    private final Element administratorLnk = new Element("//a[@href='#Welcome']");
    private final Element logoutLnk = new Element("//a[@href='logout.do']");


    //Methods
    public boolean doesContentDisplay() {
        return contentContainer.isDisplayed();
    }

    @Step("Logout dashboard page")
    public void logout(){
        administratorLnk.click();
        logoutLnk.click();
    }
}
