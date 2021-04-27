package com.harshmadhani.resource;

import com.harshmadhani.model.GithubPullRequest;
import com.harshmadhani.model.JiraIssue;
import com.harshmadhani.service.GithubPullRequestService;
import com.harshmadhani.service.JiraIssueService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Path("/ghjira")
public class GithubJiraResource {

    @Inject
    @RestClient
    JiraIssueService jiraIssueService;

    @Inject
    @RestClient
    GithubPullRequestService githubPullRequestService;

    @GET
    @Path("/invalidjira")
    public List<JiraIssue> getInvalidJira(){
        List<JiraIssue> invalidJiras = new ArrayList<>();
        String jql = "project=QUARKUS AND fixVersion=1.11.7.GA AND component=\"team/eng\" AND status = Implemented";
        JiraIssue[] jiras = jiraIssueService.getJiraIssuesByJql(jql).jiras;
        if(jiras.length>0){
            List<JiraIssue> jiraList = Arrays.asList(jiras);
            jiraList.stream().forEach(jira->{
                if(!checkMilestone(jira))
                    invalidJiras.add(jira);
            });
            return invalidJiras;
        }
        return Collections.emptyList();
    }

    private boolean checkMilestone(JiraIssue jira) {
        String[] prList = jira.fields.github_pr;
        if(prList !=null && prList.length>0){
            String prNumber = Stream.of(prList[0].split("\\/")).reduce((first, last)->last).get();
            System.out.println(prNumber);
            GithubPullRequest pullRequest = githubPullRequestService.getPullRequestByNumber(prNumber);
            if(pullRequest!=null && pullRequest.milestone!=null) {
                System.out.println(pullRequest.state);
                System.out.println(pullRequest.milestone.title);
                return true;
            }
            return false;
        }
        return false;
    }
}