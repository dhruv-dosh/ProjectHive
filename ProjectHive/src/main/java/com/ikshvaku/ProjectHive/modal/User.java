package com.ikshvaku.ProjectHive.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity // User is an Entity
@Data // For Getter and Setter methods
public class User {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generate ID
    private Long id;
    private String fullname;
    private String email;
    private String password;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Issue> assignedIssue = new ArrayList<>();

    private int projectSize = 0; // Ensure default initialization
}
