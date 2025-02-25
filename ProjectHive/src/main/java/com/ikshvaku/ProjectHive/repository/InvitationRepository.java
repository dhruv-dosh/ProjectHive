package com.ikshvaku.ProjectHive.repository;

import com.ikshvaku.ProjectHive.modal.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvitationRepository extends JpaRepository<Invitation,Long> {

    Invitation findByToken(String token);
    Invitation findByEmail(String userEmail);

}
