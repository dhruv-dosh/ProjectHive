package com.ikshvaku.ProjectHive.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity // User is an Entity
//@Data // For Getter and Setter methods
public class User {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generate ID
    private Long id;
    private String fullname;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //To Not Show Password to client side
    private String password;

    public Long getId() {
        return id;
    }

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

    public int getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(int projectSize) {
        this.projectSize = projectSize;
    }

    public User(Long id, String fullname, String email, String password, List<Issue> assignedIssue, int projectSize) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.assignedIssue = assignedIssue;
        this.projectSize = projectSize;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", assignedIssue=" + assignedIssue +
                ", projectSize=" + projectSize +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Issue> assignedIssue = new ArrayList<>();

    private int projectSize = 0; // Ensure default initialization
}
