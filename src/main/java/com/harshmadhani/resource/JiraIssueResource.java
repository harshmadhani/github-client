package com.harshmadhani.resource;

import com.harshmadhani.model.JiraIssue;
import com.harshmadhani.service.JiraIssueService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/issue")
public class JiraIssueResource {
    @Inject
    @RestClient
    JiraIssueService jiraIssueService;

    @GET
    @Path("/{issueNumber}")
    public JiraIssue getIssue(@PathParam("issueNumber") String issueNumber){
        return jiraIssueService.getJiraIssueByNumber(issueNumber);
    }
}
