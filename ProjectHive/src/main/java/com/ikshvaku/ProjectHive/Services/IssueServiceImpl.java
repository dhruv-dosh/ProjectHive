package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Issue;
import com.ikshvaku.ProjectHive.modal.Project;
import com.ikshvaku.ProjectHive.modal.User;
import com.ikshvaku.ProjectHive.repository.IssueRepository;
import com.ikshvaku.ProjectHive.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService{
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;
    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if (issue.isPresent()){
            return issue.get();
        }
        throw new Exception("There Is No Issue With This Issue Id");
    }

    @Override
    public List<Issue> getIssueByProjectId(Long issueProjectId) throws Exception {
        return issueRepository.findProjectById(issueProjectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
        Project project = projectService.getProjectById(issueRequest.getIssueProjectId());
        Issue issue = new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        issue.setPriority(issueRequest.getPriority());
        issue.setIssueProjectId(issueRequest.getIssueProjectId());
        issue.setDueDate(issueRequest.getDueDate());
        issue.setProject(project);
        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        getIssueById(issueId);
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Issue issue = getIssueById(issueId);
        issue.setStatus(status);
        return issueRepository.save(issue);
    }
}
