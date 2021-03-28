package com.harshmadhani.service;

import com.harshmadhani.model.PullRequest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/pulls")
@RegisterRestClient
public interface PullRequestService {
    @GET
    @Path("/{prNumber}")
    public PullRequest getPullRequestByNumber(@PathParam("prNumber") String prNumber);
}
