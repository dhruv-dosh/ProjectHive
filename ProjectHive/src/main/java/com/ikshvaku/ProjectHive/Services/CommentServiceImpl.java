package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Comment;
import com.ikshvaku.ProjectHive.modal.Issue;
import com.ikshvaku.ProjectHive.modal.User;
import com.ikshvaku.ProjectHive.repository.CommentRepository;
import com.ikshvaku.ProjectHive.repository.IssueRepository;
import com.ikshvaku.ProjectHive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(issueOptional.isEmpty()){
            throw new Exception("Issue Not Found");
        }
        if (userOptional.isEmpty()) {
            throw new Exception("User Not Found With This Id");
        }
        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setIssue(issue);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);
        issue.getComments().add(savedComment);
        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long UserId) {

    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {
        return null;
    }
}
