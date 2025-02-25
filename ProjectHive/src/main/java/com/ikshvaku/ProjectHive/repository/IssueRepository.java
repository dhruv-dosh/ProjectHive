package com.ikshvaku.ProjectHive.repository;

import com.ikshvaku.ProjectHive.modal.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {
    public List<Issue> findProjectById(Long id);
}
