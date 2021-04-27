package com.harshmadhani.service;

import com.harshmadhani.model.GithubPullRequest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/pulls")
@RegisterRestClient
public interface GithubPullRequestService {
    @GET
    @Path("/{prNumber}")
    GithubPullRequest getPullRequestByNumber(@PathParam("prNumber") String prNumber);
}
