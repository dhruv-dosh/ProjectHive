package com.ikshvaku.ProjectHive.repository;

import com.ikshvaku.ProjectHive.modal.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
