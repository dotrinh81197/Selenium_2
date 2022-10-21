package com.auto.data.enums;

public enum Navigation {
    HOME("Home"),
    LOGIN("Login"),
    INVOICES("Invoices"),
    SETTINGS("Settings"),
    ITEMS("Items"),
    EXPENSES("Expenses");

    private String value;

    Navigation(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
