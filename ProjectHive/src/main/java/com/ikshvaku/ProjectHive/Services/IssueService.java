package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Issue;
import com.ikshvaku.ProjectHive.modal.User;
import com.ikshvaku.ProjectHive.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId)throws Exception;
    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;
    void deleteIssue(Long issueId,Long userId) throws Exception;
    Issue addUserToIssue(Long issueId,Long userId)throws Exception;
    Issue updateStatus(Long issueId, String status)throws Exception;
}
