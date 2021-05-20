package com.harshmadhani.resource;

import com.harshmadhani.model.GithubPullRequest;
import com.harshmadhani.model.JiraIssue;
import com.harshmadhani.service.GithubPullRequestService;
import com.harshmadhani.service.JiraIssueService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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

    private static final Logger log = Logger.getLogger(GithubJiraResource.class);

    @GET
    @Path("/invalidjira")
    public List<JiraIssue> getInvalidJira(@QueryParam("label") String label){
        List<JiraIssue> invalidJiras = new ArrayList<>();
        String jql = "project=QUARKUS AND fixVersion=1.11.7.GA AND component=\"team/eng\" AND status = Implemented";
        JiraIssue[] jiras = jiraIssueService.getJiraIssuesByJql(jql).jiras;
        if(jiras.length>0){
            List<JiraIssue> jiraList = Arrays.asList(jiras);
            jiraList.stream().forEach(jira->{
                if(!checkLabelAndMilestone(jira,label))
                    invalidJiras.add(jira);
            });
            return invalidJiras;
        }
        return Collections.emptyList();
    }

    private boolean checkLabelAndMilestone(JiraIssue jira, String labelToCheck) {
        String[] prList = jira.fields.github_pr;
        if(prList !=null && prList.length>0){
            String prNumber = Stream.of(prList[0].split("\\/")).reduce((first, last)->last).get();
            log.debug("PR number is "+prNumber);
            GithubPullRequest pullRequest = githubPullRequestService.getPullRequestByNumber(prNumber);
            if(pullRequest != null
                    || (pullRequest.labels != null && Stream.of(pullRequest.labels).anyMatch(label->label.name.equals(labelToCheck)))
                    || (pullRequest.milestone!= null)){
                log.debug("state of PR is "+pullRequest.state);
                log.debug("milestone of PR is "+pullRequest.milestone.title);
                return true;
            }
            return false;
        }
        return false;
    }
}