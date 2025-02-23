package com.ikshvaku.ProjectHive.repository;

import com.ikshvaku.ProjectHive.modal.Project;
import com.ikshvaku.ProjectHive.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project,Long> {
//    List<Project>findByOwner(User user); // Find By Owner
    List<Project> findByNameContainingAndTeamContains(String partialName,User user); // Can Search Project By Its Name

//    @Query("SELECT p From Project p join p.team t where t=:user")
//    List<Project> findProjectByTeam(@Param("user") User user); // By Team

    List<Project> findByTeamContainingOwner(User user,User owner);//To get All projects in which user Worked;
}
