package com.harshmadhani.service;

import com.harshmadhani.model.JiraIssue;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/issue")
@RegisterRestClient
public interface JiraIssueService {
    @GET
    @Path("/{issueNumber}")
    public JiraIssue getJiraIssueByNumber(@PathParam("issueNumber") String issueNumber);
}
