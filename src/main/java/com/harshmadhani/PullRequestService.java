package com.harshmadhani;

import com.harshmadhani.model.PullRequest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pulls")
@RegisterRestClient
public interface PullRequestService {
    @GET
    @Path("/{prNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public PullRequest getPullRequestByNumber(@PathParam("prNumber") String prNumber);
}
