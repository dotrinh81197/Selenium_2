package com.auto.page.tadashboard;

import com.logigear.element.Element;
import io.qameta.allure.Step;

public class DashboardPage {
    private final Element content_container = new Element("//div[@id='ccontent']");
    private final Element administrator_link = new Element("//a[@href='#Welcome']");
    private final Element logout_link = new Element("//a[@href='logout.do']");


    //Methods
    public boolean doesContentDisplays() {
        return content_container.isDisplayed();
    }

    @Step("Logout dashboard page")
    public void logout(){
        administrator_link.click();
        logout_link.click();
    }
}
