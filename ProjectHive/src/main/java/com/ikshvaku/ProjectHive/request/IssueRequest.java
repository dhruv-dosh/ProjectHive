package com.ikshvaku.ProjectHive.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueRequest {
    private String title;
    private String description;
    private String status;
    private String priority;
    private Long issueProjectId;

    private LocalDate dueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Long getIssueProjectId() {
        return issueProjectId;
    }

    public void setIssueProjectId(Long issueProjectId) {
        this.issueProjectId = issueProjectId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
