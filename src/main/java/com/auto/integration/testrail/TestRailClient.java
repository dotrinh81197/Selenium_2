package com.auto.integration.testrail;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestRailClient {
    private final APIClient client;

    public TestRailClient(String url, String username, String password) {
        client = new APIClient(url);
        client.setUser(username);
        client.setPassword(password);
    }

    public void addResultForCase(int runId, int caseId, int statusId, String comment) throws Exception {
        String path = String.format("add_result_for_case/%s/%s", runId, caseId);
        Map<String, Object> data = new HashMap<>();
        data.put("status_id", statusId);
        data.put("comment", comment);
        JSONObject r = (JSONObject) client.sendPost(path, data);
    }
}
