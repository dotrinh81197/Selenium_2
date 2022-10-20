package com.auto.integration.jira;

import org.apache.commons.lang3.StringUtils;

public class JiraConfiguration {

    private String url;

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    private String projectKey;
    private String username;
    private String password;
    private String bugLabel;

    public boolean isLogBug() {
        // Use AUTO_LOG_BUG env first
        String autoLogBug = System.getProperty("AUTO_LOG_BUG");
        if (autoLogBug != null && autoLogBug.length() > 0) {
            return Boolean.parseBoolean(autoLogBug);
        }
        return logBug;
    }

    public void setLogBug(boolean logBug) {
        this.logBug = logBug;
    }

    private boolean logBug;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBugLabel() {
        return bugLabel;
    }

    public void setBugLabel(String bugLabel) {
        this.bugLabel = bugLabel;
    }

    public String getIssueLink(String key) {
        return StringUtils.removeEnd(this.url, "/") + "/browse/" + key;
    }
}
