package com.harshmadhani.resource;

import com.harshmadhani.model.JiraIssue;
import com.harshmadhani.model.JiraIssueSearch;
import com.harshmadhani.service.JiraIssueService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/jira")
@ApplicationScoped
public class JiraIssueResource {
    @Inject
    @RestClient
    JiraIssueService jiraIssueService;

    @GET
    @Path("/issue/{issueNumber}")
    @PermitAll
    public JiraIssue getIssue(@PathParam("issueNumber") String issueNumber, @Context SecurityContext ctx){
        Principal caller = ctx.getUserPrincipal();
        String name = caller == null ? "anonymous" : caller.getName();
        System.out.println("hello "+name+" issecure "+ctx.isSecure()+" authscheme "+ctx.getAuthenticationScheme());
        return jiraIssueService.getJiraIssueByNumber(issueNumber);
    }

    @GET
    @Path("/searchissue")
    @RolesAllowed({"Subscriber","Echoer"})
    public JiraIssueSearch getIssuesByJql(@QueryParam("jql") String jql, @Context SecurityContext ctx){
        Principal caller = ctx.getUserPrincipal();
        String name = caller == null ? "anonymous" : caller.getName();
        System.out.println("hello "+name+" issecure "+ctx.isSecure()+" authscheme "+ctx.getAuthenticationScheme());
        return jiraIssueService.getJiraIssuesByJql(jql);
    }
}
