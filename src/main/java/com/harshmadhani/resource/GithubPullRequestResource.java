package com.harshmadhani.resource;

import com.harshmadhani.model.GithubPullRequest;
import com.harshmadhani.service.GithubPullRequestService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/github")
public class GithubPullRequestResource {
    @Inject
    @RestClient
    GithubPullRequestService githubPullRequestService;

    @GET
    @Path("/pr/{prNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public GithubPullRequest getPullRequest(@PathParam("prNumber") String prNumber){
        return githubPullRequestService.getPullRequestByNumber(prNumber);
    }
}
