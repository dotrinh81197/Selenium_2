package com.auto.data.enums;

public enum Data {
    INVALID_USER(System.getProperty("user.dir") + "/src/main/java/com/auto/model/data/InvalidUsers.json");

    private String value;

    Data(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
