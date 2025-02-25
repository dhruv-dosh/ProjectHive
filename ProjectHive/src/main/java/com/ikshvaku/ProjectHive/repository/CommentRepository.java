package com.ikshvaku.ProjectHive.repository;

import com.ikshvaku.ProjectHive.modal.Comment;
import org.apache.commons.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
     List<Comment> findCommentByIssueId(Long issueId);
}
