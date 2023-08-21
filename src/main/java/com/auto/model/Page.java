package com.auto.model;

import com.auto.utils.FakerUtils;

import java.util.Hashtable;

@lombok.Data
public class Page {
    private static Hashtable<String, String> data;

    private String pageName;
    private String parentPage;
    private String numberOfColumns;
    private String displayAfter;
    private String isPublic;

    public Page() {
        this.pageName = FakerUtils.word();
        this.parentPage = "";
        this.numberOfColumns = "";
        this.displayAfter = "";
        this.isPublic = "";

    }

    public Page(String pageName, String displayAfter) {
        this.pageName = pageName;
        this.parentPage = "";
        this.numberOfColumns = "";
        this.displayAfter = displayAfter;
        this.isPublic = "";
    }

}
