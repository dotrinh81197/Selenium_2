package com.auto.page.tadashboard;

import com.auto.model.Page;
import com.auto.utils.Constants;
import com.auto.utils.Utilities;
import com.auto.utils.WebDriverUltis;
import com.logigear.element.Element;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage {
    private final Element contentContainer = new Element("//div[@id='ccontent']");
    private final Element administratorLnk = new Element("//a[@href='#Welcome']");
    private final Element logoutLnk = new Element("//a[@href='logout.do']");
    private final Element globalSettingLnk = new Element("//li[@class='mn-setting']/a");
    private final Element addPageBtn = new Element("//a[contains(@href, 'Dashboard.openAddPageForm')]");
    private final Element newPageDialogTitleLbl = new Element("//div[@id='div_popup']//td[@class='ptc']/h2");
    private final Element pageNameTxt = new Element("//input[@class='page_txt_name']");
    private final Element OKBtn = new Element("//input[@id='OK']");
    private final Element deletePageBtn = new Element("//a[contains(@href, 'Dashboard.doDeletePage')]");
    private final Element pageParentDrl = new Element("//select[@id='parent']");
    private final Element numberColumnsDrl = new Element("//select[@id='columnnumber']");
    private final Element displayAfterDrl = new Element("//select[@id='afterpage']");
    private final Element publicCb = new Element("//input[@id='isprotected']");
    private final Element pageLnk = new Element("//div[@id='main-menu']//a[contains(@href,'/TADashboard')and text()='%s']");


    //Methods
    public boolean doesContentDisplay() {
        return contentContainer.isDisplayed();
    }

    @Step("Logout dashboard page")
    public void logout() {
        administratorLnk.waitForVisible(Constants.WAIT_TIME_DURATION);
        administratorLnk.waitForClickable(Constants.WAIT_TIME_DURATION);
        administratorLnk.click();
        logoutLnk.click();
    }

    @Step("Check Global Setting link unclickable")
    public boolean isGlobalSettingLnkUnClickable() {
        try {
            clickGlobalSettingLnk();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on Global Setting link")
    public void clickGlobalSettingLnk() {
        globalSettingLnk.waitForVisible(Constants.WAIT_TIME_DURATION);
        globalSettingLnk.waitForClickable(Constants.WAIT_TIME_DURATION);
        globalSettingLnk.click();
    }

    @Step("Check new page dialog title display")
    public boolean doesNewPageDialogTitleDisplay() {
        return newPageDialogTitleLbl.isDisplayed();
    }

    @Step("Enter Page name text box")
    public void enterPageNameTxt(String value) {
        pageNameTxt.waitForVisible();
        pageNameTxt.enter(value);
    }

    @Step("Select Parent Page dropdown list")
    public void selectParentPageDrl(String value) {
        pageParentDrl.select(value);
    }

    @Step("Select Number of columns dropdown list")
    public void selectNumberOfColumnsDrl(String value) {
        numberColumnsDrl.select(value);
    }

    @Step("Select Display after dropdown list")
    public void selectDisplayAfterDrl(String value) {
        displayAfterDrl.select(value);
    }

    @Step("Select the public checkbox")
    public void selectPublicCb() {
        publicCb.click();
    }

    @Step("Click on OK button")
    public void clickOKBtn() {
        OKBtn.waitForVisible();
        OKBtn.click();
    }

    @Step("Click on Global Setting link")
    public String getPageNameTxt(Element element) {
        return element.getText();
    }


    @Step("Check new page display beside a page")
    public boolean doesNewPageDisplayBeside(String newPageName, String besidePageName) {
        List<WebElement> pageList = new Element("//div[@id='main-menu']//a[contains(@href,'/TADashboard')]").elements();

        Integer pageNumber = pageList.size();

        for (int i = 0; i < pageNumber; i++) {
            if (pageList.get(i).getText().equalsIgnoreCase(besidePageName)) {
                if (pageList.get(i + 1).getText().equalsIgnoreCase(newPageName)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Step("Remove page")
    public void removePage(String pageName) {
        clickOnPage(pageName);
        clickDeletePageBtn();
        WebDriverUltis.acceptAlert();

    }

    @Step("Click on page name")
    public void clickOnPage(String pageName) {
        try {
            pageLnk.set(pageName);
            pageLnk.waitForVisible(Constants.WAIT_TIME_DURATION);
            pageLnk.waitForClickable(Constants.WAIT_TIME_DURATION);
            pageLnk.click();
        } catch (Exception e) {
            Allure.description(String.valueOf(e));
        }
    }

    @Step("Click Delete button")
    public void clickDeletePageBtn() {
        clickGlobalSettingLnk();
        deletePageBtn.waitForVisible();
        deletePageBtn.click();
    }

    @Step("Click Add Page button")
    public void clickAddPageBtn() {
        clickGlobalSettingLnk();
        addPageBtn.waitForVisible();
        addPageBtn.click();
    }


    @Step("Create a new page")
    public void createNewPage(Page page) {

        clickAddPageBtn();
        enterPageNameTxt(page.getPageName());
        if (!page.getParentPage().equalsIgnoreCase("")) {
            selectParentPageDrl(page.getParentPage());
        }
        if (!page.getNumberOfColumns().equalsIgnoreCase("")) {
            selectNumberOfColumnsDrl(page.getNumberOfColumns());
        }
        if (!page.getDisplayAfter().equalsIgnoreCase("")) {
            selectDisplayAfterDrl(page.getDisplayAfter());
        }
        if (page.getIsPublic().equalsIgnoreCase("true")) {
            selectPublicCb();
        }

        clickOKBtn();
    }

    @Step("Remove Children Page")
    public void removeChildrenPage(String parentPage, String childrenPage) {
        clickOnPage(parentPage);
        clickOnPage(childrenPage);
        clickDeletePageBtn();
    }

    @Step("Check Page is not display")
    public boolean doesPageNotDisplay(Page page) {
        page.getPageName();
        pageLnk.set(page.getPageName());
        return Utilities.doesElementIsNotDisplay(pageLnk);
    }

    @Step("Check button delete is not display")
    public boolean doesDeleteButtonIsNotDisplay() {
        return Utilities.doesElementIsNotDisplay(deletePageBtn);
    }

}


