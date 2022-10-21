package com.auto.data.enums;

public enum Data {
    USER(System.getProperty("user.dir") + "/src/main/java/com/auto/model/data/user.json");

    private String value;

    Data(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
