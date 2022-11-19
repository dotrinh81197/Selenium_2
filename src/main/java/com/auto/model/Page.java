package com.auto.model;

import com.auto.utils.FakerUtils;
import com.auto.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.auto.data.enums.Data;

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
        this.pageName = "TestPage";
        this.parentPage = "";
        this.numberOfColumns = "";
        this.displayAfter = "";
        this.isPublic = "";
    }

    public Page(String typeOfPage) {
        getPageData(typeOfPage);
        this.pageName = data.get("pageName");
        this.parentPage = data.get("parentPage");
        this.numberOfColumns = data.get("numberOfColumns");
        this.displayAfter = data.get("displayAfter");
        this.isPublic = data.get("isPublic");
    }

    private static void getPageData(String typeOfPage) {
        data = JsonUtils.getData(typeOfPage, Data.PAGE);
    }

}
