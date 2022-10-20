package com.auto.integration.jira;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.api.domain.input.TransitionInput;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.auto.utils.JsonUtils;
import io.atlassian.util.concurrent.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.auto.utils.Constants.ConfigFiles;
import static com.auto.utils.Constants.JIRA;

public class JiraReporter {
    private static final Logger log = LoggerFactory.getLogger(JiraReporter.class);
    private static JiraReporter instance = null;
    private JiraRestClient client;
    private JiraConfiguration configuration = null;
    private List<IssueType> issueTypes;

    private JiraReporter() {
        this.configuration = JsonUtils.to(ConfigFiles.get(JIRA), JiraConfiguration.class);
    }

    public static JiraReporter instance() {
        if (instance == null) {
            instance = new JiraReporter();
        }
        return instance;
    }

    public JiraConfiguration config() {
        return this.configuration;
    }

    private URI uri() {
        try {
            return new URI(configuration.getUrl());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JiraRestClient client() {
        if (client == null) {
            JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            client = factory.createWithBasicHttpAuthentication(this.uri(), configuration.getUsername(),
                    configuration.getPassword());
        }
        return client;
    }

    public void processBug(String summary, List<String> steps, String attachment) {
        Issue bug = searchBug(summary);
        String description = buildSteps(steps);
        String link = "";
        if (bug != null) {
            link = configuration.getIssueLink(bug.getKey());
            log.info("Found a bug {} on Jira", link);
            // Update bug status to reopened if it is done
            if (bug.getStatus().getName().equalsIgnoreCase("done")) {
                log.info("Bug {} was fixed before. Reopened it", link);
                updateStatus(bug, "Reopened");
            }
        } else {
            // Create new bug
            BasicIssue newIssue = createIssue(summary, description);
            link = configuration.getIssueLink(newIssue.getKey());
            log.info("New bug {} has been created for failed test case", link);
        }
    }

    public BasicIssue createIssue(String summary, String description) {
        IssueType issueType = getType("Bug");
        IssueRestClient issueClient = client().getIssueClient();
        IssueInputBuilder iib = new IssueInputBuilder();
        iib.setProjectKey(configuration.getProjectKey());
        iib.setSummary(summary);
        iib.setIssueType(issueType);
        iib.setDescription(description);
        iib.setFieldValue("labels", Arrays.asList(configuration.getBugLabel()));
        IssueInput issue = iib.build();
        return issueClient.createIssue(issue).claim();
    }

    public void updateStatus(Issue issue, String status) {
        IssueRestClient issueClient = client().getIssueClient();
        Iterable<Transition> transitions = issueClient
                .getTransitions(issue).claim();
        Transition transition = StreamSupport.stream(transitions.spliterator(), false).
                filter(t -> t.getName().equalsIgnoreCase(status)).findFirst().orElse(null);
        if (transition != null) {
            issueClient.transition(issue,
                    new TransitionInput(transition.getId())).claim();
        } else {
            throw new IllegalArgumentException("Cannot find " + status + " on Jira");
        }

    }

    private List<IssueType> issueTypes() {
        if (this.issueTypes == null) {
            Iterable<IssueType> types = client().getMetadataClient()
                    .getIssueTypes().claim();
            this.issueTypes = StreamSupport.stream(types.spliterator(), false)
                    .collect(Collectors.toList());
        }
        return this.issueTypes;
    }

    private IssueType getType(String name) {
        return issueTypes().stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    private Issue searchBug(String summary) {
        String jql = String.format("summary ~ \"%s\" and labels in (%s)", summary, configuration.getBugLabel());
        SearchRestClient searchRestClient = this.client().getSearchClient();
        Promise<SearchResult> searchResult = searchRestClient.searchJql(jql);
        SearchResult results = searchResult.claim();
        return StreamSupport.stream(results.getIssues().spliterator(), false).findFirst().orElse(null);
    }

    private String buildSteps(List<String> steps) {
        StringBuilder builder = new StringBuilder();
        builder.append("Step to reproduce");
        AtomicInteger index = new AtomicInteger();
        steps.stream().map(s -> index.getAndIncrement() + 1 + ". " + s)
                .forEach(s -> builder.append("\n").append(s));
        return builder.toString();
    }
}
