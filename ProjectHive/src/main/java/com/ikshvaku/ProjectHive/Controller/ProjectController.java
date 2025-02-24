package com.ikshvaku.ProjectHive.Controller;


import com.ikshvaku.ProjectHive.Response.MessageResponse;
import com.ikshvaku.ProjectHive.Services.ProjectService;
import com.ikshvaku.ProjectHive.Services.UserService;
import com.ikshvaku.ProjectHive.modal.Chat;
import com.ikshvaku.ProjectHive.modal.Project;
import com.ikshvaku.ProjectHive.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false)String catagory,
            @RequestParam(required = false)String tags,
            @RequestHeader("Authorisation")String jwt
    )throws Exception{
       User user = userService.findUserProfileByJwt(jwt);
       List<Project> projects = projectService.getProjectByTeam(user,catagory,tags);
       return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectsById(
            @PathVariable Long projectId,
            @RequestHeader("Authorisation")String jwt
    )throws Exception{
       User user = userService.findUserProfileByJwt(jwt);
       Project project = projectService.getProjectById(projectId);
       return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorisation")String jwt,
            @RequestBody Project project
    )throws Exception{
       User user = userService.findUserProfileByJwt(jwt);
       Project createProject = projectService.createProject(project,user);
       return new ResponseEntity<>(createProject, HttpStatus.CREATED);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorisation")String jwt,
            @RequestBody Project project
    )throws Exception{
       User user = userService.findUserProfileByJwt(jwt);
       Project updatedProject = projectService.updateProject(project,projectId);
       return new ResponseEntity<>(updatedProject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorisation")String jwt
    )throws Exception{
       User user = userService.findUserProfileByJwt(jwt);
       projectService.deleteProject(projectId,user.getId());
        MessageResponse messageResponse = new MessageResponse("Project Deleted Successfully");
       return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(
            @RequestParam(required = false)String keyword,
            @RequestHeader("Authorisation")String jwt
    )throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.searchProjects(keyword,user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectsId(
            @PathVariable Long projectId,
            @RequestHeader("Authorisation")String jwt
    )throws Exception{
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }


}
