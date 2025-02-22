package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Chat;
import com.ikshvaku.ProjectHive.modal.Project;
import com.ikshvaku.ProjectHive.modal.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Override
    public Project createProject(Project project, User user) throws Exception {
        return null;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        return null;
    }

    @Override
    public Project getProjectId(Long projectId) throws Exception {
        return null;
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {

    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {
        return null;
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {

    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) throws Exception {

    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        return null;
    }
}
