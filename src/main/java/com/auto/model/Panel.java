package com.auto.model;

import com.auto.data.enums.Data;
import com.auto.utils.FakerUtils;
import com.auto.utils.JsonUtils;

import java.util.Hashtable;

@lombok.Data
public class Panel {
    private static Hashtable<String, String> data;

    private String type;
    private String dataProfile;
    private String displayName;
    private String chartTitle;
    private String isShowTitle;
    private String chartType;
    private String style;
    private String category;
    private String categoryCaption;
    private String series;
    private String seriesCaption;
    private String legends;
    private String dataLabels;

    public Panel() {
        this.type = "Chart";
        this.dataProfile = "Action Implementation By Status";
        this.displayName = FakerUtils.word();
        this.chartTitle = "";
        this.isShowTitle = "false";
        this.chartType = "Pie";
        this.style = "2D";
        this.category = "";
        this.categoryCaption = "";
        this.series = "Name";
        this.seriesCaption = "";
        this.legends = "";
        this.dataLabels = "";
    }

    public Panel(String typeOfPanel) {
        getPanelData(typeOfPanel);
        this.type = data.get("type");
        this.dataProfile = data.get("dataProfile");
        this.displayName = data.get("displayName");
        this.chartTitle = data.get("chartTitle");
        this.isShowTitle = data.get("isShowTitle");
        this.chartType = data.get("chartType");
        this.style = data.get("style");
        this.category = data.get("category");
        this.categoryCaption = data.get("categoryCaption");
        this.series = data.get("series");
        this.seriesCaption = data.get("seriesCaption");
        this.legends = data.get("legends");
        this.dataLabels = data.get("dataLabels");
    }

    private static void getPanelData(String typePanel) {
        data = JsonUtils.getData(typePanel, Data.PANEL_DATA_FILE);
    }


}
