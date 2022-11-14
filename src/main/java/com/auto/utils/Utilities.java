package com.auto.utils;

import com.logigear.Modal;

import static com.logigear.statics.Selaium.driver;

public class Utilities {

    public static String getAlertMessage() {
        return new Modal(driver()).getAlertText();
    }

    public static boolean doesElementIsNotDisplay(Element element) {
        try {
            return !element.isDisplayed();

        } catch (Exception e) {
            return true;
        }
    }
}
