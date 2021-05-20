package com.harshmadhani.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubPullRequest {
    public String number;
    public String state;
    public Milestone milestone;
    public Label[] labels;
    public static class Milestone{
        public String title;
    }
    public static class Label{
        public String name;
    }
}
