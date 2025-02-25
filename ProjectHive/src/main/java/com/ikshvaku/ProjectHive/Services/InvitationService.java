package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {
    public void sendInvitation(String email, Long projectId) throws MessagingException;
    public Invitation acceptInvitation(String token,Long userId) throws Exception;

    public String getTokenByUserEmail(String userEmail) throws Exception;

    void deleteToken(String token);
}
