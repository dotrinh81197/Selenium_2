package com.auto.integration.jira;

public class JiraIssue {
    private String link;
    private String key;
    private String type;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JiraIssue(String key, String link, String type) {
        this.key = key;
        this.link = link;
        this.type = type;
    }

    public JiraIssue(String key) {
        this.key = key;
    }

    public JiraIssue() {
    }

    public JiraIssue(String key, String link) {
        this.key = key;
        this.link = link;
        this.type = "Bug";
    }
}
