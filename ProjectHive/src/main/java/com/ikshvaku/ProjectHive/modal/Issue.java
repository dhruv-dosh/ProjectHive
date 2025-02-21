package com.ikshvaku.ProjectHive.modal;

import jakarta.persistence.*;

@Entity
public class Issue {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generate ID
    private Long id;

    @ManyToOne
    private User assignee;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }
}
