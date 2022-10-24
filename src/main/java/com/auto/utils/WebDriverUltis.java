package com.auto.utils;

import static com.logigear.statics.Selaium.driver;

public class WebDriverUltis {
    public static void acceptAlert() {
        driver().getWebDriver().switchTo().alert().accept();
    }

}
