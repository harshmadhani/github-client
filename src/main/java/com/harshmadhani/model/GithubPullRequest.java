package com.harshmadhani.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubPullRequest {
    public String number;
    public String state;
    public Milestone milestone;
    public static class Milestone{
        public String title;
    }
}
