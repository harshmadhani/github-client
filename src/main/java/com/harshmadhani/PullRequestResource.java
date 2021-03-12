package com.harshmadhani;

import com.harshmadhani.model.PullRequest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pr")
public class PullRequestResource {
    @Inject
    @RestClient
    PullRequestService pullRequestService;

    @GET
    @Path("/{prNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public PullRequest getPullRequest(@PathParam("prNumber") String prNumber){
        return pullRequestService.getPullRequestByNumber(prNumber);
    }
}
