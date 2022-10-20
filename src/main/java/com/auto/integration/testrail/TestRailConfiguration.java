package com.auto.integration.testrail;

import org.apache.commons.lang3.StringUtils;

public class TestRailConfiguration {
    private String url;
    private String username;
    private String password;
    private int runId;
    private boolean publishResult;

    public int getRunId() {
        // Use TR_TEST_RUN_ID env first
        String id = System.getProperty("TR_TEST_RUN_ID");
        if (id != null && id.length() > 0) {
            return Integer.parseInt(id);
        }
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public boolean isPublishResult() {
        // Use TR_PUBLISH_RESULT env first
        String publish = System.getProperty("TR_PUBLISH_RESULT");
        if (publish != null && publish.length() > 0) {
            return Boolean.parseBoolean(publish);
        }
        return publishResult;
    }

    public void setPublishResult(boolean publishResult) {
        this.publishResult = publishResult;
    }

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

    public String getCaseViewLink(String id) {
        return StringUtils.removeEnd(this.url, "/") + "/index.php?/cases/view/" + id;
    }
}
