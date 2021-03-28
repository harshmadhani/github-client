package com.harshmadhani.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssue {
    public String key;
    public Fields fields;
    public static class Fields {
        public String summary;
        @JsonAlias("customfield_12310220")
        public String[] github_pr;
    }
}
