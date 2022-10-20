package com.auto.page.browser;

import com.logigear.element.Element;

public class GooglePage {
    private final Element searchTextBox = new Element("name=q");

    public void search(String keyword) {
        searchTextBox.enter(keyword);
    }
}
