package com.auto.utils;

import com.logigear.element.Element;

import static com.logigear.statics.Selaium.driver;

public class Utilities {

    public static String getAlertMessage() {
        return driver().getWebDriver().switchTo().alert().getText();
    }

    public static boolean doesElementIsNotDisplay(Element element) {
        try {
            return !element.isDisplayed();

        } catch (Exception e) {
            return true;
        }
    }
}
