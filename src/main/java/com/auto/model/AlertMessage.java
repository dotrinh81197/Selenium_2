package com.auto.model;

import com.auto.data.enums.Data;
import com.auto.utils.JsonUtils;

import java.util.Hashtable;

@lombok.Data
public class AlertMessage {
    private static Hashtable<String, String> data;

    private String text;

    public String getAlertText() {
        return text;
    }

    public AlertMessage(String typeOfAlert) {
        data = JsonUtils.getData(typeOfAlert, Data.ALERT_MESSAGE);
        this.text = data.get("text");
    }

    public AlertMessage(String typeOfAlert, Page page) {
        data = JsonUtils.getData(typeOfAlert, Data.ALERT_MESSAGE);
        this.text = String.format(data.get("text"), page.getPageName());
    }

}