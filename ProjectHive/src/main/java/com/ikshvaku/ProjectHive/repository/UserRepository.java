package com.ikshvaku.ProjectHive.repository;

import com.ikshvaku.ProjectHive.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
