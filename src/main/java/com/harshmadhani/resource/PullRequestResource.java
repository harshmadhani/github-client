package com.harshmadhani.resource;

import com.harshmadhani.model.PullRequest;
import com.harshmadhani.service.PullRequestService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/github")
public class PullRequestResource {
    @Inject
    @RestClient
    PullRequestService pullRequestService;

    @GET
    @Path("/pr/{prNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public PullRequest getPullRequest(@PathParam("prNumber") String prNumber){
        return pullRequestService.getPullRequestByNumber(prNumber);
    }
}
