package com.harshmadhani.service;

import com.harshmadhani.model.JiraIssue;
import com.harshmadhani.model.JiraIssueSearch;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RegisterRestClient
public interface JiraIssueService {
    @GET
    @Path("/issue/{issueNumber}")
    public JiraIssue getJiraIssueByNumber(@PathParam("issueNumber") String issueNumber);

    @GET
    @Path("/search")
    public JiraIssueSearch getJiraIssuesByJql(@QueryParam("jql") String jql);
}
