package com.harshmadhani.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssueSearch {
    @JsonAlias("issues")
    public JiraIssue[] jiras;
    @JsonAlias("total")
    public int jiraIssuesTotal;
}
