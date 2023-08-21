package com.auto.utils;

import com.logigear.Modal;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;


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

    public static void deleteFiles(@Nonnull File folder) throws IOException {
        if (folder.isDirectory()) {
            FileUtils.cleanDirectory(folder);
        }

    }

    @Step
    public static boolean doesElementDisable(Element element) {
        try {
            return element.getAttribute("disabled").contains("true");

        } catch (Exception e) {
            return false;
        }
    }
}

