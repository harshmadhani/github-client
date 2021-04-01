package com.harshmadhani.resource;

import com.harshmadhani.model.JiraIssue;
import com.harshmadhani.model.JiraIssueSearch;
import com.harshmadhani.service.JiraIssueService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/jira")
public class JiraIssueResource {
    @Inject
    @RestClient
    JiraIssueService jiraIssueService;

    @GET
    @Path("/issue/{issueNumber}")
    public JiraIssue getIssue(@PathParam("issueNumber") String issueNumber){
        return jiraIssueService.getJiraIssueByNumber(issueNumber);
    }

    @GET
    @Path("/searchissue")
    public JiraIssueSearch getIssuesByJql(@QueryParam("jql") String jql){
        return jiraIssueService.getJiraIssuesByJql(jql);
    }
}
