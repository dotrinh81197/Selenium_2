package com.auto.page.tadashboard;

import com.auto.utils.WebDriverUltis;
import com.logigear.element.Element;
import io.qameta.allure.Step;

public class DataProfilesPage extends GeneralPage{
    private final Element addNewBtn = new Element("//div[@class='panel_tag2']//a[@href='profile.jsp?action=create']");
    private final Element profileNameTxt = new Element("//input[@id='txtProfileName']");
    private final Element itemTypeDrl = new Element("//select[@id='cbbEntityType']");
    private final Element relatedDataDrl = new Element("//select[@id='cbbSubReport']");
    private final Element nextBtn = new Element("//input[@value='Next']");
    private final Element finishBtn = new Element("//input[@value='Finish']");
    private final Element cancelBtn = new Element("//input[@value='Cancel']");
    private final Element dataProfileRow = new Element("//div[@class='panel_tag1']//table//td//a[text()='%s']");
    private final Element checkAllBtn = new Element("//a[@onclick=\"Dashboard.doCheckAll(true,'chkDel');return false;\"]");
    private final Element deleteBtn = new Element("//div[@class='panel_tag2']//a[@href='javascript:Dashboard.deleteProfiles();']");

    @Step
    public void clickAddNewBtn() {
        addNewBtn.click();
    }

    @Step
    public void enterDataProfileName(String value) {
        profileNameTxt.enter(value);
    }

    @Step
    public void clickFinishBtn() {
        finishBtn.click();
    }

    @Step
    public void createDataProfile(String profileName) {
        clickAddNewBtn();
        enterDataProfileName(profileName);
        clickFinishBtn();
    }

    @Step
    public void deleteAllDataProfileCreated() {
        clickDataProfilesLnk();
        checkAllBtn.waitForClickable();
        checkAllBtn.click();
        deleteBtn.click();
        WebDriverUltis.acceptAlert();
    }

}
