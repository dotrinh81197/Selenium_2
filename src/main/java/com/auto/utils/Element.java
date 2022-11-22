package com.auto.utils;

import com.logigear.element.BaseElement;
import com.logigear.element.ElementCaching;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Element extends BaseElement {

    public Element(String locator) {
        super(locator);
    }

    public Element(By by) {
        super(by);
    }

    public Element(String locator, Object... args) {
        super(locator, args);
    }

    public Element(BaseElement parent, String locator) {
        super(parent, locator);
    }

    public Element(BaseElement parent, By by) {
        super(parent, by);
    }

    public Element(BaseElement parent, String locator, Object... args) {
        super(parent, locator, args);
    }

    public void click() {
        this.waitForVisible();
        super.click();
    }

    public void hover() {
        this.waitForVisible();
        super.hover();
    }

    public void enter(String value) {
        this.waitForVisible();
        this.enter(value, true);
    }

    public void selectByPartOfVisibleText(String value) {
        Select select = new Select(this.element());
        List<WebElement> optionElements = select.getOptions();

        for (WebElement optionElement : optionElements) {
            if (optionElement.getText().contains(value)) {
                String optionIndex = optionElement.getAttribute("index");
                select.selectByIndex(Integer.parseInt(optionIndex));
                break;
            }
        }
    }
}
